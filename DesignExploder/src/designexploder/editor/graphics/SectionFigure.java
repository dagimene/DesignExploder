package designexploder.editor.graphics;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.AbstractBorder;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ImageFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Insets;

import designexploder.editor.graphics.styles.Style;
import designexploder.editor.graphics.styles.Style.Constant;
import designexploder.editor.graphics.styles.StylesFactory;
import designexploder.model.extension.common.Nature;

public class SectionFigure extends Figure {

	private List<Figure> rows = new ArrayList<Figure>();
	
	public SectionFigure() {
		ToolbarLayout layout = new ToolbarLayout(false);
		layout.setMinorAlignment(ToolbarLayout.ALIGN_TOPLEFT);
		layout.setStretchMinorAxis(false);
		layout.setSpacing(2);
		setLayoutManager(layout);
		
		setBorder(new AbstractBorder() {
			public void paint(IFigure figure, Graphics graphics, Insets insets) {
				graphics.drawLine(getPaintRectangle(figure, insets).getTopLeft(), tempRect.getTopRight());
			}
			public Insets getInsets(IFigure figure) {
				return new Insets(1, 0, 0, 0);
			}
		});
	}
	
	public void setLabelsQuantity(int quantity) {
		int lSize = rows.size();
		while(lSize > quantity) {
			remove(rows.remove(--lSize));
		}
		while(lSize < quantity) {
			Figure row = new Figure();
			row.setLayoutManager(new ToolbarLayout());
			row.add(new Label());
			rows.add(row);
			add(row);
			lSize++;
		}
	}
	
	public void setLabelContent(int index, String text, Nature nature, List<Nature> icons) {
		Figure row = rows.get(index);
		List<?> children = row.getChildren();
		int childrenCount = children.size();
		Label label = (Label) children.get(childrenCount - 1);
		row.removeAll();
		for (Nature icon : icons) {
			Style style = StylesFactory.getInstance().getStyleFor(icon);
			row.add(new ImageFigure(style.getImage(Constant.getIcon())));
		}
		label.setText(text);
		Style style = StylesFactory.getInstance().getStyleFor(nature);
		label.setFont(style.getFont(Constant.getFont(false)));
		label.setForegroundColor(style.getColor(Constant.getForeground(false)));
		row.add(label);
	}
}
