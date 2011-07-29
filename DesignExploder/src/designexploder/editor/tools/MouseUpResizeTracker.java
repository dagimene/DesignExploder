package designexploder.editor.tools;

import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.tools.ResizeTracker;

public class MouseUpResizeTracker extends ResizeTracker {

	/** 
	 *  Mouse up drag state.
	 *  This must be equal STATE_ACCESSIBLE_DRAG_IN_PROGRESS since package
	 *  scoped method 'isInDragInProgress' cannot be overridden to add
	 *  the new drag state, preventing handleDragInProgress method to
	 *  process the event.
	 */ 
	protected static final int MOUSE_UP_DRAG_STATE_IN_PROGRESS = STATE_ACCESSIBLE_DRAG_IN_PROGRESS;

	public MouseUpResizeTracker(GraphicalEditPart owner, int direction) {
		super(owner, direction);
	}
	
	protected boolean handleButtonUp(int button) {
		if (button != 1) {
			setState(STATE_INVALID);
			handleInvalidInput();
		} else {
			if (stateTransition(STATE_INITIAL, MOUSE_UP_DRAG_STATE_IN_PROGRESS)) {
				setStartLocation(getLocation());
			}
		}
		return true;
	}
}
