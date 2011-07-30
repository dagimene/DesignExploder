package designexploder.model.impl.build;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.ui.IMemento;

import designexploder.model.Node;

public enum ModelProperties {
	
	ID {
		@Override
		public void read(IMemento memento, Node node) {
			String id = memento.getString("id");
			throw new UnsupportedOperationException("Cannot set id "+id+" to node "+node);
		}

		@Override
		public void write(IMemento memento, Node node) {
			String id = node.getId();
			memento.putString("id", id);
		}
	},
	LOCATION {
		@Override
		public void read(IMemento memento, Node node) {
			int x = memento.getInteger("x");
			int y = memento.getInteger("y");
			node.setLocation(new Point(x, y));
		}

		@Override
		public void write(IMemento memento, Node node) {
			Point location = node.getLocation();
			memento.putInteger("x", location.x);
			memento.putInteger("y", location.y);
		}
	},
	BOUNDS {
		@Override
		public void read(IMemento memento, Node node) {
			int x = memento.getInteger("x");
			int y = memento.getInteger("y");
			int w = memento.getInteger("w");
			int h = memento.getInteger("h");
			node.setBounds(new Rectangle(x, y, w, h));
		}

		@Override
		public void write(IMemento memento, Node node) {
			Rectangle bounds = node.getBounds();
			memento.putInteger("x", bounds.x);
			memento.putInteger("y", bounds.y);
			memento.putInteger("w", bounds.width);
			memento.putInteger("h", bounds.height);
		}
	};

	public abstract void read(IMemento memento, Node node);
	
	public abstract void write(IMemento memento, Node node); 
	
}
