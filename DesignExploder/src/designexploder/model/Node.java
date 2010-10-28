package designexploder.model;

import java.util.List;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

public interface Node extends Named {

	Point getLocation();

	void setLocation(Point location);
	
	Dimension getSize();
	
	void setSize(Dimension size);
	
	Rectangle getBounds();
	
	void setBounds(Rectangle bounds);
	
	void setOutflows(List<Connection> connections);

	List<Connection> getOutflows();

	List<Connection> getInflows();

	void setInflows(List<Connection> connections);

}
