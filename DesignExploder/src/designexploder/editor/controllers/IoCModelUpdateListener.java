package designexploder.editor.controllers;

import java.util.HashSet;
import java.util.Set;

import designexploder.model.BasicModelUtil;
import designexploder.model.Connection;
import designexploder.model.ModelExtension;
import designexploder.model.Node;
import designexploder.model.NodeContainer;
import designexploder.model.build.ModelBuilder;
import designexploder.model.event.BasicModelEventTypes;
import designexploder.model.event.ModelCollectionAlterEvent;
import designexploder.model.event.ModelEvent;
import designexploder.model.event.ModelEventListener;
import designexploder.model.event.ModelEventTrigger;
import designexploder.model.event.ModelPropertyChangeEvent;
import designexploder.model.extension.IoC.ApplicationContext;
import designexploder.model.extension.IoC.BeanInjection;
import designexploder.model.extension.IoC.BeanNode;
import designexploder.model.extension.IoC.Dependency;
import designexploder.model.extension.IoC.IoCModelEventTypes;
import designexploder.model.extension.IoC.IoCModelNatures;
import designexploder.model.extension.IoC.build.IoCModelDependenciesProcessor;
import designexploder.model.extension.classnode.ClassRelation;
import designexploder.model.extension.common.CommonModelEventTypes;
import designexploder.model.extension.common.Nature;
import designexploder.util.adt.IterableIterator;

public class IoCModelUpdateListener implements ModelEventListener, ModelBuilder {

	private IoCModelDependenciesProcessor processor = new IoCModelDependenciesProcessor();

	@Override
	public NodeContainer build(NodeContainer diagram) {
		installListeners(diagram);
		processor.enrichModel(diagram, new HashSet<Node>());
		return diagram;
	}
	
	public void updateContext(NodeContainer context) {
		clearDependencies(context);
		processor.enrichModel(context, getParentBeans(context));
	}

	private void clearDependencies(NodeContainer context) {
		for (Node node : new IterableIterator<Node>(context.getDeepIterator())) {
			BeanNode beanNode = node.getExtension(BeanNode.class);
			if(beanNode != null) {
				clearDependencies(node, beanNode);
			}
		}
	}

	private void clearDependencies(Node node, BeanNode beanNode) {
		for (Dependency dependency : beanNode.getDependencies()) {
			clearDependency(node, dependency);
		}
	}

	private void clearDependency(Node node, Dependency dependency) {
		// Clear dependency injections
		Set<Connection> beanInjections = dependency.getBeanInjections();
		for (Connection beanInjection : beanInjections) {
			clearInjection(node, beanInjection);
		}
		beanInjections.clear();
		// Reset dependency nature
		dependency.setNature(IoCModelNatures.UNRESOLVED_DEPENDENCY);
	}

	private void clearInjection(Node node, Connection beanInjection) {
		if(beanInjection.getExtension(ClassRelation.class) != null) {
			beanInjection.removeExtension(BeanInjection.class);
		} else {
			BasicModelUtil.removeConnection(beanInjection);
		}
	}

	private Set<Node> getParentBeans(NodeContainer context) {
		Set<Node> availableBeans = new HashSet<Node>();
		while(context instanceof Node) {
			context = ((Node) context).getNodeContainer();
			for (Node node : new IterableIterator<Node>(BasicModelUtil.getExtendedNodes(context, BeanNode.class).iterator())) {
				availableBeans.add(node);
			}
		}
		return availableBeans;
	}

	private void installListeners(NodeContainer container) {
		container.addListener(BasicModelEventTypes.NODE_ADDED, this);
		container.addListener(BasicModelEventTypes.NODE_REMOVED, this);
		for (Node node : container.getNodes()) {
			installListeners(node);
		}
	}

	private void removeListeners(NodeContainer container) {
		for (Node node : container.getNodes()) {
			removeListeners(node);
		}
		container.removeListener(BasicModelEventTypes.NODE_ADDED, this);
		container.removeListener(BasicModelEventTypes.NODE_REMOVED, this);
	}
	
	private void removeListeners(Node node) {
		if(node instanceof NodeContainer) {
			if(node.getExtension(ApplicationContext.class) != null) {
				removeListeners((NodeContainer) node);
			}
			removeListeners((NodeContainer)node);
		} else {
			BeanNode beanNode = node.getExtension(BeanNode.class);
			if(beanNode != null) {
				node.removeListener(BasicModelEventTypes.EXTENSION_REMOVED, this);
				removeListeners(beanNode);
			} else {
				node.removeListener(BasicModelEventTypes.EXTENSION_ADDED, this);
			}
		}
	}

