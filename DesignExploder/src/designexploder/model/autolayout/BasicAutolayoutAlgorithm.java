package designexploder.model.autolayout;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

import designexploder.model.Diagram;
import designexploder.model.Node;
import designexploder.model.utils.ModelUtils;

/**
 * Spaces given nodes.
 * @author Daniel
 */
class BasicAutolayoutAlgorithm implements Autolayot {
	
	private int x = 20;
	
	@Override
	public void autolayout(Diagram diagram) {
		for (Node node : diagram.getNodes()) {
			node.setBounds(new Rectangle(new Point(x += 30, 40), ModelUtils.NO_DIM));
		}
	}
	
}
