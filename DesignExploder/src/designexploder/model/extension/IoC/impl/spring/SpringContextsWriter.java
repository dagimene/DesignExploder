package designexploder.model.extension.IoC.impl.spring;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import designexploder.model.ContainerNode;
import designexploder.model.extension.IoC.*;
import designexploder.model.extension.IoC.impl.spring.parsing.*;
import designexploder.model.extension.common.Nature;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;

import designexploder.model.BasicModelUtil;
import designexploder.model.Node;
import designexploder.model.NodeContainer;
import designexploder.model.build.ModelBuilder;
import designexploder.model.extension.classnode.ClassNode;
import designexploder.util.EclipseUtil;
import designexploder.util.adt.ADTUtil;

public class SpringContextsWriter implements ModelBuilder {

	private static final String AUTOWIRE = "autowire";
	private final IPackageFragmentRoot contextsFragmentRoot;
	private final IJavaProject project;
    private boolean hasReplaceMethods;

	public static ModelBuilder create(IPackageFragmentRoot packageFragmentRoot) {
		return new SpringContextsWriter(packageFragmentRoot.getJavaProject(), packageFragmentRoot);
	}

	public SpringContextsWriter(IJavaProject project, IPackageFragmentRoot contextsFragmentRoot) {
		this.project = project;
		this.contextsFragmentRoot = contextsFragmentRoot;
	}

	@Override
	public NodeContainer build(NodeContainer diagram) {
		clearContexts();
		saveContext(diagram/*, new HashSet<Node>()*/);
		return null;
	}

