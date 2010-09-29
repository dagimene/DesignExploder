package designexploder.model;

import java.util.List;

public interface Node {

	String getLabel();

	void setLabel(String label);

	List<Connection> getConnections();

	void setConnections(List<Connection> connections);

}
