package designexploder.editor.actions;

import org.eclipse.gef.ui.actions.EditorPartAction;
import org.eclipse.jface.action.IAction;
import org.eclipse.ui.IEditorPart;

import designexploder.editor.tools.ListenableTool;
import designexploder.editor.tools.ToolActivationListener;

public abstract class EditorToolBindedAction extends EditorPartAction implements ToolActivationListener {

	public EditorToolBindedAction(IEditorPart editor) {
		super(editor, IAction.AS_CHECK_BOX);
	}

	@Override
	public void setToolActive(boolean active, ListenableTool listenableCreationTool) {
		setChecked(active);
	}

}
