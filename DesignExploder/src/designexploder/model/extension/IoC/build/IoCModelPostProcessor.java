package designexploder.model.extension.IoC.build;

import java.util.Collections;
import java.util.Set;

import designexploder.model.BasicModelUtil;
import designexploder.model.Connection;
import designexploder.model.Node;
import designexploder.model.NodeContainer;
import designexploder.model.build.IdAwareModelBuilder;
import designexploder.model.extension.IoC.ApplicationContext;
import designexploder.model.extension.IoC.BeanInjection;
import designexploder.model.extension.IoC.BeanNode;
import designexploder.model.extension.IoC.Dependency;
import designexploder.model.extension.IoC.impl.IoCModelFactory;
import designexploder.model.extension.classnode.ClassConnection;
import designexploder.model.extension.classnode.ClassItem;
import designexploder.model.extension.classnode.ClassModelNatures;
import designexploder.model.extension.classnode.ClassModelUtil;
import designexploder.model.extension.classnode.ClassNode;
import designexploder.model.extension.classnode.ClassType;
import designexploder.model.extension.classnode.Method;
import designexploder.model.extension.classnode.Parameter;
import designexploder.model.extension.classnode.Type;
import designexploder.model.impl.BasicModelFactory;
import designexploder.util.adt.ADTUtil;
import designexploder.util.adt.Condition;
import designexploder.util.adt.IterableIterator;

public class IoCModelPostProcessor extends IdAwareModelBuilder {

	@Override
	public NodeContainer build(NodeContainer diagram) {
		setDiagram(diagram);
		for(Node node : new IterableIterator<Node>(diagram.getDeepIterator())) {
			if(node.getExtension(BeanNode.class) != null) {
				registerNode(node);
			}
		}
		return enrichModel(diagram, Collections.<Node>emptySet());
	}

	private NodeContainer enrichModel(NodeContainer container, Set<Node> parentBeans) {

		// Start from outer scopes, but noticing facades.
		ApplicationContext context = container.getExtension(ApplicationContext.class);
		Set<Node> beanNodes = null;
		if(context != null) {
			beanNodes = ADTUtil.createSet(BasicModelUtil.getExtendedNodes(container, BeanNode.class).iterator());
			// TODO: Facades: Remove facades (as they are already in parentBeans), and add first children level containers facades.
			// TODO: Facades are the target for collection flavored dependencies.
			for (Node node : beanNodes) {
				BeanNode bean = node.getExtension(BeanNode.class);
				for (Dependency dependency : bean.getDependencies()) {
					resolveDependency(beanNodes, node, dependency);
				}
			}
		}
		
		// Continue with children contexts
		for(NodeContainer child : BasicModelUtil.getContainerNodes(container)) {
			enrichModel(child, beanNodes == null ? parentBeans : beanNodes);
		}
		
		return container;
	}

	private void resolveDependency(Set<Node> beanNodes, Node node,
			Dependency dependency) {
		// TODO: Check tree flavor
		// TODO: Check for collection flavor
		ClassItem classItem = dependency.getTarget();
		if(classItem.isMethod()) {
			Method method = (Method) classItem;
			Parameter parameter = method.getParameters().get(0);
			Type type = parameter.getType();
			// Won't inject basic types or arrays (except for collection flavor)
			if(type.isClassType()) {
				// TODO: Super method should be added to this class, possibly in the bean factory.
				final ClassType targetType = type.asClassType();
				Set<Node> candidates = ADTUtil.filterCollection(beanNodes, new Condition<Node>() {
					@Override
					public boolean check(Node bean) {
						Type beanType = bean.getExtension(ClassNode.class).getType();
						return beanType.isClassType() && ClassModelUtil.isSubclass(beanType.asClassType(), targetType);
					}
				});
				if(candidates.size() == 1) { // TODO: Collection flavor
					createInjection(node, candidates.iterator().next(), dependency);
				}
			}
		}
		// else {} // TODO: Attribute class item handling
	}

	private void createInjection(Node node, Node target, Dependency dependency) {
		// Find existent 
		for (Connection connection : 
			BasicModelUtil.getExtendedModels(BasicModelUtil.findConnections(node, target), ClassConnection.class)) {
			if(connection.getExtension(BeanInjection.class) != null) continue;
			ClassConnection classConnection = connection.getExtension(ClassConnection.class);
			// TODO: For collection flavored dependencies, the connection nature will be COMPOSITION 
			if(classConnection.getNature() == ClassModelNatures.ASSOCIATION && classConnection.getName().equals(dependency.getName())) {
				addBeanInjection(connection, dependency);
				return;
			}
		}
		// Create new connection
		Connection connection = BasicModelFactory.getInstance().createConnection();
		connection.setId(BasicModelUtil.nextIdForConnection(node, target));
		addBeanInjection(connection, dependency);
		addConnection(connection, node, target);
	}

	private void addBeanInjection(Connection connection, Dependency dependency) {
		BeanInjection injection = IoCModelFactory.getInstance().createBeanInjection();
		injection.setDependency(dependency);
		connection.addExtension(BeanInjection.class, injection);
	}
}
