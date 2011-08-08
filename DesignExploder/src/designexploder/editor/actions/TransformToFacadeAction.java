package designexploder.editor.actions;

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IWorkbenchPart;

import designexploder.editor.DexDiagramEditor;
import designexploder.editor.commands.ChangeNatureCommand;
import designexploder.editor.controllers.ClassNodeEditPart;
import designexploder.editor.graphics.styles.StylesFactory;
import designexploder.model.ExtensibleModelElement;
import designexploder.model.Node;
import designexploder.model.extension.IoC.BeanNode;
import designexploder.model.extension.IoC.IoCModelNatures;
import designexploder.model.extension.IoC.IoCModelUtil;
import designexploder.model.extension.classnode.ClassNode;
import designexploder.model.extension.common.Nature;

public class TransformToFacadeAction extends UniqueSelectionAction {

	private static final String TO_FACADE_BEAN_LABEL = "Become a facade bean";
	private static final String TO_NON_FACADE_BEAN_LABEL = "Stop being a facade bean";
	
	public TransformToFacadeAction(IWorkbenchPart part) {
		super(part);
		setId(DexActionFactory.TRANSFORM_TO_FACADE.name());
		setTargetEditPartClass(ClassNodeEditPart.class);
	}
	
	@Override
	public void run() {
		Command command;
		Node node = (Node) getModel();
		BeanNode beanNode = node.getExtension(BeanNode.class);
		if(beanNode == null) {
			command = TransformToBeanAction.getToBeanCommand(node, IoCModelNatures.BEAN_FACADE);
		} else {
			command = new ChangeNatureCommand(beanNode, isFacade(beanNode) ? 
				IoCModelUtil.getBeanNatureFor(node.getExtension(ClassNode.class), beanNode) :
				IoCModelNatures.BEAN_FACADE);
		}
		getCommandStack().execute(command);
		((DexDiagramEditor)getWorkbenchPart()).forceUpdateSelectionActions();
	}

	@Override
	protected void refresh() {
		super.refresh();
		setText(calculateText());
		setImageDescriptor(calculateImageDescriptor());
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
			BeanNode beanNode = ((ExtensibleModelElement) getModel()).getExtension(BeanNode.class);
			return isFacade(beanNode) ? TO_NON_FACADE_BEAN_LABEL : TO_FACADE_BEAN_LABEL;
		}
		return TO_FACADE_BEAN_LABEL;
	}

	private ImageDescriptor calculateImageDescriptor() {
		if(isEnabled()) {
			BeanNode beanNode = ((ExtensibleModelElement) getModel()).getExtension(BeanNode.class);
			ClassNode classNode = ((ExtensibleModelElement) getModel()).getExtension(ClassNode.class);
			Nature nature = isFacade(beanNode) ?
					IoCModelUtil.getBeanNatureFor(classNode, beanNode) :
					IoCModelNatures.BEAN_FACADE;
			Image image = StylesFactory.getInstance().getIconFor(nature).getImage();
			return ImageDescriptor.createFromImage(image);
		}
		return null;
	}

	private boolean isFacade(BeanNode beanNode) {
		return beanNode != null && beanNode.getNature() == IoCModelNatures.BEAN_FACADE;
	}
}
