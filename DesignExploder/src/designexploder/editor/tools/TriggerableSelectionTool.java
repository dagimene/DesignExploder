package designexploder.editor.tools;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.swt.events.MouseEvent;

public class TriggerableSelectionTool extends DragBypassingSelectionTool {
	
	public void initEditPartDrag(EditPart target, MouseEvent mouseEvent) {
		if(target instanceof GraphicalEditPart) {
			initDragByPass(new MouseUpResizeTracker((GraphicalEditPart) target, PositionConstants.SOUTH_EAST), mouseEvent);
		}
	}
	
}
