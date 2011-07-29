package designexploder.editor.graphics;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Layer;
import org.eclipse.draw2d.LayeredPane;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.swt.graphics.Color;

public class ContainerNodeFigure extends LayeredPane {
	
	public static final Object CHILDREN_LAYER = new Object();

	public ContainerNodeFigure() {
		Layer childrenLayer = new Layer();
		childrenLayer.setLayoutManager(new XYLayout());
		add(childrenLayer, CHILDREN_LAYER);
		
		setBorder(new LineBorder(new Color(null,141,154,241), 1));
		setBackgroundColor(new Color(null,200,206,249));

		setOpaque(true);
	}

	public IFigure getChildrenLayer() {
		return getLayer(CHILDREN_LAYER);
	}
}
