package designexploder.model.build;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import designexploder.model.BasicModelUtil;
import designexploder.model.Node;
import designexploder.model.NodeContainer;
import designexploder.util.adt.IterableIterator;

public abstract class BaseModelBuilder implements ModelBuilder {

	private NodeContainer diagram;
	
	/**
	 * Map for faster node lookup
	 */
	private Map<String, Node> nodes = new HashMap<String, Node>();
	
	@Override
	public NodeContainer build(NodeContainer diagram) {
		this.diagram = diagram;
		for(Node node : new IterableIterator<Node>(diagram.getDeepIterator())) {
			registerNode(node);
		}
		return diagram;
	}

	protected void addNode(Node node) {
		addNode(node, diagram);
	}
	
	protected void addNode(Node node, NodeContainer container) {
		registerNode(node);
		node.setNodeContainer(container);
		BasicModelUtil.addNode(node);
	}
	
	public void removeNode(Node node) {
		unregisterNode(node);
		BasicModelUtil.removeNode(node);
	}
	
	protected void registerNode(Node node) {
		nodes.put(node.getId(), node);
	}

	protected void unregisterNode(Node node) {
		nodes.remove(node.getId());
	}

	protected Node findNode(String id) {
		return nodes.get(id);
	}
	
	protected Collection<Node> getAllNodes() {
		return nodes.values();
	}

}