package designexploder.model;

public interface Connection {

	Node getTarget();
	
	Node getSource();
	
	void setSource(Node source);
	
	void setTarget(Node target);
	
}
