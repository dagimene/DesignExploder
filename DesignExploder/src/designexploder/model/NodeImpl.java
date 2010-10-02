package designexploder.model;

import java.util.Collections;
import java.util.List;

import org.eclipse.draw2d.geometry.Point;

class NodeImpl implements Node {

	private List<Connection> outflows = Collections.emptyList();
	private List<Connection> inflows = Collections.emptyList();
	private String label = "";
	private Point location;
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
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

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}
}
