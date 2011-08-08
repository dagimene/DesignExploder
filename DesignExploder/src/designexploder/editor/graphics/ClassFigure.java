package designexploder.editor.graphics;

import org.eclipse.draw2d.BorderLayout;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.ImageFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.ToolbarLayout;

import designexploder.editor.graphics.styles.Style;
import designexploder.editor.graphics.styles.Style.Constant;
import designexploder.editor.graphics.styles.StylesFactory;
import designexploder.model.extension.common.Nature;

public class ClassFigure extends Figure {

	private Label label;
	private Nature nature;
	private boolean selected;
	private SectionFigure attributeFigure = new SectionFigure();
	private SectionFigure methodFigure = new SectionFigure();
	private ImageFigure icon;

	public ClassFigure() {
		setLayoutManager(new ToolbarLayout());
		setBorder(new LineBorder(ColorConstants.black, 1));
		setOpaque(true);

		Figure header = new Figure();
		header.setLayoutManager(new BorderLayout());
		header.add(icon = new ImageFigure(), BorderLayout.LEFT);
		header.add(label = new Label(), BorderLayout.CENTER);
		// icon.setPreferredSize(16, 16);
		add(header);
		add(attributeFigure);
		add(methodFigure);
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
		updateStyle();
	}
	
	public void setNature(Nature nature) {
		this.nature = nature;
		updateStyle();
	}
	
	public void setLabel(String label) {
		this.label.setText(label);
	}

	private void updateStyle() {
		Style style = StylesFactory.getInstance().getStyleFor(nature);
		setBackgroundColor(style.getColor(Constant.getBackground(selected)));
		icon.setImage(style.getImage(Constant.getIcon()).getImage());
		label.setFont(style.getFont(Constant.getFont(selected)));
		label.setForegroundColor(style.getColor(Constant.getForeground(selected)));
	}
	
	public SectionFigure getAttributesCompartment() {
		return attributeFigure;
	}

	public SectionFigure getMethodsCompartment() {
		return methodFigure;
	}
}
