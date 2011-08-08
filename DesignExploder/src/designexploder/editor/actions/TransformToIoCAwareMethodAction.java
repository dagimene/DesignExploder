package designexploder.editor.actions;

import java.util.Set;

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IWorkbenchPart;

import designexploder.editor.DexDiagramEditor;
import designexploder.editor.commands.IoC.AddIoCAwareMethodCommand;
import designexploder.editor.commands.IoC.RemoveIoCAwareMethodCommand;
import designexploder.editor.controllers.ClassItemEditPart;
import designexploder.editor.graphics.styles.StylesFactory;
import designexploder.model.Node;
import designexploder.model.extension.IoC.BeanNode;
import designexploder.model.extension.IoC.IoCAwareMethod;
import designexploder.model.extension.IoC.IoCModelNatures;
import designexploder.model.extension.IoC.impl.IoCModelFactory;
import designexploder.model.extension.classnode.ClassItem;
import designexploder.model.extension.classnode.ClassNode;
import designexploder.model.extension.classnode.Method;
import designexploder.model.extension.common.Nature;
import designexploder.util.adt.ADTUtil;
import designexploder.util.adt.Condition;

/**
 * Transforms Methods in IoCAwareMethods according to the next nature criteria:
 *  
 *  !Abstract, Static && Method type = ClassNode type => Factory
 *  !Abstract, !Static => Init
 *  Abstract, !Static, !Final, Method type = void => Context Instantiate  
 *  
 * @author Daniel
 */
public class TransformToIoCAwareMethodAction extends UniqueSelectionAction {

	private static final String BECOME_INIT = "Become an init method";
	private static final String BECOME_INSTANTIATE = "Become a context instantiator method";
	private static final String BECOME_FACTORY = "Become a bean factory method";
	private static final String REMOVE_IOC_AWARENESS = "Remove IoC awarness";

	public TransformToIoCAwareMethodAction(IWorkbenchPart part) {
		super(part);
		setId(DexActionFactory.TRANSFORM_TO_IOC_AWARE_METHOD.name());
		setTargetEditPartClass(ClassItemEditPart.class);
	}
	
	@Override
	public void run() {
		Method method = (Method) getModel();
		Node node = getNode();
		IoCAwareMethod iocAwareMethod = getIoCAwareMethod(method, node);
		Command command;
		if(iocAwareMethod != null) {
			command = new RemoveIoCAwareMethodCommand(node, iocAwareMethod);
		} else {
			iocAwareMethod = IoCModelFactory.getInstance().createIoCAwareMethod();
			iocAwareMethod.setTarget((Method) getModel());
			iocAwareMethod.setNature(getCandidateNature(method, node));
			command = new AddIoCAwareMethodCommand(node, iocAwareMethod); 
		}
		getCommandStack().execute(command);
		((DexDiagramEditor)getWorkbenchPart()).forceUpdateSelectionActions();
	}
	
	private Node getNode() {
		return ((ClassItemEditPart) getEditPart()).getParentModel();
	}
	
	@Override
	protected boolean calculateEnabled() {
		ClassItem item = super.calculateEnabled() ? (ClassItem)getModel() : null;
		if(item != null && item.isMethod() && getNode().getExtension(BeanNode.class) != null) {
			Method method = (Method) getModel();
			Node node = getNode();
			return getIoCAwareMethod(method, node) != null || isFactoryCandidate(method, node) || isInitCandidate(method) || isInstantiate(method);
		}
		return false;
	}

	@Override
	protected void refresh() {
		super.refresh();
		setText(calculateText());
		setImageDescriptor(calculateImageDescriptor());
	}
	
	private String calculateText() {
		ClassItem item = getModel() instanceof ClassItem ? (ClassItem)getModel() : null;
		if(item != null && item.isMethod() && getNode().getExtension(BeanNode.class) != null) {
			Method method = (Method) getModel();
			Node node = getNode();
			if(getIoCAwareMethod(method, node) != null) {
				return REMOVE_IOC_AWARENESS;
			} else if(isInstantiate(method)) {
				return BECOME_INSTANTIATE;
			} else if(isInitCandidate(method)) {
				return BECOME_INIT;
			} else if(isFactoryCandidate(method, node)) {
				return BECOME_FACTORY;
			}
		}
		return BECOME_FACTORY;
	}
	
	private ImageDescriptor calculateImageDescriptor() {
		ClassItem item = getModel() instanceof ClassItem ? (ClassItem)getModel() : null;
		if(item != null && item.isMethod() && getNode().getExtension(BeanNode.class) != null) {
			Nature nature = getCandidateNature((Method) getModel(), getNode());
			if(nature != null) {
				Image image = StylesFactory.getInstance().getIconFor(nature).getImage();
				return ImageDescriptor.createFromImage(image);
			}
		}
		return null;
	}
	
	private static Nature getCandidateNature(Method method, Node node) {
		Nature nature = null;
		if(isInstantiate(method)) {
			nature = IoCModelNatures.IOC_METHOD_INSTANTIATE;
		} else if(isInitCandidate(method)) {
			nature = IoCModelNatures.IOC_METHOD_INIT;
		} else if(isFactoryCandidate(method, node)){
			nature = IoCModelNatures.IOC_METHOD_FACTORY; 
		}
		return nature;
	}
	
	private static IoCAwareMethod getIoCAwareMethod(final Method method, Node node) {
		BeanNode beanNode = node.getExtension(BeanNode.class);
		if(beanNode != null) {
			Set<IoCAwareMethod> filtered = ADTUtil.filterCollection(beanNode.getIoCAwareMethods(), new Condition<IoCAwareMethod>() {
				@Override
				public boolean check(IoCAwareMethod e) {
					return e.getTarget() == method;
				}
			});
			if(!filtered.isEmpty()) {
				return filtered.iterator().next(); 
			}
		}
		return null;
	}

	private static boolean isFactoryCandidate(Method method, Node node) {
		return !method.isAbstract() && method.isStatic() &&
					method.getType() == node.getExtension(ClassNode.class).getType();
	}
	
	private static boolean isInitCandidate(Method method) {
		return !method.isAbstract() && !method.isStatic();
	}
	
	private static boolean isInstantiate(Method method) {
		return method.isAbstract() && !method.isStatic() && !method.isFinal() &&
				method.getType().isBasic() && method.getType().getName() == "void";
	}
}