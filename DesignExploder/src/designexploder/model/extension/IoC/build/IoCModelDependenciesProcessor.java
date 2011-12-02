package designexploder.model.extension.IoC.build;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import designexploder.model.BasicModelUtil;
import designexploder.model.Connection;
import designexploder.model.Node;
import designexploder.model.NodeContainer;
import designexploder.model.extension.IoC.*;
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
                for (IoCAwareMethod ioCAwareMethod: bean.getIoCAwareMethods()) {
                    if(ioCAwareMethod.getNature() == IoCModelNatures.IOC_METHOD_FACTORY_UNRESOLVED) {
                        resolveFactory(beanNodes, node, (TargetedIoCAwareMethod) ioCAwareMethod);
                    }
                    if(ioCAwareMethod.getNature() == IoCModelNatures.IOC_METHOD_INSTANTIATE_UNRESOLVED) {
                        resolveInstantiate(node, (TargetedIoCAwareMethod) ioCAwareMethod);
                    }
                }
			}
		}
		
		// Continue with children contexts
		for(NodeContainer child : BasicModelUtil.getContainerNodes(container)) {
			enrichModel(child, new HashSet<Node>(availableBeans));
		}
		
		return container;
	}

    private void resolveInstantiate(Node node, TargetedIoCAwareMethod ioCAwareMethod) {
        final Type type = ioCAwareMethod.getTarget().getType();
        if(type.isClassType()) {
            final ClassType targetType = type.asClassType();
            Set<Node> scopedBeans = IoCModelUtil.getScopedBeans(targetType, node.getNodeContainer());
            if(scopedBeans.size() == 1) {
                ioCAwareMethod.setNature(IoCModelNatures.IOC_METHOD_INSTANTIATE);
                createBeanInstantiation(node, (Node) scopedBeans.iterator().next().getNodeContainer(), ioCAwareMethod);
            } else {
                for(Node scopedBean : scopedBeans) {
                    createBeanInstantiation(node, scopedBean, ioCAwareMethod);
                }
            }
        }
    }

    private void resolveFactory(Set<Node> contextBeanNodes, Node node, TargetedIoCAwareMethod ioCAwareMethod) {
        final Type type = ioCAwareMethod.getTarget().getType();
        if(type.isClassType()) {
            final ClassType targetType = type.asClassType();
            Set<Node> candidates = getExactMatchCandidates(contextBeanNodes, targetType);
            /*if((candidates.size() == 1 && !candidates.contains(node))) {
				// Resolved factory!
                ioCAwareMethod.setNature(IoCModelNatures.IOC_METHOD_FACTORY);
                createBeanInstantiation(node, candidates.iterator().next(), ioCAwareMethod);
			} else if(candidates.size() > 1) {
				// Add unresolved injection nature to associations
				createUnresolvedBeanInstantiations(node, ioCAwareMethod, candidates);
			}*/
            if((candidates.size() - (candidates.contains(node) ? 1 : 0) > 0)) {
                ioCAwareMethod.setNature(IoCModelNatures.IOC_METHOD_FACTORY);
                for(Node candidate : candidates) {
                    if(candidate != node) {
                        createBeanInstantiation(node, candidate, ioCAwareMethod);
                    }
                }
            }
        }
    }

    private void resolveDependency(Set<Node> availableBeans, Node node,
			Dependency dependency) {
		// TODO: Check tree flavor
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
        if(type.isArray()) {
			type = type.asArrayType().getInnerType();
			collection = true;
		} else if(type.isClassType() && ClassModelUtil.isCollection(type.asClassType())) {
			 List<Type> typeParameters = type.asClassType().getTypeParameters();
			 type = typeParameters.size() == 1 ? typeParameters.get(0) : null;
			 collection = true;
		}
        if(type != null && type.isBasic()) { // Won't inject basic types
			type = null;
		}
		if(type != null) {
			final ClassType targetType = type.asClassType();
            Set<Node> candidates = getCandidates(availableBeans, targetType);
            if((candidates.size() == 1 && !collection) || (candidates.size() > 0 && collection && !candidates.contains(node))) {
				// Resolved dependency!
				dependency.setNature(collection ? IoCModelUtil.getCollectionDependencyNature(node, candidates) :
					IoCModelUtil.getDependencyNature(node, candidates.iterator().next()));
				for (Node candidate : candidates) {
					createInjection(node, candidate, dependency);
				}
			} else if(classItem.isAttribute()) {
				// Add unresolved injection nature to associations
				createUnresolvedInjections(node, dependency, candidates);
			}
		}
	}

    private Set<Node> getCandidates(Set<Node> availableBeans, final ClassType targetType) {
        return ADTUtil.filterCollection(availableBeans, new Condition<Node>() {
            @Override
            public boolean check(Node bean) {
                Type beanType = bean.getExtension(ClassNode.class).getType();
                return beanType.isClassType() && ClassModelUtil.isSubclass(beanType.asClassType(), targetType);
            }
        });
    }

    private Set<Node> getExactMatchCandidates(Set<Node> availableBeans, ClassType targetType) {
        final ClassType typeErasure = targetType.getTypeErasure();
        return ADTUtil.filterCollection(availableBeans, new Condition<Node>() {
            @Override
            public boolean check(Node bean) {
                Type beanType = bean.getExtension(ClassNode.class).getType();
                return beanType.isClassType() && typeErasure.equals(beanType.asClassType().getTypeErasure());
            }
        });
    }

    public void createUnresolvedBeanInstantiations(Node node, TargetedIoCAwareMethod ioCAwareMethod, Set<Node> candidates) {
        for (Node candidate : candidates) {
            createBeanInstantiation(node, candidate, ioCAwareMethod);
        }
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

    private void createBeanInstantiation(Node node, Node target, TargetedIoCAwareMethod ioCAwareMethod) {
		// Create new connection
		Connection connection = BasicModelFactory.getInstance().createConnection();
		connection.setId(BasicModelUtil.nextIdForConnection(node, target));
		addBeanInstantiation(connection, ioCAwareMethod);
		addConnection(connection, node, target);
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

    private void addBeanInstantiation(Connection connection, TargetedIoCAwareMethod ioCAwareMethod) {
        IoCInstantiation instantiation = IoCModelFactory.getInstance().createBeanInstantiation();
        instantiation.setTargetedIoCAwareMethod(ioCAwareMethod);
        ioCAwareMethod.addIoCInstantiation(connection);
        connection.addExtension(instantiation);
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
