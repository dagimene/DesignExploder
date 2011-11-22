package designexploder.editor.controllers;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;

import designexploder.editor.controllers.listeners.ClassNodeEventListenerDelegate;
import designexploder.editor.controllers.listeners.AbstractModelEventListenerDelegate;
import designexploder.editor.controllers.listeners.RefreshableEditPart;
import designexploder.editor.controllers.policies.ClassNodeComponentEditPolicy;
import designexploder.editor.controllers.policies.ClassNodeRequestsEditPolicy;
import designexploder.editor.graphics.ClassFigure;
import designexploder.editor.graphics.GraphicsFactory;
import designexploder.editor.renderers.BaseNodeRenderer;
import designexploder.editor.renderers.extension.IoC.BeanNodeDecorator;
import designexploder.editor.renderers.extension.classnode.ClassNodeDecorator;
import designexploder.model.extension.classnode.ClassItem;
import designexploder.model.extension.classnode.ClassNode;

public class ClassNodeEditPart extends NodeEditPart {

	private BaseNodeRenderer renderer = new BaseNodeRenderer();
	
	public ClassNodeEditPart() {
		renderer.addDecorator(new BeanNodeDecorator());
		renderer.addDecorator(new ClassNodeDecorator());
	}
	
	@Override
	protected AbstractModelEventListenerDelegate createListenerDelegate() {
		return new ClassNodeEventListenerDelegate(getModel(), this);
	}

	@Override
	protected IFigure createFigure() {
		return GraphicsFactory.createClassFigure();
	}
	
	@Override
	public void refreshVisuals() {
		renderer.render(getModel(), getFigure());
		super.refreshVisuals();
		for (Object editPart : getChildren()) {
			((RefreshableEditPart) editPart).refreshVisuals();
		}
	}

	@Override
	public ClassFigure getFigure() {
		return (ClassFigure) super.getFigure();
	}
	
	@Override
	protected void createEditPolicies() {
		super.createEditPolicies();
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new ClassNodeComponentEditPolicy());
		installEditPolicy(EditPolicy.NODE_ROLE, new ClassNodeRequestsEditPolicy());
	}
	
	@Override
	protected List<ClassItem> getModelChildren() {
		ClassNode classNode = getModel().getExtension(ClassNode.class);
		ArrayList<ClassItem> classItems = new ArrayList<ClassItem>();
		if(classNode != null) {
			classItems.addAll(classNode.getAttributes());
			classItems.addAll(classNode.getMethods());
		}
		return classItems;
	}
	
	@Override
	protected void addChildVisual(EditPart childEditPart, int index) {
		IFigure figure = ((GraphicalEditPart) childEditPart).getFigure();
		if(((ClassItemEditPart)childEditPart).getModel().isAttribute()) {
			getFigure().getAttributesCompartment().add(figure, index);
		} else {
			getFigure().getMethodsCompartment().add(figure, index - getFigure().getAttributesCompartment().getChildren().size());
		}
	}

	@Override
	protected void removeChildVisual(EditPart childEditPart) {
		IFigure figure = ((GraphicalEditPart) childEditPart).getFigure();
		if(((ClassItem)((ClassItemEditPart)childEditPart).getModel()).isAttribute()) {
			getFigure().getAttributesCompartment().remove(figure);
		} else {
			getFigure().getMethodsCompartment().remove(figure);
		}
	}

	public ClassNode getClassModel() {
		return getModel().getExtension(ClassNode.class);
	}

	public void refreshClassItem(ClassItem target) {
		ClassNode classNode = getModel().getExtension(ClassNode.class);
		int index;
		if(target.isAttribute()) {
			index = classNode.getAttributes().indexOf(target);
		} else {
			index = classNode.getAttributes().size() + classNode.getMethods().indexOf(target);
		}
		((ClassItemEditPart)getChildren().get(index)).refreshVisuals();
	}

}
