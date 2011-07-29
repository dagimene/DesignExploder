package designexploder.editor.graphics;

import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.Layer;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;

public class ApplicationContextFigure extends ContainerNodeFigure {

	public static final Object FRONT_LAYER = new Object();

	private Label label;

	public ApplicationContextFigure() {
		super();
		
		Layer frontLayer = new Layer();
		frontLayer.setLayoutManager(new ToolbarLayout());
		label = createLabel();
		frontLayer.add(label);

		addLayerBefore(frontLayer, FRONT_LAYER, getChildrenLayer());
	}

	public void setLabel(String label) {
		this.label.setText(label);
	}
	
	private Label createLabel() {
		Label label = new Label();
		label.setFont(new Font(null, "Arial", 12, SWT.NORMAL));
		label.setBackgroundColor(new Color(null,181,181,249));
		label.setBorder(new LineBorder(new Color(null,141,154,241), 1));
		label.setOpaque(true);
		return label;
	}	
}
