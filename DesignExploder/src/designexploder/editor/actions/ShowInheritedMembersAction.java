package designexploder.editor.actions;

import designexploder.editor.DexDiagramEditor;
import designexploder.editor.commands.classnode.ToggleInheritedMembersCommand;
import designexploder.editor.controllers.ClassNodeEditPart;
import designexploder.model.ExtensibleModelElement;
import designexploder.model.Node;
import designexploder.model.extension.classnode.ClassNode;
import designexploder.model.extension.common.NodeDesignProperties;
import org.eclipse.ui.IWorkbenchPart;

public class ShowInheritedMembersAction extends UniqueSelectionAction {

	private static final String SHOW_LABEL = "Shot inherited members";
	private static final String HIDE_LABEL = "Hide inherited members";

	public ShowInheritedMembersAction(IWorkbenchPart part) {
		super(part);
		setId(DexActionFactory.SHOW_INHERITED_MEMBERS.name());
		setTargetEditPartClass(ClassNodeEditPart.class);
	}
	
	@Override
	public void run() {
        ToggleInheritedMembersCommand command = new ToggleInheritedMembersCommand((Node) getModel());
		getCommandStack().execute(command);
		((DexDiagramEditor)getWorkbenchPart()).forceUpdateSelectionActions();
	}

	@Override
	protected void refresh() {
		super.refresh();
		setText(calculateText());
	}

	@Override
	protected boolean calculateEnabled() {
		if(super.calculateEnabled()) {
			ClassNode classNode = ((ExtensibleModelElement) getModel()).getExtension(ClassNode.class);
			return classNode != null;
		}
		return false;
	}

	private String calculateText() {
		if(isEnabled()) {
			NodeDesignProperties extension = ((ExtensibleModelElement) getModel()).getExtension(NodeDesignProperties.class);
            return (extension != null && extension.isShowInheritedMembers()) ? HIDE_LABEL : SHOW_LABEL;
		}
		return SHOW_LABEL;
	}
	
}
