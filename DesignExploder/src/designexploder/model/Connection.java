package designexploder.model;

public interface Connection extends ModelEventTrigger {

	Node getTarget();
	
	Node getSource();
	
	void setSource(Node source);
	
	void setTarget(Node target);
	
}
