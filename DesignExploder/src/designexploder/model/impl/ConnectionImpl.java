package designexploder.model.impl;

import designexploder.model.Connection;
import designexploder.model.Node;

public class ConnectionImpl implements Connection {
	
	private Node target;
	
	private Node source;
	
	public ConnectionImpl() {}

	public ConnectionImpl(Node target, Node source) {
		this.target = target;
		this.source = source;
	}

	public Node getTarget() {
		return target;
	}

	public void setTarget(Node target) {
		this.target = target;
	}

	public Node getSource() {
		return source;
	}

	public void setSource(Node source) {
		this.source = source;
	}
	
}
