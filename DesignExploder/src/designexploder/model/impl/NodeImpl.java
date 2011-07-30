package designexploder.model.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

import designexploder.model.Connection;
import designexploder.model.Node;
import designexploder.model.NodeContainer;
import designexploder.model.event.BasicModelEventTypes;

class NodeImpl extends ExtensibleModelElementImpl implements Node {

	private NodeContainer container;
	private List<Connection> outflows = new ArrayList<Connection>();
	private List<Connection> inflows = new ArrayList<Connection>();
	private Rectangle bounds = new Rectangle(0, 0, -1, -1);
	private boolean resizeable;
	
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

	public List<Connection> getInflows() {
		return Collections.unmodifiableList(inflows);
	}

	public void addOutflow(Connection outflow) {
		outflows.add(outflow);
		fireModelCollectionAlterEvent(BasicModelEventTypes.OUTFLOW_ADDED, outflows, outflow);
	}

	public void addInflow(Connection inflow) {
		inflows.add(inflow);
		fireModelCollectionAlterEvent(BasicModelEventTypes.INFLOW_ADDED, Collections.unmodifiableList(inflows), inflow);
	}

	public void removeOutflow(Connection outflow) {
		outflows.remove(outflow);
		fireModelCollectionAlterEvent(BasicModelEventTypes.OUTFLOW_REMOVED, outflows, outflow);
	}

	public void removeInflow(Connection inflow) {
		inflows.remove(inflow);
		fireModelCollectionAlterEvent(BasicModelEventTypes.INFLOW_REMOVED, Collections.unmodifiableList(inflows), inflow);
	}

	public void setNodeContainer(NodeContainer container) {
		this.container = container;
	}

	public NodeContainer getNodeContainer() {
		return container;
	}

	@Override
	public boolean isResizeable() {
		return resizeable;
	}

	@Override
	public void setResizeable(boolean resizeable) {
		this.resizeable = resizeable;
	}
}
