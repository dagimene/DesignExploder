package designexploder.editor.controllers;

import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import designexploder.editor.graphics.GraphicsFactory;
import designexploder.model.Node;

public class NodeEditPart extends AbstractGraphicalEditPart {
	
	@Override
	protected IFigure createFigure() {
		return GraphicsFactory.createClassFigure();
	}
	
	@Override
	protected void refreshVisuals() {
		Node model = (Node)getModel();
		((GraphicalEditPart)getParent()).setLayoutConstraint(this, getFigure(), model.getBounds());
	}
	
	@Override
	protected void createEditPolicies() {}

	@Override
	@SuppressWarnings("rawtypes")
	protected List getModelSourceConnections() {
		return ((Node)getModel()).getOutflows();
	}

	@Override
	@SuppressWarnings("rawtypes")
	protected List getModelTargetConnections() {
		return ((Node)getModel()).getInflows();
	}
}