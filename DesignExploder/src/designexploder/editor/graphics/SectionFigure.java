package designexploder.editor.graphics;

import java.util.List;

import org.eclipse.draw2d.AbstractBorder;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Insets;

import designexploder.model.extension.common.Nature;
import designexploder.resources.IconResource;

public class SectionFigure extends Figure {

	public SectionFigure() {
		ToolbarLayout layout = new ToolbarLayout(false);
		layout.setMinorAlignment(ToolbarLayout.ALIGN_TOPLEFT);
		//layout.setSpacing(2);
		layout.setStretchMinorAxis(true);
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
	
	public void setLabelContent(int index, String text, Nature nature, List<IconResource> icons) {
		((ClassItemFigure) getChildren().get(index)).setContent(text, nature, icons);
	}
}
