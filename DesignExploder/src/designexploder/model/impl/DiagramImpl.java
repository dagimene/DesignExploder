package designexploder.model.impl;

import java.util.Collections;
import java.util.List;

import designexploder.model.Diagram;
import designexploder.model.Node;

public class DiagramImpl implements Diagram {
	
	public List<Node> nodes = Collections.emptyList();

	public List<Node> getNodes() {
		return nodes;
	}

	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}
	
}
