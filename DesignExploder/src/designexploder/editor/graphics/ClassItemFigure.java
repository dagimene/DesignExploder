package designexploder.editor.graphics;

import java.util.List;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.ImageFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.ToolbarLayout;

import designexploder.editor.graphics.styles.Style;
import designexploder.editor.graphics.styles.StylesFactory;
import designexploder.editor.graphics.styles.Style.Constant;
import designexploder.model.extension.common.Nature;
import designexploder.resources.IconResource;

public class ClassItemFigure extends Figure {

	private Label label;
	private Style style;
	private boolean selected;

	public ClassItemFigure() {
		ToolbarLayout layout = new ToolbarLayout(true);
		layout.setSpacing(2);
		setLayoutManager(layout);
		label = new Label();
		add(label);
	}

	public void setContent(String text, Nature nature, List<IconResource> icons) {
	}

	public void setLabel(String label) {
		this.label.setText(label);
	}

	public void setNature(Nature nature) {
		style = StylesFactory.getInstance().getStyleFor(nature);
		updateStyle();
	}

	private void updateStyle() {
		label.setFont(style.getFont(Constant.getFont(selected)));
		label.setForegroundColor(style.getColor(Constant.getForeground(false)));
	}

	public void setIcons(List<IconResource> icons) {
		removeAll();
		for (IconResource iconResource : icons) {
			add(new ImageFigure(iconResource.getImage()));
		}
		add(label);
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
		updateStyle();
	}
	
}
