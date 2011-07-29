package designexploder.editor.controllers;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editpolicies.RootComponentEditPolicy;

import designexploder.editor.graphics.GraphicsFactory;

public class RootNodeContainerEditPart extends NodeContainerEditPart {
	
	@Override
	protected IFigure createFigure() {
		return GraphicsFactory.createDiagramFigure();
	}
	
	@Override
	protected void createEditPolicies() {
		super.createEditPolicies();
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new RootComponentEditPolicy());
	}
	
}
