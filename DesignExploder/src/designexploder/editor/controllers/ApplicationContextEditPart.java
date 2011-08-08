package designexploder.editor.controllers;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;

import designexploder.editor.controllers.policies.ApplicationContextComponentEditPolicy;
import designexploder.editor.graphics.ApplicationContextFigure;
import designexploder.editor.graphics.GraphicsFactory;
import designexploder.model.extension.IoC.ApplicationContext;

public class ApplicationContextEditPart extends ContainerNodeEditPart {

	@Override
	protected IFigure createFigure() {
		return GraphicsFactory.createApplicationContextFigure();
	}

	@Override
	protected void createEditPolicies() {
		super.createEditPolicies();
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new ApplicationContextComponentEditPolicy());
	}
	
	@Override
	public void refreshVisuals() {
		super.refreshVisuals();
		getFigure().setLabel(getModel().getId());
	}

	@Override
	public ApplicationContextFigure getFigure() {
		return (ApplicationContextFigure) super.getFigure();
	}
	
	public ApplicationContext getApplicationContextModel() {
		return getModel().getExtension(ApplicationContext.class);
	}	
	
}
