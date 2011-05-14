package designexploder.model.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import designexploder.model.BasicModelEventTypes;
import designexploder.model.Connection;
import designexploder.model.Diagram;
import designexploder.model.Node;

class DiagramImpl<N extends Node<C>, C extends Connection> extends ExtendedModelEventTrigger implements Diagram<N, C> {
	
	public List<N> nodes = (List<N>) new ArrayList<N>();

	protected DiagramImpl() {}
	
	public List<N> getNodes() {
		return Collections.unmodifiableList(nodes);
	}

	@Override
	public void addNode(N node) {
		nodes.add(node);
		fireModelCollectionAlterEvent(BasicModelEventTypes.NODE_ADDED, nodes, node);
	}

	@Override
	public void removeNode(N node) {
		nodes.remove(node);
		fireModelCollectionAlterEvent(BasicModelEventTypes.NODE_REMOVED, nodes, node);
	}
}
