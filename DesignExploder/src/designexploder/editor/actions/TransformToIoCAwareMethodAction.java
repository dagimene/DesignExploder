package designexploder.editor.actions;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import designexploder.model.NodeContainer;
import designexploder.model.extension.IoC.IoCModelUtil;
import designexploder.model.extension.classnode.*;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
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

	private static final String BECOME_INIT = "Become a bean initialization method";
    private static final String BECOME_FINALIZE = "Become a bean finalization method";
    private static final String BECOME_FACTORY = "Become a bean factory method";
	private static final String BECOME_INSTANTIATE = "Become a context instantiator method";
    private static final String BECOME_ACTIVATE = "Become a context activator method";
    private static final String BECOME_DESTROY = "Become a context destroyer method";
    private static final String REMOVE_IOC_AWARENESS = "Remove IoC awareness";

    private static final Map<IoCModelNatures, String> labels = new HashMap<IoCModelNatures, String>();

    public static Set<IoCModelNatures> getSupportedNatures() {
        return labels.keySet();
    }

    static {
        labels.put(IoCModelNatures.IOC_METHOD_INIT, BECOME_INIT);
        labels.put(IoCModelNatures.IOC_METHOD_FINALIZE, BECOME_FINALIZE);
        labels.put(IoCModelNatures.IOC_METHOD_FACTORY, BECOME_FACTORY);
        labels.put(IoCModelNatures.IOC_METHOD_INSTANTIATE, BECOME_INSTANTIATE);
        labels.put(IoCModelNatures.IOC_METHOD_ACTIVATE, BECOME_ACTIVATE);
        labels.put(IoCModelNatures.IOC_METHOD_DESTROY, BECOME_DESTROY);
    }

    private final IoCModelNatures targetNature;

    public TransformToIoCAwareMethodAction(IWorkbenchPart part, IoCModelNatures targetNature) {
		super(part);
        this.targetNature = targetNature;
        setId(targetNature.name());
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
            command = removeOtherMethodsNature(targetNature);
			iocAwareMethod = IoCModelFactory.getInstance().createIoCAwareMethod();
			iocAwareMethod.setTarget((Method) getModel());
            iocAwareMethod.setNature(targetNature);
            Command addCommand = new AddIoCAwareMethodCommand(node, iocAwareMethod);
            if(command == null) {
			    command = addCommand;
            } else {
                ((CompoundCommand)command).add(addCommand);
            }
		}
		getCommandStack().execute(command);
		((DexDiagramEditor)getWorkbenchPart()).forceUpdateSelectionActions();
	}

    private Command removeOtherMethodsNature(IoCModelNatures iocMethodNature) {
        CompoundCommand command = null;
        Node node = getNode();
        Set<IoCAwareMethod> ioCAwareMethods = getIoCAwareMethods(node, iocMethodNature);
        if(!ioCAwareMethods.isEmpty()) {
            command = new CompoundCommand();
            for(IoCAwareMethod method : ioCAwareMethods) {
                command.add(new RemoveIoCAwareMethodCommand(node, method));
            }
        }
        return command;
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
            IoCModelNatures currentNature = getIoCAwareMethodNature(method, node);
            return currentNature == null ? isCompatible(method, node, targetNature)
                    : currentNature == targetNature;
		}
		return false;
	}

    private IoCModelNatures getIoCAwareMethodNature(Method method, Node node) {
        IoCAwareMethod ioCAwareMethod = getIoCAwareMethod(method, node);
        return ioCAwareMethod != null ? (IoCModelNatures) ioCAwareMethod.getNature() : null;
    }

    private static boolean isCompatible(Method method, Node node, IoCModelNatures targetNature) {
        boolean result;
        switch (targetNature) {
            case IOC_METHOD_INSTANTIATE:
                result = IoCModelUtil.isContextInstantiateMethod(method, node.getNodeContainer());
                break;
            case IOC_METHOD_ACTIVATE:
            case IOC_METHOD_DESTROY:
                result = IoCModelUtil.isInsideContext(node) && IoCModelUtil.isReplaceableMethod(method);
                break;
            case IOC_METHOD_INIT:
            case IOC_METHOD_FINALIZE:
                result = IoCModelUtil.isBeanCycleMethod(method);
                break;
            case IOC_METHOD_FACTORY:
                result = IoCModelUtil.isFactoryCandidate(method, node);
                break;
            default:
                throw new IllegalArgumentException("Unsupported Nature");
        }
        return result;
    }

    @Override
	protected void refresh() {
		super.refresh();
		setText(calculateText());
		setImageDescriptor(calculateImageDescriptor());
	}
	
	private String calculateText() {
		ClassItem item = getModel() instanceof ClassItem ? (ClassItem)getModel() : null;
		return (item != null && item.isMethod() && getNode().getExtension(BeanNode.class) != null &&
                getIoCAwareMethod((Method) getModel(), getNode()) != null) ?
                REMOVE_IOC_AWARENESS : labels.get(targetNature);
	}
	
	private ImageDescriptor calculateImageDescriptor() {
        if(isEnabled()) {
            Image image = StylesFactory.getInstance().getIconFor(targetNature).getImage();
            return ImageDescriptor.createFromImage(image);
        }
        return null;
	}
	
    private static Set<IoCAwareMethod> getIoCAwareMethods(Node node, final Nature nature) {
        BeanNode beanNode = node.getExtension(BeanNode.class);
        return beanNode == null ? null :
            ADTUtil.filterCollection(beanNode.getIoCAwareMethods(), new Condition<IoCAwareMethod>() {
                @Override
                public boolean check(IoCAwareMethod e) {
                    return e.getNature() == nature;
                }
            });
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



}
