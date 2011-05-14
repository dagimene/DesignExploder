package designexploder.model.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

import designexploder.model.BasicModelEventTypes;
import designexploder.model.Connection;
import designexploder.model.Node;

public class NodeImpl<C extends Connection> extends ExtendedModelEventTrigger implements Node<C> {

	private List<C> outflows = new ArrayList<C>();
	private List<C> inflows = new ArrayList<C>();
	private Rectangle bounds = new Rectangle();
	private String id;
	
	private static long ID_GENERATOR;

	protected NodeImpl() {
		this(String.valueOf(ID_GENERATOR++));
	}

	protected NodeImpl(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
	
	protected void setId(String id) {
		this.id = id;
	}

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

	public List<C> getOutflows() {
		return outflows;
	}

	public void addOutflow(C outflow) {
		outflows.add(outflow);
		fireModelCollectionAlterEvent(BasicModelEventTypes.OUTFLOW_ADDED, outflows, outflow);
	}

	public List<C> getInflows() {
		return Collections.unmodifiableList(inflows);
	}

	public void addInflow(C inflow) {
		inflows.add(inflow);
		fireModelCollectionAlterEvent(BasicModelEventTypes.INFLOW_ADDED, Collections.unmodifiableList(inflows), inflow);
	}

	public void removeOutflow(C outflow) {
		outflows.add(outflow);
		fireModelCollectionAlterEvent(BasicModelEventTypes.OUTFLOW_REMOVED, outflows, outflow);
	}

	public void removeInflow(C inflow) {
		inflows.remove(inflow);
		fireModelCollectionAlterEvent(BasicModelEventTypes.INFLOW_REMOVED, Collections.unmodifiableList(inflows), inflow);
	}
}
