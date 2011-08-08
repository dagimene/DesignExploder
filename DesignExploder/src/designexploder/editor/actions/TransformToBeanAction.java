package designexploder.editor.actions;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IWorkbenchPart;

import designexploder.editor.DexDiagramEditor;
import designexploder.editor.commands.extension.AddExtensionCommand;
import designexploder.editor.commands.extension.RemoveExtensionCommand;
import designexploder.editor.controllers.ClassNodeEditPart;
import designexploder.editor.graphics.styles.StylesFactory;
import designexploder.model.ExtensibleModelElement;
import designexploder.model.Node;
import designexploder.model.extension.IoC.BeanNode;
import designexploder.model.extension.IoC.IoCModelNatures;
import designexploder.model.extension.IoC.IoCModelUtil;
import designexploder.model.extension.IoC.impl.IoCModelFactory;
import designexploder.model.extension.classnode.ClassNode;
import designexploder.model.extension.common.Nature;

public class TransformToBeanAction extends UniqueSelectionAction {

	private static final String TO_BEAN_LABEL = "Become a bean";
	private static final String TO_STATELESS_BEAN_LABEL = "Become a stateless bean";
	private static final String TO_AUTO_IMPLEMENTED_BEAN_LABEL = "Become an autoimplemented bean";
	private static final String TO_CONTEXT_FACTORY_LABEL = "Become a context factory";
	private static final String REMOVE_BEAN_NATURE_LABEL = "Remove bean nature";
	
	private static final Map<IoCModelNatures, String> NATURES_MAP = new HashMap<IoCModelNatures, String>();
	static {
		NATURES_MAP.put(IoCModelNatures.BEAN, TO_BEAN_LABEL);
		NATURES_MAP.put(IoCModelNatures.BEAN_AUTO, TO_AUTO_IMPLEMENTED_BEAN_LABEL);
		NATURES_MAP.put(IoCModelNatures.BEAN_FACTORY, TO_CONTEXT_FACTORY_LABEL);
		NATURES_MAP.put(IoCModelNatures.BEAN_STATELESS, TO_STATELESS_BEAN_LABEL);
	}
	
	public TransformToBeanAction(IWorkbenchPart part) {
		super(part);
		setId(DexActionFactory.TRANSFORM_TO_BEAN.name());
		setTargetEditPartClass(ClassNodeEditPart.class);
	}
	
	@Override
	public void run() {
		Command command;
		Node node = (Node) getModel();
		BeanNode extension = node.getExtension(BeanNode.class);
		if(extension == null) {
			command = getToBeanCommand(node);
		} else {
			command = new RemoveExtensionCommand<BeanNode>(node, BeanNode.class);
		}
		getCommandStack().execute(command);
		((DexDiagramEditor)getWorkbenchPart()).forceUpdateSelectionActions();
	}

	public static Command getToBeanCommand(Node node) {
		ClassNode classNode = node.getExtension(ClassNode.class);
		return getToBeanCommand(node, IoCModelUtil.getBeanNatureFor(classNode, null));
	}
	
	public static Command getToBeanCommand(Node node, IoCModelNatures beanNature) {
		Command command;
		BeanNode extension = IoCModelFactory.getInstance().createBeanNode();
		extension.setNode(node);
		extension.setNature(beanNature);
		command = new AddExtensionCommand<BeanNode>(node, BeanNode.class, extension);
		return command;
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
			ClassNode classNode = ((ExtensibleModelElement) getModel()).getExtension(ClassNode.class);
			return beanNode != null ? REMOVE_BEAN_NATURE_LABEL : NATURES_MAP.get(IoCModelUtil.getBeanNatureFor(classNode, beanNode));
		}
		return TO_BEAN_LABEL;
	}
	
	private ImageDescriptor calculateImageDescriptor() {
		if(isEnabled()) {
			BeanNode beanNode = ((ExtensibleModelElement) getModel()).getExtension(BeanNode.class);
			ClassNode classNode = ((ExtensibleModelElement) getModel()).getExtension(ClassNode.class);
			Nature nature = beanNode != null ? classNode.getNature() :
				IoCModelUtil.getBeanNatureFor(classNode, beanNode);
			Image image = StylesFactory.getInstance().getIconFor(nature).getImage();
			return ImageDescriptor.createFromImage(image);
					
		}
		return null;
	}
}
