package designexploder.model;

@SuppressWarnings("rawtypes")
public interface Connection extends ModelEventTrigger {

	String getId();
	
	Node getTarget();
	
	Node getSource();
	
	void setSource(Node source);
	
	void setTarget(Node target);
	
}
