package designexploder.model.impl;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

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
		this.bounds = bounds;
	}
	
	public Point getLocation() {
		return bounds.getLocation();
	}

	public void setLocation(Point location) {
		bounds.setLocation(location);
	}

	public Dimension getSize() {
		return bounds.getSize();
	}

	@Override
	public void setSize(Dimension size) {
		bounds.setSize(size);
	}	

	public List<Connection> getOutflows() {
		return outflows;
	}

	public void setOutflows(List<Connection> outflows) {
		this.outflows = outflows;
	}

	public List<Connection> getInflows() {
		return inflows;
	}

	public void setInflows(List<Connection> inflows) {
		this.inflows = inflows;
	}

}
