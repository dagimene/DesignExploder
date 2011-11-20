package designexploder.model.extension.IoC.impl.spring;

import java.util.*;

import designexploder.model.*;
import designexploder.model.extension.IoC.*;
import designexploder.model.extension.IoC.impl.spring.parsing.*;
import designexploder.model.extension.classnode.ClassModelUtil;
import designexploder.model.extension.classnode.ClassType;
import designexploder.model.extension.classnode.Type;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;

import designexploder.model.build.ModelBuilder;
import designexploder.model.extension.classnode.ClassNode;
import designexploder.util.EclipseUtil;
import designexploder.util.adt.ADTUtil;

public class SpringContextsWriter implements ModelBuilder {

	private static final String AUTOWIRE = "autowire";
	private final IPackageFragmentRoot contextsFragmentRoot;
	private final IJavaProject project;
    private Set<IoCModelNatures> replaceMethods = new HashSet<IoCModelNatures>();

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
            for(IoCModelNatures nature : replaceMethods) {
                beansElement.appendChild(new ContextMethodsReplacerElement("::" + nature.name()));
            }
            replaceMethods.clear();
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
        //beanElement.setName(beanNode.getName());
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
        //beanElement.setName(beanNode.getName());
		beanElement.setClazz(classNode.getType().getName());

        addIoCInitMethod(beanElement, beanNode);
        addIoCFinalizeMethods(beanElement, beanNode);

        addIoCReplaceMethods(beanElement, node, beanNode);

        beanElement.setAutowireByType();

		// Set Injections 
		for (Dependency dependency : beanNode.getDependencies()) {
			beanElement.appendChild(createDependencyElement(dependency));
		}
		
		return beanElement;
	}

    private void addIoCReplaceMethods(BeanElement beanElement, Node node, BeanNode beanNode) {
        for(IoCAwareMethod ioCAwareMethod : beanNode.getIoCAwareMethods()) {
            IoCModelNatures nature = (IoCModelNatures)ioCAwareMethod.getNature();
            switch (nature) {
                case IOC_METHOD_INSTANTIATE:
                    Node facadeBean = IoCModelUtil.getUniqueScopedBean(ioCAwareMethod.getTarget().getType(), node.getNodeContainer());
                    if(facadeBean != null) {
                        beanElement.appendChild(ReplaceMethodElement.create(ioCAwareMethod.getTarget().getName(),
                                extractScopeName(facadeBean.getNodeContainer().getId())));
                    }
                    break;
                case IOC_METHOD_ACTIVATE:
                case IOC_METHOD_DESTROY:
                    beanElement.appendChild(ReplaceMethodElement.create(ioCAwareMethod.getTarget().getName(),
                            nature));
                    replaceMethods.add(nature);
            }
        }
    }

    private void addIoCInitMethod(BeanElement beanElement, BeanNode beanNode) {
        for(IoCAwareMethod ioCAwareMethod : beanNode.getIoCAwareMethods()) {
            if(ioCAwareMethod.getNature() == IoCModelNatures.IOC_METHOD_INIT) {
                beanElement.setInitMethod(ioCAwareMethod.getTarget().getName());
                break ; // Only one init-method is supported
            }
        }
    }

    private void addIoCFinalizeMethods(BeanElement beanElement, BeanNode beanNode) {
        for(IoCAwareMethod ioCAwareMethod : beanNode.getIoCAwareMethods()) {
            if(ioCAwareMethod.getNature() == IoCModelNatures.IOC_METHOD_FINALIZE) {
                beanElement.setFinalizeMethod(ioCAwareMethod.getTarget().getName());
                break ; // Only one destroy-method is supported
            }
        }
    }

    private DependencyElement createDependencyElement(Dependency dependency) {
		DependencyElement dependencyElement = new DependencyElement();
		dependencyElement.setName(dependency.getName());

        boolean resolved = false;
        /* Unresolved collection injections will be autowired as usual. The expected result is to get
         * the available instances (maybe a proxy) in the list, but not all the available instances
         * taken from a children scope.
         */
        if(dependency.getNature() == IoCModelNatures.INJECTION_PROXIES_COLLECTION) {
            resolved = tryInjectingDexScopedBeansList(dependency, dependencyElement);
            /* If a INJECTION_PROXIES_COLLECTION natured dependency fails to be injected, AUTOWIRE property will
             * be used. This configuration will possibly fail at runtime, but will be preserved at the xml.
             */
        }
        if(!resolved) {
		    dependencyElement.setValueProperty(AUTOWIRE);
        }
		return dependencyElement;
	}

    /**
     * Tries satisfying a the dependency with a DexScopedBeansList.
     * @param dependency
     * @param dependencyElement
     * @return if the dependency could be satisfied.
     */
    private boolean tryInjectingDexScopedBeansList(Dependency dependency, DependencyElement dependencyElement) {
        if(dependency.isResolved() && dependency.getBeanInjections().size() == 1) {
            Type dependencyType = ClassModelUtil.getClassItemType(dependency.getTarget());
            if(dependencyType.isClassType()) {
                ClassType classType = dependencyType.asClassType();
                List<Type> typeParameters = classType.getTypeParameters();
                if(typeParameters.size() == 1 && ClassModelUtil.isAssignableFromWellKnownCollection(classType)) {
                    Type typeParameter = typeParameters.get(0);
                    Connection targetConnection = dependency.getBeanInjections().iterator().next();
                    NodeContainer targetNodeContainer = targetConnection.getTarget().getNodeContainer();
                    String scopeName = extractScopeName(targetNodeContainer.getId());

                    dependencyElement.appendChild(new DexScopedBeansListElement(scopeName, typeParameter.getName()));
                    return true;
                }
            }
        }
        return false;
    }

}
