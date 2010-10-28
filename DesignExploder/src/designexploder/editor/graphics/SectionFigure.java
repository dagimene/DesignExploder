package designexploder.editor.graphics;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.draw2d.AbstractBorder;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Insets;

import designexploder.editor.graphics.styles.Style;
import designexploder.editor.graphics.styles.StylesFactory;
import designexploder.model.classnode.DexConstant;
import designexploder.util.Pair;

public class SectionFigure extends Figure {

	private List<Pair<Label, DexConstant>> labels = new LinkedList<Pair<Label, DexConstant>>();
	
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
	
	public void setLabels(List<Pair<String, DexConstant>> components) {
		int lSize = labels.size();
		int cSize = components.size(); 
		while(lSize > cSize) {
			remove(labels.remove(--lSize).getFirst());
		}
		while(lSize < cSize) {
			Label l = new Label();
			labels.add(new Pair<Label, DexConstant>(l, null));
			add(l);
			lSize++;
		}
		Iterator<Pair<Label, DexConstant>> labelsItt = labels.iterator();
		for (Pair<String, DexConstant> component : components) {
			Pair<Label, DexConstant> label = labelsItt.next(); 
			label.getFirst().setText(component.getFirst());
			if(label.getSecond() != component.getSecond()) {
				label.setSecond(component.getSecond());
				Label l = label.getFirst();
				Style s = StylesFactory.getInstance().getStyleFor(label.getSecond());
				l.setForegroundColor(s.getColor(Style.Constant.FOREGROUND));
				l.setFont(s.getFont(Style.Constant.FONT));
			}
		}
	}
}