	private void installListeners(Node node) {
		if(node instanceof NodeContainer) {
			if(node.getExtension(ApplicationContext.class) != null) {
				installListeners((NodeContainer) node);
			}
			installListeners((NodeContainer)node);
		} else {
			BeanNode beanNode = node.getExtension(BeanNode.class);
			if(beanNode != null) {
				node.addListener(BasicModelEventTypes.EXTENSION_REMOVED, this);
				installListeners(beanNode);
			} else {
				node.addListener(BasicModelEventTypes.EXTENSION_ADDED, this);
			}
		}
	}
	
	private void installListeners(BeanNode beanNode) {
		beanNode.addListener(CommonModelEventTypes.NATURE_CHANGED, this);
		beanNode.addListener(IoCModelEventTypes.DEPENDENCY_ADDED, this);
		beanNode.addListener(IoCModelEventTypes.DEPENDENCY_REMOVED, this);
		// Not interesting  by the time being...
		// beanNode.addListener(IoCModelEventTypes.IOC_AWARE_METHOD_ADDED, this);
		// beanNode.addListener(IoCModelEventTypes.IOC_AWARE_METHOD_REMOVED, this);
	}
	
	private void removeListeners(BeanNode beanNode) {
		beanNode.removeListener(CommonModelEventTypes.NATURE_CHANGED, this);
		beanNode.removeListener(IoCModelEventTypes.DEPENDENCY_ADDED, this);
		beanNode.removeListener(IoCModelEventTypes.DEPENDENCY_REMOVED, this);
		// Not interesting  by the time being...
		// beanNode.removeListener(IoCModelEventTypes.IOC_AWARE_METHOD_ADDED, this);
		// beanNode.removeListener(IoCModelEventTypes.IOC_AWARE_METHOD_REMOVED, this);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void processModelEvent(ModelEvent e) {
		Node node = updateListeners(e);
		if(node != null) {
			NodeContainer affectedContext = node.getNodeContainer();
			BeanNode beanNode = node.getExtension(BeanNode.class);
			if(beanNode != null && e.getType() == IoCModelEventTypes.DEPENDENCY_REMOVED) {
				Dependency dependency = ((ModelCollectionAlterEvent<Dependency>) e).getElement();
				clearDependency(node, dependency);
			}
			if(beanNode == null && e.getType() == BasicModelEventTypes.EXTENSION_REMOVED) {
				ModelExtension element = ((ModelCollectionAlterEvent<ModelExtension>) e).getElement();
				if(((Class<?>)element.getExtensionClass()) == BeanNode.class) { 
					beanNode = (BeanNode) element;
					clearDependencies(node, beanNode);
				}
			}
			if(beanNode != null &&
					(beanNode.getNature() == IoCModelNatures.BEAN_FACADE || 
					(e.getType() == CommonModelEventTypes.NATURE_CHANGED && ((ModelPropertyChangeEvent<Nature>)e).getOldValue() == IoCModelNatures.BEAN_FACADE))
					&& affectedContext instanceof Node) {
				affectedContext = ((Node) affectedContext).getNodeContainer();
			}
			updateContext(affectedContext);
		}
	}

	@SuppressWarnings("unchecked")
	public Node updateListeners(ModelEvent e) {
		Node node = null;
		ModelEventTrigger source = e.getSource();
		if(e.getType() == BasicModelEventTypes.NODE_ADDED) {
			node = ((ModelCollectionAlterEvent<Node>)e).getElement();
			installListeners(node);
		} else if(e.getType() == BasicModelEventTypes.NODE_REMOVED) {
			node = ((ModelCollectionAlterEvent<Node>)e).getElement();
			removeListeners(node);
		} else if(e.getType() == BasicModelEventTypes.EXTENSION_ADDED) {
			ModelExtension element = ((ModelCollectionAlterEvent<ModelExtension>)e).getElement();
			if(((Class<?>)element.getExtensionClass()) == BeanNode.class) {
				node = (Node) source;
				node.removeListener(BasicModelEventTypes.EXTENSION_ADDED, this);
				node.addListener(BasicModelEventTypes.EXTENSION_REMOVED, this);
				installListeners((BeanNode) element);
			}
		} else if(e.getType() == BasicModelEventTypes.EXTENSION_REMOVED) {
			ModelExtension element = ((ModelCollectionAlterEvent<ModelExtension>)e).getElement();
			if(((Class<?>)element.getExtensionClass()) == BeanNode.class) {
				node = (Node) source;
				node.removeListener(BasicModelEventTypes.EXTENSION_REMOVED, this);
				node.addListener(BasicModelEventTypes.EXTENSION_ADDED, this);
				removeListeners((BeanNode) element);
			}
		} else if(e.getType() == CommonModelEventTypes.NATURE_CHANGED
				|| e.getType() == IoCModelEventTypes.DEPENDENCY_ADDED
				|| e.getType() == IoCModelEventTypes.DEPENDENCY_REMOVED) {
			if(source instanceof BeanNode) {
				node = ((BeanNode)source).getNode();
			}
		}
		return node;
	}
}
