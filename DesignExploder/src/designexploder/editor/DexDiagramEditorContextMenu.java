package designexploder.editor;

import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.actions.ActionFactory;

import designexploder.editor.actions.DexActionFactory;

public class DexDiagramEditorContextMenu extends ContextMenuProvider {

	private static final String GROUP_IoC_ARTIFACTS = "GROUP_BEAN_METHOD";
	private static final String GROUP_BEAN_NATURE = "GROUP_BEAN_NATURE";
	private static final String GROUP_EDIT = "GROUP_EDIT";
	private ActionRegistry actionRegistry;
	
	public DexDiagramEditorContextMenu(EditPartViewer viewer, ActionRegistry actionRegistry) {
		super(viewer);
		this.actionRegistry = actionRegistry;
	}

	@Override
	public void buildContextMenu(IMenuManager menu) {
		
		menu.add(new Separator(GROUP_BEAN_NATURE));
		menu.add(new Separator(GROUP_IoC_ARTIFACTS));
		menu.add(new Separator(GROUP_EDIT));

		IAction action = actionRegistry.getAction(ActionFactory.DELETE.getId());
		menu.appendToGroup(GROUP_EDIT, action);
		
		action = actionRegistry.getAction(DexActionFactory.TRANSFORM_TO_BEAN.name());
		menu.appendToGroup(GROUP_BEAN_NATURE, action);
		
		action = actionRegistry.getAction(DexActionFactory.TRANSFORM_TO_FACADE.name());
		menu.appendToGroup(GROUP_BEAN_NATURE, action);
			
		action = actionRegistry.getAction(DexActionFactory.TRANSFORM_TO_IOC_AWARE_METHOD.name());
		menu.appendToGroup(GROUP_IoC_ARTIFACTS, action);

		action = actionRegistry.getAction(DexActionFactory.INJECT_CLASS_ITEM.name());
		menu.appendToGroup(GROUP_IoC_ARTIFACTS, action);

	}

}
