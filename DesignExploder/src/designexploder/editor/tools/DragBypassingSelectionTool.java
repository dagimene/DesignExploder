package designexploder.editor.tools;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.tools.SelectionTool;
import org.eclipse.swt.events.MouseEvent;

public class DragBypassingSelectionTool extends SelectionTool {
	
	/** Mouse up drag state */
	protected static final int MOUSE_UP_DRAG_STATE_IN_PROGRESS = SelectionTool.MAX_STATE << 1;
	/** Max state */
	protected static final int MAX_STATE = MOUSE_UP_DRAG_STATE_IN_PROGRESS;
	
	
	public DragBypassingSelectionTool() {
		super();
	}

	public boolean initDragByPass(DragTracker tracker, MouseEvent event) {
		resetHover();

		if(stateTransition(STATE_INITIAL, MOUSE_UP_DRAG_STATE_IN_PROGRESS)) {
			setStartLocation(new Point(event.x, event.y));
			setDragTracker(tracker);
			tracker.mouseUp(event, null);
			return true;
		}
		
		return false;
	}

	@Override
	protected boolean handleButtonDown(int button) {
		if(stateTransition(MOUSE_UP_DRAG_STATE_IN_PROGRESS, STATE_INITIAL)) {
			getDragTracker().commitDrag();
			setDragTracker(null);
			getCurrentViewer().flush();
		}
		return super.handleButtonDown(button);
	}
	
	/** Handle new drag state */
	protected boolean handleFocusLost() {
		if (isInState(MOUSE_UP_DRAG_STATE_IN_PROGRESS)) {
			if (getDragTracker() != null)
				setDragTracker(null);
			setState(STATE_INITIAL);
			return true;
		}
		return super.handleFocusLost();
	}
}