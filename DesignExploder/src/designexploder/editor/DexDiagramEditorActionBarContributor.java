package designexploder.editor;

import org.eclipse.gef.ui.actions.ActionBarContributor;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.RetargetAction;

import designexploder.editor.actions.DexActionFactory;
import designexploder.resources.IconResource;

public class DexDiagramEditorActionBarContributor extends ActionBarContributor {
	
	protected void buildActions() {
		addRetargetAction(createCreateApplicationContextAction());
		//addRetargetAction(new LabelRetargetAction(ActionFactory.UNDO.getId(), null));
		//addRetargetAction(new LabelRetargetAction(ActionFactory.REDO.getId(), null));
		//addRetargetAction(new LabelRetargetAction(ActionFactory.DELETE.getId(), null));
	}

	private RetargetAction createCreateApplicationContextAction() {
		RetargetAction result = new RetargetAction(DexActionFactory.CREATE_APPLICATION_CONTEXT.name(), "Create Application Context");
		result.setImageDescriptor(ImageDescriptor.createFromFile(IconResource.class, IconResource.CONTEXT_ICON.getPath()));
		return result;
	}

	public void contributeToToolBar(IToolBarManager toolBarManager) {
		super.contributeToToolBar(toolBarManager);
		//toolBarManager.add(getAction(ActionFactory.UNDO.getId()));
		//toolBarManager.add(getAction(ActionFactory.REDO.getId()));
		toolBarManager.add(getAction(DexActionFactory.CREATE_APPLICATION_CONTEXT.name()));
	}

	protected void declareGlobalActionKeys() {
		this.addGlobalActionKey(ActionFactory.SAVE.getId());
		this.addGlobalActionKey(ActionFactory.UNDO.getId());
		this.addGlobalActionKey(ActionFactory.REDO.getId());
		this.addGlobalActionKey(ActionFactory.DELETE.getId());
		this.addGlobalActionKey(ActionFactory.SELECT_ALL.getId());
		this.addGlobalActionKey(DexActionFactory.CREATE_APPLICATION_CONTEXT.name());
	}

	@Override
	public void setActiveEditor(IEditorPart editor) {
		super.setActiveEditor(editor);
		
	}
}