package designexploder.model.impl;

import designexploder.model.BasicModelEventTypes;
import designexploder.model.Connection;
import designexploder.model.Node;

public class ConnectionImpl extends ModelPropertyChangeEventTrigger implements Connection {
	
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
		Node oldTarget = target;
		this.target = target;
		fireModelPropertyChangeEvent(BasicModelEventTypes.TARGET_CHANGED, oldTarget, target);
	}

	public Node getSource() {
		return source;
	}

	public void setSource(Node source) {
		Node oldSource = source;
		this.source = source;
		fireModelPropertyChangeEvent(BasicModelEventTypes.TARGET_CHANGED, oldSource, source);
	}
	
}
