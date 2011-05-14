package designexploder.model;

import java.util.List;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

public interface Node<C extends Connection> extends ModelEventTrigger {

	String getId();
	
	Point getLocation();

	Rectangle getBounds();
	
	Dimension getSize();
	
	void setLocation(Point location);
	
	void setSize(Dimension size);
	
	void setBounds(Rectangle bounds);
	
	List<C> getOutflows();

	void addOutflow(C connection);

	void removeOutflow(C connection);

	List<C> getInflows();

	void addInflow(C connection);

	void removeInflow(C connection);

}
