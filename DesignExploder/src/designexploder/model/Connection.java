package designexploder.model;

public interface Connection extends ExtensibleModelElement {

	Node getTarget();
	
	Node getSource();
	
	void setSource(Node source);
	
	void setTarget(Node target);
	
}
