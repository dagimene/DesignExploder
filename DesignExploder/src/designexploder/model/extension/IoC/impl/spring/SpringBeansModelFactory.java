package designexploder.model.extension.IoC.impl.spring;

import java.util.*;

import designexploder.model.extension.IoC.*;
import designexploder.model.extension.IoC.build.IoCModelDependenciesProcessor;
import designexploder.model.extension.IoC.impl.spring.parsing.ReplaceMethodElement;
import org.eclipse.jdt.core.IJavaProject;

import designexploder.model.Node;
import designexploder.model.NodeContainer;
import designexploder.model.extension.IoC.impl.IoCModelFactory;
import designexploder.model.extension.IoC.impl.spring.parsing.BeanElement;
import designexploder.model.extension.IoC.impl.spring.parsing.DependencyElement;
import designexploder.model.extension.IoC.impl.spring.parsing.SpringConfigFile;
import designexploder.model.extension.classnode.Attribute;
import designexploder.model.extension.classnode.ClassNode;
import designexploder.model.extension.classnode.Method;
import designexploder.model.impl.BasicModelFactory;
import designexploder.util.adt.FindNextIterator;
import designexploder.util.adt.IdUtil;
import designexploder.util.adt.IterableIterator;
import designexploder.util.adt.IdUtil.ID;

public class SpringBeansModelFactory {

	@SuppressWarnings("unused")
	private final IJavaProject project;

	public SpringBeansModelFactory(IJavaProject project) {
		this.project = project;
	}

	/**
	 * Nodes must be added to the container as they are returned.
	 * @param container
	 * @param source
	 * @param diagram
	 * @return
	 */
	public Iterable<Node> getBeans(NodeContainer container, SpringConfigFile source, final NodeContainer diagram) {
        final Map<String, Set<String>> factoryMethods = new HashMap<String, Set<String>>();
		final Iterator<BeanElement> beanElements = source.getBeans().iterator();
		return new IterableIterator<Node>(new FindNextIterator<Node>() {
			protected Node findNext() {
				Node result = null;
				while(result == null && beanElements.hasNext()) {
                    result = createBeanNode(beanElements.next(), diagram, factoryMethods);
				}
				return result;
			}
		});
	}

    private Node createBeanNode(BeanElement element, NodeContainer diagram, Map<String, Set<String>> factoryMethods) {
        Node result = null;
        ID id = IdUtil.parseId(element.getId());
        if(id != null && diagram.findNode(id.toString()) == null) {
            if(id.type == IdUtil.FACADE_TYPE && element.getAopProxy() != null) {
                return null;
            }
            ID classNodeId = IdUtil.createClassId(id.name);
            Node classNode = classNodeId != null ? diagram.findNode(classNodeId.toString()) : null;
            if(classNode != null) {
                Node node = BasicModelFactory.getInstance().createModelCopy(classNode, id.toString(), true);
                BeanNode beanNode = createBeanNode(node, element, node.getExtension(ClassNode.class), id);
                beanNode.setNode(node);
                node.addExtension(beanNode);
                result = node;
            }
        }

        registerBeanFactory(element, diagram, factoryMethods);

        if(result != null) {
            addPendingFactoryMethods(factoryMethods, result);
        }

        return result;
    }

    private void addPendingFactoryMethods(Map<String, Set<String>> factoryMethods, Node result) {
        Set<String> nodeFactoryMethods = factoryMethods.get(result.getId());
        if(nodeFactoryMethods != null && !factoryMethods.isEmpty()) {
            for (Method method : result.getExtension(ClassNode.class).getMethods()) {
                if(IoCModelUtil.isFactoryCandidate(method, result) && nodeFactoryMethods.contains(method.getName())) {
                    IoCAwareMethod iocAwareMethod = IoCModelFactory.getInstance().createIoCAwareMethod(IoCModelNatures.IOC_METHOD_FACTORY);
                    iocAwareMethod.setTarget(method);
                    result.getExtension(BeanNode.class).addIoCAwareMethod(iocAwareMethod);
                }
            }
        }
        factoryMethods.remove(result.getId());
    }

    private void registerBeanFactory(BeanElement element, NodeContainer diagram, Map<String, Set<String>> factoryMethods) {
        String factoryBean = element.getFactoryBean();
        String factoryMethod = element.getFactoryMethod();
        if(factoryBean != null && factoryMethod != null) {
            Node factoryNode = diagram.findNode(factoryBean);
            if(factoryNode != null) {
                for (Method method : factoryNode.getExtension(ClassNode.class).getMethods()) {
                    if(IoCModelUtil.isFactoryCandidate(method, factoryNode) && method.getName().equals(factoryMethod)) {
                        IoCAwareMethod iocAwareMethod = IoCModelFactory.getInstance().createIoCAwareMethod(IoCModelNatures.IOC_METHOD_FACTORY);
                        iocAwareMethod.setTarget(method);
                        factoryNode.getExtension(BeanNode.class).addIoCAwareMethod(iocAwareMethod);
                    }
                }
            } else {
                Set<String> methods = factoryMethods.get(factoryBean);
                if(methods == null) {
                    methods = new HashSet<String>();
                    factoryMethods.put(factoryBean, methods);
                }
                methods.add(factoryMethod);
            }
        }
    }

