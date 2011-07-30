package designexploder.model;

import java.util.Iterator;
import java.util.List;

public interface NodeContainer extends ExtensibleModelElement {

	List<Node> getNodes();
	
	void addNode(Node node);
	
	void removeNode(Node node);

	Node findNode(String id);
	
	Iterator<Node> getDeepIterator();

}
