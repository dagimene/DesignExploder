package designexploder.model;

import java.util.List;

class DiagramImpl implements Diagram {
	
	public List<Node> nodes;

	public List<Node> getNodes() {
		return nodes;
	}

	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}
	
}