	private void clearContexts() {
		try {
			IFolder sourceFolder = (IFolder) contextsFragmentRoot.getResource();
			for (IFile file : EclipseUtil.getFiles(sourceFolder, Collections.singleton("xml"))) {
				file.delete(true, null); // Keep history
			}
			sourceFolder.delete(true, null);
			sourceFolder.create(true, true, null);
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

	private void saveContext(NodeContainer container/*, HashSet<Node> availableBeans*/) {
		ApplicationContext context = container.getExtension(ApplicationContext.class);
		if(context != null) {
			String id = container.getId();
			IFile file = EclipseUtil.createFileHandlerFromId((IContainer) contextsFragmentRoot.getResource(), id);
			Set<Node> beanNodes = ADTUtil.createSet(BasicModelUtil.getExtendedNodes(container, BeanNode.class).iterator());
            Set<Node> facadeNodes = new HashSet<Node>();
            IoCModelUtil.addFacadeBeans(container, facadeNodes);
			/*availableBeans.addAll(beanNodes);*/
			/*Set<Node> availableBeansAndFacades = IoCModelUtil.addFacadeBeans(container, new HashSet<Node>(availableBeans));*/
			BeansElement beansElement = new BeansElement();
			for (Node node : beanNodes) {
				beansElement.appendChild(createBeanNode(node/*, availableBeans*/));
			}
            for(Node node : facadeNodes) {
                beansElement.appendChild(createFacadeNode(node));
            }
            Iterator<ContainerNode> scopes = BasicModelUtil.getContainerNodes(container).iterator();
            if(scopes.hasNext()) {
                CustomScopeConfigurerElement customScopeConfigurer = new CustomScopeConfigurerElement();
                do {
                    String key = scopes.next().getId();
                    String name = extractScopeName(key);
                    customScopeConfigurer.declareScope(name, key);
                    beansElement.appendChild(new DexContextScopeImplElement(name, key));
                } while(scopes.hasNext());
                beansElement.appendChild(customScopeConfigurer);
            }
            if(hasReplaceMethods) {
                beansElement.appendChild(new ContextMethodsReplacerElement());
            }
            hasReplaceMethods = false;
			SpringConfigFile springConfigFile = new SpringConfigFile();
			springConfigFile.setRootElement(beansElement);
			EclipseUtil.createAndWriteFile(file, springConfigFile.toPrettyXML());
		}
		// Continue with children contexts
        for(NodeContainer child : BasicModelUtil.getContainerNodes(container)) {
			saveContext(child/*, new HashSet<Node>(availableBeans)*/);
		}
	}

    private String extractScopeName(String key) {
        return ":::" + key.substring("ctx://".length(), key.length() - ".xml".length()).toLowerCase();
    }

    private BeanElement createFacadeNode(Node node) {
		// Model Values
		BeanElement beanElement = new BeanElement();
		ClassNode classNode = node.getExtension(ClassNode.class);
		BeanNode beanNode = node.getExtension(BeanNode.class);

		// Set Node Data
		beanElement.setId(node.getId());
        beanElement.setName(beanNode.getName());
		beanElement.setClazz(classNode.getType().getName());
        beanElement.setScope(node.getNodeContainer().getId());
        beanElement.appendChild(new ScopedProxyElement());

		return beanElement;
    }

    private BeanElement createBeanNode(Node node/*, HashSet<Node> availableBeans*/) {
		// Model Values
		BeanElement beanElement = new BeanElement();
		ClassNode classNode = node.getExtension(ClassNode.class);
		BeanNode beanNode = node.getExtension(BeanNode.class);

		// Set Node Data
		beanElement.setId(node.getId());
        beanElement.setName(beanNode.getName());
		beanElement.setClazz(classNode.getType().getName());

        addIoCInitMethods(beanElement, beanNode);

        hasReplaceMethods = hasReplaceMethods || addIoCReplaceMethods(beanElement, beanNode);

        beanElement.setAutowireByType();

		// Set Injections 
		for (Dependency dependency : beanNode.getDependencies()) {
			beanElement.appendChild(createDependencyElement(dependency));
		}
		
		return beanElement;
	}

    private boolean addIoCReplaceMethods(BeanElement beanElement, BeanNode beanNode) {
        boolean hasReplaceMethods = false;
        for(IoCAwareMethod ioCAwareMethod : beanNode.getIoCAwareMethods()) {
            IoCModelNatures nature = (IoCModelNatures)ioCAwareMethod.getNature();
            switch (nature) {
                case IOC_METHOD_INSTANTIATE:
                case IOC_METHOD_ACTIVATE:
                case IOC_METHOD_DESTROY:
                    beanElement.appendChild(ReplaceMethodElement.create(ioCAwareMethod.getTarget().getName()));
                    hasReplaceMethods = true;
            }
        }
        return hasReplaceMethods;
    }

    private void addIoCInitMethods(BeanElement beanElement, BeanNode beanNode) {
        for(IoCAwareMethod ioCAwareMethod : beanNode.getIoCAwareMethods()) {
            if(ioCAwareMethod.getNature() == IoCModelNatures.IOC_METHOD_INIT) {
                beanElement.setInitMethod(ioCAwareMethod.getTarget().getName());
                break ; // Only one init-method is supported
            }
        }
    }

    private DependencyElement createDependencyElement(Dependency dependency) {
		DependencyElement dependencyElement = new DependencyElement();
		dependencyElement.setName(dependency.getName());
		/*if(dependency.isResolved()) {
			if(dependency.getNature() == IoCModelNatures.INJECTION_COLLECTION) {
				Type dependencyType = ClassModelUtil.getClassItemType(dependency.getTarget());
				if(dependencyType.isClassType()) {
					CollectionElement collection = new CollectionElement(ClassModelUtil.isList((ClassType) dependencyType));
					Set<Connection> beanInjections = dependency.getBeanInjections();
					for (Connection connection : beanInjections) {
						RefElement ref = new RefElement();
						ref.setBean(connection.getTarget().getId());
						collection.appendChild(ref);
					}
					dependencyElement.appendChild(collection);
				}
			} else { // BEAN, PROXY or TREE
				Set<Connection> beanInjections = dependency.getBeanInjections();
				dependencyElement.setRef(beanInjections.iterator().next().getTarget().getId());
			}
		}*/
		dependencyElement.setValueProperty(AUTOWIRE);
		return dependencyElement;
	}

}
