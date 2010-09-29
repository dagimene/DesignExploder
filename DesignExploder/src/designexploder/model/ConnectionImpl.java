package designexploder.model;

class ConnectionImpl implements Connection {
	
	private Node target;
	
	private Node source;

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
