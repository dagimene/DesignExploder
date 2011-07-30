package designexploder.actions;

import org.eclipse.core.resources.IFolder;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;

import designexploder.editor.DexDiagramEditor;
import designexploder.editor.tools.EditorToolManager;
import designexploder.editor.tools.ListenableCreationTool;
import designexploder.editor.tools.ListenableTool;
import designexploder.model.extension.IoC.ApplicationContext;
import designexploder.model.impl.BasicModelFactory;
import designexploder.util.DexUtils;

public class CreateApplicationContextAction extends EditorToolBindedAction {

	private ListenableTool creationTool;
	
	public CreateApplicationContextAction(IEditorPart editor) {
		super(editor);
		setText("Create Application Context");
		setId(DexActionFactory.CREATE_APPLICATION_CONTEXT.getId());
	}
	
	@Override
	protected boolean calculateEnabled() {
		return true;
	}

	@Override
	public void run() {
		IFolder defaultFolder = DexUtils.getDefaultAppsContextFolder(getEditorPart().getEditorInput().getFile().getProject());
		String config = "";
		if(config != null) {
			IWorkbenchPage page = getEditorPart().getSite().getPage();
			page.closeEditor(page.getActiveEditor(), true);
			page.activate(getEditorPart());
			getToolManager().setActiveTool(getCreationTool());
		}
	}

	private ListenableTool getCreationTool() {
		if(creationTool == null) {
			creationTool = new ListenableCreationTool();
			creationTool.addActivationListener(this);
		}
		((ListenableCreationTool) creationTool).setFactory(new DummyCreationFactory(createObject(), getObjectType()));
		return creationTool;
	}

	protected EditorToolManager getToolManager() {
		return (EditorToolManager) getEditorPart().getAdapter(EditorToolManager.class);
	}
	
	protected Object getObjectType() {
		return ApplicationContext.class;
	}
	
	private Object createObject() {
		return BasicModelFactory.getInstance().createContainerNode();
	}

	@Override
	protected DexDiagramEditor getEditorPart() {
		return (DexDiagramEditor) super.getEditorPart();
	}
}