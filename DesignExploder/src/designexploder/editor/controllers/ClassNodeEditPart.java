package designexploder.editor.controllers;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;

import designexploder.editor.controllers.policies.ClassNodeComponentEditPolicy;
import designexploder.editor.controllers.policies.ClassNodeRequestsEditPolicy;
import designexploder.editor.graphics.ClassFigure;
import designexploder.editor.graphics.GraphicsFactory;
import designexploder.editor.renderers.BaseNodeRenderer;
import designexploder.editor.renderers.extension.IoC.BeanNodeDecorator;
import designexploder.editor.renderers.extension.classnode.ClassNodeDecorator;
import designexploder.model.extension.classnode.ClassNode;

public class ClassNodeEditPart extends NodeEditPart {

	private BaseNodeRenderer renderer = new BaseNodeRenderer();
	
	public ClassNodeEditPart() {
		renderer.addDecorator(new BeanNodeDecorator());
		renderer.addDecorator(new ClassNodeDecorator());
	}
	
	
	@Override
	protected IFigure createFigure() {
		return GraphicsFactory.createClassFigure();
	}
	
	@Override
	protected void refreshVisuals() {
		renderer.render(getModel(), getFigure());
		super.refreshVisuals();
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

	public ClassNode getClassModel() {
		return getModel().getExtension(ClassNode.class);
	}
}
