package designexploder.editor.tools;

import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.tools.CreationTool;
import org.eclipse.swt.events.MouseEvent;

import designexploder.model.Node;
import designexploder.util.SWTUtil;

public class ResizeAwareCreationTool extends CreationTool {

	private boolean dragStarted;
	
	@Override
	protected void handleFinished() {
		MouseEvent triggeringEvent = SWTUtil.createMouseEvent(getCurrentInput(), getCurrentViewer().getControl()); 
		Node newNode = (Node) getCreateRequest().getNewObject();
		EditPart newEditPart = (EditPart) getCurrentViewer().getEditPartRegistry().get(newNode);
		
		super.handleFinished(); // load default tool
		
		if(triggeringEvent != null && newNode.isResizeable() && !dragStarted) {
			activateResizeTool(newEditPart, triggeringEvent);
		}
	}
	
	@Override
	public void mouseUp(MouseEvent me, EditPartViewer viewer) {
		super.mouseUp(me, viewer);
		dragStarted = false; // reset flag
	}
	
	private void activateResizeTool(EditPart newEditPart, MouseEvent mouseEvent) {
		EditorToolManager toolManager = (EditorToolManager) ((DefaultEditDomain)getDomain()).getEditorPart().getAdapter(EditorToolManager.class);
		if(toolManager != null) {
			toolManager.activateResizeTool(newEditPart, mouseEvent);
		}
	}

	@Override
	protected boolean handleDragStarted() {
		boolean handleDragStartedResult = super.handleDragStarted();
		if(handleDragStartedResult) {
			dragStarted = true;
		}
		return handleDragStartedResult;
	}
}
