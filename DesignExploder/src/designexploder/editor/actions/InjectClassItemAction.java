package designexploder.editor.actions;

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchPart;

import designexploder.editor.DexDiagramEditor;
import designexploder.editor.commands.IoC.AddDependencyCommand;
import designexploder.editor.commands.IoC.RemoveDependencyCommand;
import designexploder.editor.controllers.ClassItemEditPart;
import designexploder.model.Node;
import designexploder.model.extension.IoC.BeanNode;
import designexploder.model.extension.IoC.Dependency;
import designexploder.model.extension.IoC.IoCModelUtil;
import designexploder.model.extension.IoC.impl.IoCModelFactory;
import designexploder.model.extension.classnode.Attribute;
import designexploder.model.extension.classnode.ClassItem;
import designexploder.model.extension.classnode.Method;
import designexploder.resources.ClassItemIconProvider;
import designexploder.resources.IconResource;

public class InjectClassItemAction extends UniqueSelectionAction {

	private static final String INJECT_FIELD = "Inject Field";
	private static final String INJECT_METHOD = "Inject Method";
	private static final String REMOVE_INJECTION = "Remove Injection";
	
	public InjectClassItemAction(IWorkbenchPart part) {
		super(part);
		setId(DexActionFactory.INJECT_CLASS_ITEM.name());
		setTargetEditPartClass(ClassItemEditPart.class);
	}
	
	@Override
	public void run() {
		ClassItem classItem = (ClassItem) getModel();
		Dependency dependency = IoCModelUtil.findDependencyFromClassItem(classItem, getNode());
		Command command;
		if(dependency == null) {
			dependency = IoCModelFactory.getInstance().createDependency(classItem);
			command = new AddDependencyCommand(getNode(), dependency);
		} else {
			command = new RemoveDependencyCommand(getNode(), dependency);
		}
		getCommandStack().execute(command);
		((DexDiagramEditor)getWorkbenchPart()).forceUpdateSelectionActions();
	}

	@Override
	protected boolean calculateEnabled() {
		ClassItem item = super.calculateEnabled() ? (ClassItem)getModel() : null;
		return item != null &&
				((item.isAttribute() && ((Attribute) item).getSetter() != null) || (item.isMethod() && ((Method) item).isSetter()))
				&& getNode().getExtension(BeanNode.class) != null;
	}
	
	@Override
	protected void refresh() {
		super.refresh();
		setText(calculateText());
		setImageDescriptor(calculateImageDescriptor());
	}
	
	private String calculateText() {
		if(isEnabled()) {
			ClassItem classItem = (ClassItem) getModel();
			Dependency dependency = IoCModelUtil.findDependencyFromClassItem(classItem, getNode());
			if(dependency != null) {
				return REMOVE_INJECTION;
			} else if(classItem.isAttribute()) {
				return INJECT_FIELD;
			} else {
				return INJECT_METHOD;
			}
		}
		return INJECT_FIELD;
	}
	
	private ImageDescriptor calculateImageDescriptor() {
		if(isEnabled()) {
			ClassItem classItem = (ClassItem) getModel();
			Dependency dependency = IoCModelUtil.findDependencyFromClassItem(classItem, getNode());
			IconResource iconResource = ClassItemIconProvider.getItemIconResource(classItem, dependency == null);
			return ImageDescriptor.createFromImage(iconResource.getImage());
		}
		return null;
	}
	
	private Node getNode() {
		return ((ClassItemEditPart) getEditPart()).getParentModel();
	}
}
