package designexploder.model.impl;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import designexploder.model.Node;
import designexploder.model.NodeContainer;
import designexploder.model.event.BasicModelEventTypes;

final class NodeContainerImpl extends ExtensibleModelElementImpl implements NodeContainer {

	private HierarchyLevelList nodes = new HierarchyLevelList(); 

	public List<Node> getNodes() {
		return Collections.unmodifiableList(nodes);
	}

	@Override
	public void addNode(Node node) {
		nodes.add(node);
		fireModelCollectionAlterEvent(BasicModelEventTypes.NODE_ADDED, nodes, node);
	}

	@Override
	public void removeNode(Node node) {
		nodes.remove(node);
		fireModelCollectionAlterEvent(BasicModelEventTypes.NODE_REMOVED, nodes, node);
	}

	@Override
	public Node findNode(String id) {
		Iterator<Node> iterator = getDeepIterator();
		while(iterator.hasNext()) {
			Node node = iterator.next();
			if(id.equals(node.getId())) {
				return node;
			}
		}
		return null;
	}

	@Override
	public Iterator<Node> getDeepIterator() {
		return nodes.deepIterator();
	}

}
