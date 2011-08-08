package designexploder.model.extension.IoC.impl.spring;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.jdt.core.IJavaProject;

import designexploder.model.Node;
import designexploder.model.NodeContainer;
import designexploder.model.extension.IoC.BeanNode;
import designexploder.model.extension.IoC.Dependency;
import designexploder.model.extension.IoC.IoCModelUtil;
import designexploder.model.extension.IoC.impl.IoCModelFactory;
import designexploder.model.extension.IoC.impl.spring.parsing.BeanElement;
import designexploder.model.extension.IoC.impl.spring.parsing.DependencyElement;
import designexploder.model.extension.IoC.impl.spring.parsing.SpringConfigFile;
import designexploder.model.extension.classnode.Attribute;
import designexploder.model.extension.classnode.ClassNode;
import designexploder.model.extension.classnode.Method;
import designexploder.model.impl.BasicModelFactory;
import designexploder.util.adt.IdUtil;
import designexploder.util.adt.IterableIterator;
import designexploder.util.adt.IdUtil.ID;

public class SpringBeansModelFactory {

	@SuppressWarnings("unused")
	private final IJavaProject project;

	public SpringBeansModelFactory(IJavaProject project) {
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
					BeanNode beanNode = createBeanNode(element, node.getExtension(ClassNode.class), id);
					beanNode.setNode(node);
					node.addExtension(beanNode);
					result.add(node);
				}
			}
		}
		return result;
	}

	private BeanNode createBeanNode(BeanElement element, ClassNode clazz, ID id) {
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
		return bean;
	}

	private Dependency createDependency(DependencyElement element, ClassNode clazz) {
		String property = element.getName();
		if(property != null) {
			property = property.intern();
			/*
			 * TODO: add recursive search in superclass methods.
			 * Add class reference to method model, and method to superclass.
			 * Same for attributes.
			 */
			// TODO: Handle collections at this point? Only if spring has a special handling...
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