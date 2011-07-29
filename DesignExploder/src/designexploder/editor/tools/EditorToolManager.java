package designexploder.editor.tools;

import org.eclipse.gef.EditDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Tool;
import org.eclipse.swt.events.MouseEvent;

public class EditorToolManager {

	private final EditDomain editDomain;

	public EditorToolManager(EditDomain editDomain) {
		this.editDomain = editDomain;
	}

	public void setActiveTool(Tool tool) {
		editDomain.setActiveTool(tool);
	}

	public void activateResizeTool(EditPart newEditPart, MouseEvent mouseEvent) {
		((TriggerableSelectionTool)editDomain.getActiveTool()).initEditPartDrag(newEditPart, mouseEvent);
	}
	
}