    private BeanNode createBeanNode(Node node, BeanElement element, ClassNode clazz, ID id) {
        BeanNode bean = IoCModelFactory.getInstance().createBeanNode();
        String name = element.getName();
        bean.setName(name != null ? name : element.getId());
        for (DependencyElement dependencyElement : new IterableIterator<DependencyElement>(element.getDependencies())) {
            Dependency dependency = createDependency(dependencyElement, clazz);
            if(dependency != null) {
                bean.addDependency(dependency);
            }
        }
        bean.setNature(IoCModelUtil.getBeanNatureFor(clazz, bean, id));
        createInitMethod(element, clazz, bean);
        createFinalizeMethod(element, clazz, bean);
        createIoCAwareMethods(node, element, clazz, bean);
        return bean;
    }

    private void createIoCAwareMethods(Node node, BeanElement element, ClassNode clazz, BeanNode bean) {
        Iterator<ReplaceMethodElement> replaceMethods = element.getReplaceMethods();
        while(replaceMethods.hasNext()) {
            ReplaceMethodElement replaceMethod = replaceMethods.next();
            String replacer = replaceMethod.getReplacer();
            if(replacer != null) {
                IoCModelNatures ioCAwareMethodNature = null;
                Method ioCAwareMethod = null;
                if (replacer.startsWith(":::")) {
                    ioCAwareMethodNature = IoCModelNatures.IOC_METHOD_INSTANTIATE;
                    ioCAwareMethod = findContextInstantiateMethod(clazz, node.getNodeContainer(), replaceMethod.getName());
                } else {
                    if (replacer.equals("::" + IoCModelNatures.IOC_METHOD_ACTIVATE.name())) {
                        ioCAwareMethodNature = IoCModelNatures.IOC_METHOD_ACTIVATE;
                    } else if (replacer.equals("::" + IoCModelNatures.IOC_METHOD_DESTROY.name())) {
                        ioCAwareMethodNature = IoCModelNatures.IOC_METHOD_DESTROY;
                    }
                    if (ioCAwareMethodNature != null) {
                        ioCAwareMethod = findReplaceableMethod(clazz, replaceMethod.getName());
                    }
                }
                if (ioCAwareMethod != null) {
                    addIoCAwareMethod(bean, ioCAwareMethod, ioCAwareMethodNature);
                }
            }
        }
    }

    private void createInitMethod(BeanElement element, ClassNode clazz, BeanNode bean) {
        String initMethod = element.getInitMethod();
        if(initMethod != null) {
            Method ioCAwareMethod = findBeanCycleMethod(clazz, initMethod);
            if(ioCAwareMethod != null) {
                addIoCAwareMethod(bean, ioCAwareMethod, IoCModelNatures.IOC_METHOD_INIT);
            }
        }
    }

    private void createFinalizeMethod(BeanElement element, ClassNode clazz, BeanNode bean) {
        String finalizeMethod = element.getFinalizeMethod();
        if(finalizeMethod != null) {
            Method ioCAwareMethod = findBeanCycleMethod(clazz, finalizeMethod);
            if(ioCAwareMethod != null) {
                addIoCAwareMethod(bean, ioCAwareMethod, IoCModelNatures.IOC_METHOD_FINALIZE);
            }
        }
    }

    private Method findContextInstantiateMethod(ClassNode clazz, NodeContainer nodeContainer, String methodName) {
        Method result = null;
        for(Method method : clazz.getMethods()) {
            if(method.getName().equals(methodName) && IoCModelUtil.isContextInstantiateMethod(method, nodeContainer)) {
                result = method;
                break;
            }
        }
        return result;
    }

    private Method findBeanCycleMethod(ClassNode clazz, String methodName) {
        Method result = null;
        for(Method method : clazz.getMethods()) {
            if(method.getName().equals(methodName) && IoCModelUtil.isBeanCycleMethod(method)) {
                result = method;
                break;
            }
        }
        return result;
    }

    private Method findReplaceableMethod(ClassNode clazz, String methodName) {
        Method result = null;
        for(Method method : clazz.getMethods()) {
            if(method.getName().equals(methodName) && IoCModelUtil.isReplaceableMethod(method)) {
                result = method;
                break;
            }
        }
        return result;
    }

    private void addIoCAwareMethod(BeanNode bean, Method method, IoCModelNatures nature) {
        IoCAwareMethod iocAwareMethod = IoCModelFactory.getInstance().createIoCAwareMethod(nature);
        iocAwareMethod.setTarget(method);
        bean.addIoCAwareMethod(iocAwareMethod);
    }

    private Dependency createDependency(DependencyElement element, ClassNode clazz) {
		String property = element.getName();
		if(property != null) {
			property = property.intern();
			// TODO: Analyze setter visibility requirements.
			for (Method method : clazz.getMethods()) {
				if(method.isSetter() && method.getProperty() == property) {
					return IoCModelFactory.getInstance().createDependency(method);
				}
			}
			for (Attribute attribute : clazz.getAttributes()) {
				if(attribute.getName() == property && attribute.getSetter() != null) {
					return IoCModelFactory.getInstance().createDependency(attribute);
				}
			}
		}
		return null;
	}
}
