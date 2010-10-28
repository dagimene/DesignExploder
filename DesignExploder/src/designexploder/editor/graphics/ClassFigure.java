package designexploder.editor.graphics;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.ToolbarLayout;

import designexploder.editor.graphics.styles.Style;
import designexploder.editor.graphics.styles.Style.Constant;
import designexploder.editor.graphics.styles.StylesFactory;
import designexploder.model.classnode.DexConstant;

public class ClassFigure extends Figure {

	private Label label;
	private DexConstant nature;
	private boolean selected;
	private SectionFigure attributeFigure = new SectionFigure();
	private SectionFigure methodFigure = new SectionFigure();

	public ClassFigure() {
		setLayoutManager(new ToolbarLayout());
		setBorder(new LineBorder(ColorConstants.black, 1));
		setOpaque(true);

		label = new Label();

		add(label);
		add(attributeFigure);
		add(methodFigure);
	}

	public void setNature(DexConstant nature) {
		this.nature = nature;
		updateStyle();
	}
	
	public void setSelected(boolean selected) {
		this.selected = selected;
		updateStyle();
	}
	
	private void updateStyle() {
		Style style = StylesFactory.getInstance().getStyleFor(nature);
		setBackgroundColor(style.getColor(Constant.getBackground(selected)));
		label.setFont(style.getFont(Constant.getFont(selected)));
		label.setForegroundColor(style.getColor(Constant.getForeground(selected)));
	}
	
	public void setLabel(String label) {
		this.label.setText(label);
	}

	public SectionFigure getAttributesCompartment() {
		return attributeFigure;
	}

	public SectionFigure getMethodsCompartment() {
		return methodFigure;
	}
}
