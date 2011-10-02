package designexploder.model.extension.IoC.build;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import designexploder.model.BasicModelUtil;
import designexploder.model.Connection;
import designexploder.model.Node;
import designexploder.model.NodeContainer;
import designexploder.model.extension.IoC.ApplicationContext;
import designexploder.model.extension.IoC.BeanInjection;
import designexploder.model.extension.IoC.BeanNode;
import designexploder.model.extension.IoC.Dependency;
import designexploder.model.extension.IoC.IoCModelNatures;
import designexploder.model.extension.IoC.IoCModelUtil;
import designexploder.model.extension.IoC.impl.IoCModelFactory;
import designexploder.model.extension.classnode.Attribute;
import designexploder.model.extension.classnode.ClassRelation;
import designexploder.model.extension.classnode.ClassItem;
import designexploder.model.extension.classnode.ClassModelUtil;
import designexploder.model.extension.classnode.ClassNode;
import designexploder.model.extension.classnode.ClassType;
import designexploder.model.extension.classnode.Method;
import designexploder.model.extension.classnode.Parameter;
import designexploder.model.extension.classnode.Type;
import designexploder.model.extension.common.Nature;
import designexploder.model.impl.BasicModelFactory;
import designexploder.util.adt.ADTUtil;
import designexploder.util.adt.Condition;

public class IoCModelDependenciesProcessor {

	public NodeContainer enrichModel(NodeContainer container, Set<Node> availableBeans) {
		// Start from outer scopes, but noticing facades.
		ApplicationContext context = container.getExtension(ApplicationContext.class);
		if(context != null) {
			Set<Node> beanNodes = ADTUtil.createSet(BasicModelUtil.getExtendedNodes(container, BeanNode.class).iterator());
			availableBeans.addAll(beanNodes);
			Set<Node> availableBeansAndFacades = IoCModelUtil.addFacadeBeans(container, new HashSet<Node>(availableBeans));
			for (Node node : beanNodes) {
				BeanNode bean = node.getExtension(BeanNode.class);
				for (Dependency dependency : bean.getDependencies()) {
					resolveDependency(availableBeansAndFacades, node, dependency);
				}
			}
		}
		
		// Continue with children contexts
		for(NodeContainer child : BasicModelUtil.getContainerNodes(container)) {
			enrichModel(child, new HashSet<Node>(availableBeans));
		}
		
		return container;
	}

	private void resolveDependency(Set<Node> availableBeans, Node node,
			Dependency dependency) {
		// TODO: Check tree flavor
		// TODO: Check for collection flavor
		final ClassItem classItem = dependency.getTarget();
		Type type;
		if(classItem.isMethod()) {
			Method method = (Method) classItem;
			Parameter parameter = method.getParameters().get(0);
			type = parameter.getType();
		} else { // attribute
			Attribute attribute = (Attribute) classItem;
			type = attribute.getType();
		}
		boolean collection = false;
		if(type.isBasic()) {
			// Won't inject basic types or arrays (except for collection flavor)
			type = null;
		} else if(type.isArray()) {
			type = type.asArrayType().getInnerType();
			collection = true;
		} else if(type.isClassType() && ClassModelUtil.isCollection(type.asClassType())) {
			 List<Type> typeParameters = type.asClassType().getTypeParameters();
			 type = typeParameters.size() == 1 ? typeParameters.get(0) : null;
			 collection = true;
		}
		if(type != null) {
			// TODO: Super method should be added to this class, possibly in the bean factory.
			final ClassType targetType = type.asClassType();
			Set<Node> candidates = ADTUtil.filterCollection(availableBeans, new Condition<Node>() {
				@Override
				public boolean check(Node bean) {
					Type beanType = bean.getExtension(ClassNode.class).getType();
					return beanType.isClassType() && ClassModelUtil.isSubclass(beanType.asClassType(), targetType);
				}
			});
			if((candidates.size() == 1 && !collection) || (candidates.size() > 0 && collection && !candidates.contains(node))) { 
				// Resolved dependency!
				dependency.setNature(collection ? IoCModelNatures.INJECTION_COLLECTION :
					getDependencyNature(node, candidates.iterator().next())); 
				for (Node candidate : candidates) {
					createInjection(node, candidate, dependency);
				}
			} else if(classItem.isAttribute()) {
				// Add unresolved injection nature to associations
				createUnresolvedInjections(node, dependency, candidates);
			}
		}
	}

	private Nature getDependencyNature(Node node, Node candidate) {
		if(node == candidate) return IoCModelNatures.INJECTION_TREE;
		NodeContainer candidateContext = candidate.getNodeContainer();
		return  candidateContext instanceof Node && ((Node) candidateContext).getNodeContainer() == node.getNodeContainer() ?
					IoCModelNatures.INJECTION_PROXY : IoCModelNatures.INJECTION_BEAN;
	}

	public void createUnresolvedInjections(Node node, Dependency dependency, Set<Node> candidates) {
		if(candidates.size() != 0) { // Warn candidates
			for (Node candidate : candidates) {
				createInjection(node, candidate, dependency);
			}
		} else { // Warn attribute originated connections 
			Set<Connection> attributeOriginatedConnections = getAttributeOriginatedConnections(node, (Attribute) dependency.getTarget());
			for (Connection connection : attributeOriginatedConnections) {
				addBeanInjection(connection, dependency);
			}
		}
	}

	private void createInjection(Node node, Node target, Dependency dependency) {
		ClassItem origin = dependency.getTarget();
		if(origin.isAttribute()) {
			// Find existent connection: A connections which target is <target> and which origin is attribute <origin>
			for (Connection connection : getAttributeOriginatedConnections(node, (Attribute) origin)) {
				if(connection.getTarget() == target) {
					addBeanInjection(connection, dependency);
					return;
				}
			}
		}

		// Create new connection
		Connection connection = BasicModelFactory.getInstance().createConnection();
		connection.setId(BasicModelUtil.nextIdForConnection(node, target));
		addBeanInjection(connection, dependency);
		addConnection(connection, node, target);
	}

	private void addConnection(Connection connection, Node source, Node target) {
		connection.setSource(source);
		connection.setTarget(target);
		BasicModelUtil.addConnection(connection);
	}

	private void addBeanInjection(Connection connection, Dependency dependency) {
		if(connection.getExtension(BeanInjection.class) != null) return;
		BeanInjection injection = IoCModelFactory.getInstance().createBeanInjection();
		injection.setDependency(dependency);
		dependency.addBeanInjection(connection);
		connection.addExtension(injection);
	}

	private Set<Connection> getAttributeOriginatedConnections(Node node, final Attribute attribute) {
		return ADTUtil.<Connection>filterCollection(BasicModelUtil.getExtendedModels(node.getOutflows(), ClassRelation.class),
				new Condition<Connection>() {
					@Override
					public boolean check(Connection e) {
						return e.getExtension(ClassRelation.class).getOrigin() == attribute;
					}
				});
	}
}
