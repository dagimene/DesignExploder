package designexploder.model.extension.IoC;

import java.util.HashSet;
import java.util.Set;

import designexploder.model.Connection;
import designexploder.model.Node;
import designexploder.model.NodeContainer;
import designexploder.model.extension.classnode.*;
import designexploder.model.extension.common.Nature;
import designexploder.model.extension.common.NodeDesignProperties;
import designexploder.model.impl.BasicModelFactory;
import designexploder.util.adt.ADTUtil;
import designexploder.util.adt.Condition;
import designexploder.util.adt.IdUtil;
import designexploder.util.adt.IdUtil.ID;

import static designexploder.model.extension.IoC.IoCModelNatures.*;

public class IoCModelUtil {

	public static Node createClassNodeCopy(Node node, String newId) {
		Node newNode = BasicModelFactory.getInstance().createModelCopy(node, newId, true);
		if(newNode.getExtension(BeanNode.class) != null) {
			newNode.removeExtension(BeanNode.class);
            newNode.removeExtension(NodeDesignProperties.class);
		}
		Set<Connection> offensiveConnection = new HashSet<Connection>();
		for (Connection connection: newNode.getOutflows()) {
			if(!hasAppropiateNatures(connection)) {
				offensiveConnection.add(connection);
			}
		}
		for (Connection connection : offensiveConnection) {
			newNode.removeOutflow(connection);
		}
		offensiveConnection.clear();
		for (Connection connection: newNode.getInflows()) {
			if(!hasAppropiateNatures(connection)) {
				offensiveConnection.add(connection);
			}
		}
		for (Connection connection : offensiveConnection) {
			newNode.removeInflow(connection);
		}
		return newNode;
	}

	private static boolean hasAppropiateNatures(Connection connection) {
		if(connection.getExtension(ClassRelation.class) == null) {
			return false;
		} else if(connection.getExtension(BeanInjection.class) != null) {
			connection.removeExtension(BeanInjection.class);
		}
		return true;
	}

	public static Dependency findDependencyFromClassItem(ClassItem target, Node node) {
		BeanNode beanNode = node.getExtension(BeanNode.class);
		if(beanNode != null) {
			for (Dependency dependency : beanNode.getDependencies()) {
				if(dependency.getTarget() == target) {
					return dependency;
				}
			}
		}
		return null;
	}

    public static Node getUniqueScopedBean(Type type, NodeContainer nodeContainer) {
        Node found = null;
        if(type.isClassType()) {
            ClassType classType = type.asClassType();
            Set<Node> facadeNodes = new HashSet<Node>();
            IoCModelUtil.addFacadeBeans(nodeContainer, facadeNodes);
            for(Node facade : facadeNodes) {
                Type facadeType = facade.getExtension(ClassNode.class).getType();
                if(facadeType.isClassType() && ClassModelUtil.isSubclass(facadeType.asClassType(), classType)) {
                    if(found == null) {
                        found = facade;
                    } else {
                        found = null;
                        break;
                    }
                }
            }
        }
        return found;
    }

    public static boolean isContextInstantiateMethod(Method method, NodeContainer nodeContainer) {
        return method.isAbstract() && !method.isStatic() && !method.isFinal() && !method.getType().isVoid()
                && method.getParameters().isEmpty() && IoCModelUtil.getUniqueScopedBean(method.getType(), nodeContainer) != null;
    }

    public static boolean isFactoryCandidate(Method method, Node node) {
        Type methodType = method.getType();
        Type nodeType = node.getExtension(ClassNode.class).getType();
        return !method.isAbstract() && method.isStatic() && methodType.isClassType() && nodeType.isClassType() &&
					ClassModelUtil.isSubclass(nodeType.asClassType(), methodType.asClassType());
	}

	public static boolean isBeanCycleMethod(Method method) {
		return !method.isAbstract() && !method.isStatic() && method.getParameters().isEmpty() && method.getType().isVoid();
	}

    public  static boolean isReplaceableMethod(Method method) {
        return method.isAbstract() && !method.isStatic() && !method.isFinal() && method.getType().isVoid() && method.getParameters().isEmpty();
    }

	public static IoCAwareMethod findIoCAwareMethodFromMethod(Method target, BeanNode beanNode) {
		if(beanNode != null) {
			for (IoCAwareMethod iocAwareMethod : beanNode.getIoCAwareMethods()) {
				if(iocAwareMethod.getTarget() == target) {
					return iocAwareMethod;
				}
			}
		}
		return null;
	}

    public static boolean isInsideContext(Node node) {
        return node.getNodeContainer() instanceof Node && node.getNodeContainer().getExtension(ApplicationContext.class) != null;
    }

	public static IoCModelNatures getBeanNatureFor(ClassNode classNode, BeanNode beanNode) {
		return getBeanNatureFor(classNode, beanNode, null);
	}

	public static IoCModelNatures getBeanNatureFor(ClassNode classNode, final BeanNode beanNode, ID id) {
		IoCModelNatures result;
		if(classNode.getNature() == ClassModelNatures.INTERFACE) {
			result = IoCModelNatures.BEAN_AUTO;
		} else if(classNode.getNature() == ClassModelNatures.ENUM){
			result = IoCModelNatures.BEAN_FACTORY;
		} else if(id != null && id.type == IdUtil.FACADE_TYPE) {
			result = IoCModelNatures.BEAN_FACADE;
		} else if(classNode.getAttributes().isEmpty()) {
			result = IoCModelNatures.BEAN_STATELESS;
		} else if(ADTUtil.filterCollection(classNode.getMethods(), new Condition<Method>() {
				public boolean check(Method method) {
					return method.isAbstract() && IoCModelUtil.findIoCAwareMethodFromMethod(method, beanNode) == null;
				}
			}).isEmpty()) {
			result = IoCModelNatures.BEAN;
		} else {
			result = IoCModelNatures.BEAN_AUTO;
		}
		return result;
	}

	public static Set<Node> addFacadeBeans(NodeContainer container, Set<Node> availableBeansAndFacades) {
		for (Node node : container.getNodes()) {
			if(node instanceof NodeContainer) {
				availableBeansAndFacades.addAll(ADTUtil.filterCollection(((NodeContainer) node).getNodes(), new Condition<Node>() {
					@Override
					public boolean check(Node node) {
						BeanNode beanNode = node.getExtension(BeanNode.class);
						return beanNode != null && beanNode.getNature() == IoCModelNatures.BEAN_FACADE;
					}
				}));
			}
		}
		return availableBeansAndFacades;
	}

    public static Nature getCollectionDependencyNature(Node node, Set<Node> candidates) {
        return (candidates.size() == 1
                && getDependencyNature(node, candidates.iterator().next()) == INJECTION_PROXY) ?
                INJECTION_PROXIES_COLLECTION : INJECTION_COLLECTION;
    }

    public static Nature getDependencyNature(Node node, Node candidate) {
		// if(node == candidate) return INJECTION_TREE; TODO: Tree flavor is disabled
		NodeContainer candidateContext = candidate.getNodeContainer();
		return  candidateContext instanceof Node && ((Node) candidateContext).getNodeContainer() == node.getNodeContainer() ?
					INJECTION_PROXY : INJECTION_BEAN;
	}
}
