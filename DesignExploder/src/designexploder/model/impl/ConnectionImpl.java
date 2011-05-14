package designexploder.model.impl;

import designexploder.model.BasicModelEventTypes;
import designexploder.model.Connection;
import designexploder.model.Node;

@SuppressWarnings("rawtypes")
public class ConnectionImpl extends ExtendedModelEventTrigger implements Connection {
	
	private String id;
	
	private Node target;
	
	private Node source;
	
	private static long ID_GENERATOR;

	protected ConnectionImpl() {
		this(String.valueOf(ID_GENERATOR++));
	}

	protected ConnectionImpl(String id) {
		this.id = id;
	}
	
	protected void setId(String id) {
		this.id = id;
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

	@Override
	public String getId() {
		return id;
	}
}
