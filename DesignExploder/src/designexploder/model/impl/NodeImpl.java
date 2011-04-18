package designexploder.model.impl;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

import designexploder.model.BasicModelEventTypes;
import designexploder.model.Connection;
import designexploder.model.Node;

public class NodeImpl extends NamedImpl implements Node {

	public NodeImpl(String name) {
		super(name);
		outflows = new ArrayList<Connection>();
		inflows = new ArrayList<Connection>();
		bounds = new Rectangle(); 
	}

	private List<Connection> outflows;
	private List<Connection> inflows;
	private Rectangle bounds;
	
	public Rectangle getBounds() {
		return bounds;
	}

	public void setBounds(Rectangle bounds) {
		Rectangle oldBounds = this.bounds;
		this.bounds = bounds;
		fireBoundsChanged(oldBounds);
	}
	
	public Point getLocation() {
		return bounds.getLocation();
	}

	public void setLocation(Point location) {
		Rectangle oldBounds = new Rectangle(bounds);
		bounds.setLocation(location);
		fireModelPropertyChangeEvent(BasicModelEventTypes.LOCATION_CHANGED, oldBounds.getLocation(), location);
		fireBoundsChanged(oldBounds);
	}

	public Dimension getSize() {
		return bounds.getSize();
	}

	@Override
	public void setSize(Dimension size) {
		Rectangle oldBounds = new Rectangle(bounds);
		bounds.setSize(size);
		fireModelPropertyChangeEvent(BasicModelEventTypes.SIZE_CHANGED, oldBounds.getSize(), size);
		fireBoundsChanged(oldBounds);
	}
	
	private void fireBoundsChanged(Rectangle oldBounds) {
		fireModelPropertyChangeEvent(BasicModelEventTypes.BOUNDS_CHANGED, oldBounds, bounds);
	}

	public List<Connection> getOutflows() {
		return outflows;
	}

	public void setOutflows(List<Connection> outflows) {
		List<Connection> oldOutflows = this.outflows;
		this.outflows = outflows;
		fireModelPropertyChangeEvent(BasicModelEventTypes.OUTFLOWS_CHANGED, oldOutflows, outflows);
		fireEvent(BasicModelEventTypes.CONNECTIONS_CHANGED);
	}

	public List<Connection> getInflows() {
		return inflows;
	}

	public void setInflows(List<Connection> inflows) {
		List<Connection> oldInflows = this.inflows;
		this.inflows = inflows;
		fireModelPropertyChangeEvent(BasicModelEventTypes.INFLOWS_CHANGED, oldInflows, inflows);
		fireEvent(BasicModelEventTypes.CONNECTIONS_CHANGED);
	}

}
