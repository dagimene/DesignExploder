package designexploder.model;

import java.util.List;

import org.eclipse.draw2d.geometry.Point;

public interface Node {

	String getLabel();

	void setLabel(String label);
	
	Point getLocation();

	void setLocation(Point location);

	void setOutflows(List<Connection> connections);

	void setInflows(List<Connection> connections);

	List<Connection> getOutflows();

	List<Connection> getInflows();

}
