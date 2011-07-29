package designexploder.model;

import java.util.List;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

public interface Node extends ExtensibleModelElement {

	Point getLocation();

	Rectangle getBounds();
	
	Dimension getSize();
	
	boolean isResizeable();

	void setResizeable(boolean resizeable);

	void setLocation(Point location);
	
	void setSize(Dimension size);
	
	void setBounds(Rectangle bounds);
	
	List<Connection> getOutflows();

	void addOutflow(Connection connection);

	void removeOutflow(Connection connection);

	List<Connection> getInflows();

	void addInflow(Connection connection);

	void removeInflow(Connection connection);
	
	void setNodeContainer(NodeContainer container);

	NodeContainer getNodeContainer();

}
