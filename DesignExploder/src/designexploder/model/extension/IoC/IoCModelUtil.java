package designexploder.model.extension.IoC;

import java.util.HashSet;
import java.util.Set;

import designexploder.model.Connection;
import designexploder.model.Node;
import designexploder.model.NodeContainer;
import designexploder.model.extension.classnode.ClassItem;
import designexploder.model.extension.classnode.ClassModelNatures;
import designexploder.model.extension.classnode.ClassNode;
import designexploder.model.extension.classnode.ClassRelation;
import designexploder.model.extension.classnode.Method;
import designexploder.model.impl.BasicModelFactory;
import designexploder.util.adt.ADTUtil;
import designexploder.util.adt.Condition;
import designexploder.util.adt.IdUtil;
import designexploder.util.adt.IdUtil.ID;

public class IoCModelUtil {

	public static Node createClassNodeCopy(Node node, String newId) {
		Node newNode = BasicModelFactory.getInstance().createModelCopy(node, newId, true);
		if(newNode.getExtension(BeanNode.class) != null) {
			newNode.removeExtension(BeanNode.class);
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
}
