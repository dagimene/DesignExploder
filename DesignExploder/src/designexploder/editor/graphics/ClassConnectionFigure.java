package designexploder.editor.graphics;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.ConnectionEndpointLocator;
import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.ImageFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.MidpointLocator;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Image;

import designexploder.editor.graphics.styles.Style;
import designexploder.editor.graphics.styles.Style.Constant;
import designexploder.editor.graphics.styles.StylesFactory;
import designexploder.model.extension.common.Nature;

/**
 * Adds support for additional ClassConnection's name and cardinality
 */
public class ClassConnectionFigure extends ConnectionFigure {

	public static final int CONNECTION = 0;
	public static final int SOURCE = 1;
	public static final int TARGET = 2;

	private Label connectionLabel;
	private Label souceLabel;
	private Label targetLabel;
	private Nature nature;
	private ImageFigure icon;
	
	public ClassConnectionFigure() {
		super();
		addLabels();
	}

	public ClassConnectionFigure(ClassFigure source, ClassFigure target) {
		super(source, target);
		addLabels();
	}
	
	private void addLabels() {
		icon = new ImageFigure();
		add(createIconHolder(icon), new MidpointLocator(this, 0));
		connectionLabel = new Label();
		add(connectionLabel, new MidpointLocator(this, 0));
		souceLabel = new Label();
		add(souceLabel, new ConnectionEndpointLocator(this, false));
		targetLabel = new Label();
		add(targetLabel, new ConnectionEndpointLocator(this, true));
	}

	public void setLabelIcon(int position, Image icon) {
		getLabelFor(position).setIcon(icon);
	}

	public void setLabelText(int position, String text) {
		getLabelFor(position).setText(text);
	}
	
	private Label getLabelFor(int position) {
		switch(position) {
		case CONNECTION:
			return connectionLabel;
		case SOURCE:
			return souceLabel;
		case TARGET:
			return targetLabel;
		}
		throw new IllegalArgumentException("No label component for given constant");
	}

	public void setNature(Nature nature) {
		this.nature = nature;
		updateStyle();
	}
	
	private void updateStyle() {
		Style style = StylesFactory.getInstance().getStyleFor(nature);
		setForegroundColor(style.getColor(Constant.getLineColor()));
		setLineStyle(style.getInt(Constant.getLineDash()));
		setLineWidth(style.getInt(Constant.getLineWidth()));
		setSourceDecoration(style.getDecoration(Constant.getSourceDecoration()));
		setTargetDecoration(style.getDecoration(Constant.getTargetDecoration()));
		Image image = style.getImage(Constant.getIcon());
		icon.setImage(image);
		icon.getParent().setVisible(image != null);
	}

	private static Figure createIconHolder(Figure icon) {
		Ellipse iconHolder = new Ellipse();
		iconHolder.setPreferredSize(26, 26);
		iconHolder.setBackgroundColor(ColorConstants.white);
		iconHolder.setOpaque(true);
		iconHolder.setLayoutManager(new XYLayout());
		iconHolder.add(icon, new Rectangle(5, 5, 16, 16));
		return iconHolder;
	}
}
