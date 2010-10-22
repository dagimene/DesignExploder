package designexploder.model.impl;

import java.util.Collections;
import java.util.List;

import designexploder.model.Diagram;
import designexploder.model.Node;

public class DiagramImpl implements Diagram {
	
	public List<? extends Node> nodes = Collections.emptyList();

	public List<? extends Node> getNodes() {
		return nodes;
	}

	public void setNodes(List<? extends Node> nodes) {
		this.nodes = nodes;
	}
	
}
