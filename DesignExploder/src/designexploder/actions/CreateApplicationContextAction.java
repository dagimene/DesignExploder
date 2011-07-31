package designexploder.actions;

import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;

import designexploder.editor.DexDiagramEditor;
import designexploder.editor.tools.EditorToolManager;
import designexploder.editor.tools.ListenableCreationTool;
import designexploder.editor.tools.ListenableTool;
import designexploder.model.ContainerNode;
import designexploder.model.extension.IoC.ApplicationContext;
import designexploder.model.extension.IoC.impl.IoCModelFactory;
import designexploder.model.impl.BasicModelFactory;
import designexploder.util.adt.IdUtil;

public class CreateApplicationContextAction extends EditorToolBindedAction {

	private ListenableTool creationTool;
	private String id;
	
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
		InputDialog dialog = new InputDialog(Display.getCurrent().getActiveShell(),
				"Create New Context", "Enter context name", "AppContext",
				new ApplicationContextNameValidator(getEditorPart().getModel()));
		dialog.setBlockOnOpen(true);
		int result = dialog.open();
		if(result == Window.OK) {
			id = dialog.getValue() + ".xml";
			getEditorPart().getToolManager().setActiveTool(getCreationTool());
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
		ContainerNode node = BasicModelFactory.getInstance().createContainerNode();
		node.setResizeable(true);
		node.setId(IdUtil.createContextId(id).toString());
		ApplicationContext context = IoCModelFactory.getInstance().createApplicationContext();
		node.addExtension(ApplicationContext.class, context);
		return node;
	}

	@Override
	protected DexDiagramEditor getEditorPart() {
		return (DexDiagramEditor) super.getEditorPart();
	}
}