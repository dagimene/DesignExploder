package designexploder.model.build;

import java.util.HashMap;
import java.util.Map;

import designexploder.model.Node;
import designexploder.model.NodeContainer;

public abstract class BaseModelBuilder implements ModelBuilder {

	private NodeContainer diagram;
	
	/**
	 * Map for faster node lookup
	 */
	private Map<String, Node> nodes = new HashMap<String, Node>();
	
	@Override
	public NodeContainer build(NodeContainer diagram) {
		this.diagram = diagram;
		for (Node node : diagram.getNodes()) {
			nodes.put(node.getId(), node);
		}
		return diagram;
	}

	protected void addNode(Node node) {
		nodes.put(node.getId(), node);
		node.setNodeContainer(diagram);
		diagram.addNode(node);
	}
	
	protected Node findNode(String id) {
		return nodes.get(id);
	}

}