package designexploder.model.autolayout;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

import designexploder.model.Node;
import designexploder.model.NodeContainer;

/**
 * Spaces given nodes.
 * @author Daniel
 */
class BasicAutolayoutAlgorithm implements Autolayot {
	
	private static final Dimension NO_DIM = new Dimension(-1, -1);
	
	private int x = 20;
	
	@Override
	public void autolayout(NodeContainer diagram) {
		for (Node node : diagram.getNodes()) {
			node.setBounds(new Rectangle(new Point(x += 30, 40), NO_DIM));
		}
	}
	
}
