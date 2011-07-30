package designexploder.model.extension.IoC.impl.spring;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.jdt.core.IJavaProject;

import designexploder.model.Node;
import designexploder.model.NodeContainer;
import designexploder.model.extension.IoC.BeanNode;
import designexploder.model.extension.IoC.Dependency;
import designexploder.model.extension.IoC.IoCModelNatures;
import designexploder.model.extension.IoC.impl.IoCModelFactory;
import designexploder.model.extension.IoC.impl.spring.parsing.BeanElement;
import designexploder.model.extension.IoC.impl.spring.parsing.DependencyElement;
import designexploder.model.extension.IoC.impl.spring.parsing.SpringConfigFile;
import designexploder.model.extension.classnode.ClassNode;
import designexploder.model.extension.classnode.Method;
import designexploder.model.impl.BasicModelFactory;
import designexploder.util.adt.IdUtil;
import designexploder.util.adt.IterableIterator;
import designexploder.util.adt.IdUtil.ID;

public class SpingBeansModelFactory {

	@SuppressWarnings("unused")
	private final IJavaProject project;

	public SpingBeansModelFactory(IJavaProject project) {
		this.project = project;
	}

	public Set<Node> getBeans(NodeContainer container, SpringConfigFile source, NodeContainer diagram) {
		Set<Node> result = new HashSet<Node>();
		for (BeanElement element : source.getBeans()) {
			IdUtil.ID id = IdUtil.parseId(element.getId());
			if(id != null && diagram.findNode(id.toString()) == null) {
				ID classNodeId = IdUtil.createClassId(id.name);
				Node classNode = classNodeId != null ? diagram.findNode(classNodeId.toString()) : null;
				if(classNode != null) {
					Node node = BasicModelFactory.getInstance().createModelCopy(classNode, id.toString(), true);
					node.addExtension(BeanNode.class, createBeanNode(element, node.getExtension(ClassNode.class)));
					result.add(node);
				}
			}
		}
		return result;
	}

	private BeanNode createBeanNode(BeanElement element, ClassNode clazz) {
		BeanNode bean = IoCModelFactory.getInstance().createBeanNode();
		String name = element.getName();
		bean.setName(name != null ? name : element.getId());
		bean.setNature(IoCModelNatures.COMMON_BEAN);
		for (DependencyElement dependencyElement : new IterableIterator<DependencyElement>(element.getDependencies())) {
			Dependency dependency = createDependency(dependencyElement, clazz);
			if(dependency != null) {
				bean.addDependency(dependency);
			}
		}
		return bean;
	}

	private Dependency createDependency(DependencyElement element, ClassNode clazz) {
		String property = element.getName().intern();
		Dependency result = null;
		if(property != null) {
			for (Method method : clazz.getMethods()) {
				if(method.isSetter() && method.getProperty() == property) {
					result = IoCModelFactory.getInstance().createDependency();
					result.setTarget(method);
					break;
				}
			}
		}
		return result;
	}

}
