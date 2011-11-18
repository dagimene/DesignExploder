package designexploder.model.extension.IoC.impl.spring;

import java.util.*;

import designexploder.model.*;
import designexploder.model.extension.IoC.*;
import designexploder.model.extension.IoC.impl.spring.parsing.*;
import designexploder.model.extension.classnode.ClassModelUtil;
import designexploder.model.extension.classnode.ClassType;
import designexploder.model.extension.classnode.Type;
import designexploder.model.extension.classnode.impl.ParameterizedClassTypeImpl;
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

        addIoCInitMethods(beanElement, beanNode);

        hasReplaceMethods = addIoCReplaceMethods(beanElement, beanNode) || hasReplaceMethods;

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

        boolean resolved = false;
        /* Unresolved collection injections will be autowired as usual. The expected result is to get
         * the available instances (maybe a proxy) in the list, but not all the available instances
         * taken from a children scope.
         */
        if(dependency.getNature() == IoCModelNatures.INJECTION_COLLECTION) {
            resolved = tryInjectingDexScopedBeansList(dependency, dependencyElement);
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
                if(typeParameters.size() == 1 && (ClassModelUtil.isAssignableFromList(classType) ||
                        ClassModelUtil.isAssignableFromSet(classType))) {
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
		/*if() {
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
    }

}
