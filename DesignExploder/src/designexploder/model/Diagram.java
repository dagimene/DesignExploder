package designexploder.model;

import java.util.List;

public interface Diagram {

	List<? extends Node> getNodes();
	
	void setNodes(List<? extends Node> nodes);
	
}
