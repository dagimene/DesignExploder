package designexploder.model.autolayout;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

import designexploder.model.Connection;
import designexploder.model.Diagram;
import designexploder.model.Node;

/**
 * Spaces given nodes.
 * @author Daniel
 */
class BasicAutolayoutAlgorithm implements Autolayot {
	
	private static final Dimension NO_DIM = new Dimension(-1, -1);
	
	private int x = 20;
	
	@Override
	public <N extends Node<C>, C extends Connection> void autolayout(
			Diagram<N, C> diagram) {
		for (N node : diagram.getNodes()) {
			node.setBounds(new Rectangle(new Point(x += 30, 40), NO_DIM));
		}
	}
	
}
