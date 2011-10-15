package designexploder.model.extension.IoC.impl.spring;

import java.io.ByteArrayInputStream;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

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
import designexploder.model.extension.IoC.ApplicationContext;
import designexploder.model.extension.IoC.BeanNode;
import designexploder.model.extension.IoC.Dependency;
import designexploder.model.extension.IoC.IoCModelUtil;
import designexploder.model.extension.IoC.impl.spring.parsing.BeanElement;
import designexploder.model.extension.IoC.impl.spring.parsing.BeansElement;
import designexploder.model.extension.IoC.impl.spring.parsing.DependencyElement;
import designexploder.model.extension.IoC.impl.spring.parsing.SpringConfigFile;
import designexploder.model.extension.classnode.ClassNode;
import designexploder.util.EclipseUtil;
import designexploder.util.adt.ADTUtil;

public class SpringContextsWriter implements ModelBuilder {

	private static final String AUTOWIRE = "autowire";
	private final IPackageFragmentRoot contextsFragmentRoot;
	private final IJavaProject project;

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
		saveContext(diagram, new HashSet<Node>());
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

	private void saveContext(NodeContainer container, HashSet<Node> availableBeans) {
		ApplicationContext context = container.getExtension(ApplicationContext.class);
		if(context != null) {
			String id = container.getId();
			IFile file = EclipseUtil.createFileHandlerFromId((IContainer) contextsFragmentRoot.getResource(), id);
			Set<Node> beanNodes = ADTUtil.createSet(BasicModelUtil.getExtendedNodes(container, BeanNode.class).iterator());
			availableBeans.addAll(beanNodes);
			Set<Node> availableBeansAndFacades = IoCModelUtil.addFacadeBeans(container, new HashSet<Node>(availableBeans));
			BeansElement beansElement = new BeansElement();
			for (Node node : beanNodes) {
				beansElement.appendChild(createBeanNode(node, availableBeans));
			}
			SpringConfigFile springConfigFile = new SpringConfigFile();
			springConfigFile.setRootElement(beansElement);
			EclipseUtil.createAndWriteFile(file, springConfigFile.toPrettyXML());
		}
		// Continue with children contexts
		for(NodeContainer child : BasicModelUtil.getContainerNodes(container)) {
			saveContext(child, new HashSet<Node>(availableBeans));
		}
	}

	private BeanElement createBeanNode(Node node, HashSet<Node> availableBeans) {
		// Model Values
		BeanElement beanElement = new BeanElement();
		ClassNode classNode = node.getExtension(ClassNode.class);
		BeanNode beanNode = node.getExtension(BeanNode.class);

		// Set Node Data
		beanElement.setId(node.getId());
        beanElement.setName(beanNode.getName());
		beanElement.setClazz(classNode.getType().getName());
        beanElement.setAutowireByType();
		
		// Set Injections 
		for (Dependency dependency : beanNode.getDependencies()) {
			beanElement.appendChild(createDependencyElement(dependency));
		}
		
		return beanElement;
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
