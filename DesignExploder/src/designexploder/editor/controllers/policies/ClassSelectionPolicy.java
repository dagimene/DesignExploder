package designexploder.editor.controllers.policies;

import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import designexploder.editor.graphics.ClassFigure;

public class ClassSelectionPolicy extends NonResizableEditPolicy {
	@Override
	protected void showSelection() {
		super.showSelection();
		ClassFigure figure = (ClassFigure)getHostFigure();
		figure.setSelected(true);
	}
	
	@Override
	protected void hideSelection() {
		super.showSelection();
		ClassFigure figure = (ClassFigure)getHostFigure();
		figure.setSelected(false);
	}
}
