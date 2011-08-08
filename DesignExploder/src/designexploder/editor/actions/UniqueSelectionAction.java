package designexploder.editor.actions;

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.IWorkbenchPart;

public abstract class UniqueSelectionAction extends SelectionAction {

	private Class<? extends EditPart> targetEditPartClass;
	
	public UniqueSelectionAction(IWorkbenchPart part) {
		super(part);
	}
	
	public UniqueSelectionAction(IWorkbenchPart part, int style) {
		super(part, style);
	}

	@Override
	protected boolean calculateEnabled() {
		return getSelectedObjects().size() == 1 && 
				(targetEditPartClass == null ||
				targetEditPartClass.isAssignableFrom(getSelectedObjects().get(0).getClass()));
	}
	
	@SuppressWarnings("rawtypes")
	public EditPart getEditPart() {
		List selectedObjects = getSelectedObjects();
		Object object = selectedObjects.size() > 0 ? selectedObjects.get(0) : null;
		return object instanceof EditPart ? (EditPart)  object : null;
	}
	
	public Object getModel() {
		EditPart editPart = getEditPart();
		return editPart != null ? editPart.getModel() : null;
	}

	public void setTargetEditPartClass(Class<? extends EditPart> targetEditPartClass) {
		this.targetEditPartClass = targetEditPartClass;
	}
}
