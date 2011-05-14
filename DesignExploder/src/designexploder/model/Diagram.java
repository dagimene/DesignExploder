package designexploder.model;

import java.util.List;

public interface Diagram<N extends Node<C>, C extends Connection> extends ModelEventTrigger {

	List<N> getNodes();
	
	void addNode(N node);
	
	void removeNode(N node);

}
