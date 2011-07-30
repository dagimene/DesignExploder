package designexploder.model.impl;

import java.util.Iterator;
import java.util.List;

import designexploder.model.ContainerNode;
import designexploder.model.Node;
import designexploder.model.event.BasicModelEventTypes;
import designexploder.model.event.ModelEventListener;
import designexploder.model.event.ModelEventType;

final class ContainerNodeImpl extends NodeImpl implements ContainerNode {
	
	private NodeContainerImpl containerDelegate = new NodeContainerImpl();

	@Override
	public Iterator<Node> getDeepIterator() {
		return containerDelegate.getDeepIterator();
	}

	@Override
	public Node findNode(String id) {
		return containerDelegate.findNode(id);
	}

	@Override
	public void addNode(Node node) {
		containerDelegate.addNode(node);
	}

	@Override
	public void removeNode(Node node) {
		containerDelegate.removeNode(node);
	}

	@Override
	public List<Node> getNodes() {
		return containerDelegate.getNodes();
	}

	public void addListener(ModelEventType type, ModelEventListener listener) {
		if (isDelegatedEvent(type)) {
			containerDelegate.addListener(type, listener);
		} else {
			super.addListener(type, listener);
		}
	}

	public boolean removeListener(ModelEventType type,
			ModelEventListener listener) {
		if (isDelegatedEvent(type)) {
			return containerDelegate.removeListener(type, listener);
		}
		return super.removeListener(type, listener);
	}
	
	private boolean isDelegatedEvent(ModelEventType type) {
		return type == BasicModelEventTypes.NODE_ADDED || type == BasicModelEventTypes.NODE_REMOVED;
	}
}