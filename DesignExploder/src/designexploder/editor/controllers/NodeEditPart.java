package designexploder.editor.controllers;

import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import designexploder.editor.graphics.GraphicsFactory;
import designexploder.model.Node;

public class NodeEditPart extends AbstractGraphicalEditPart {
	
	private static final Dimension NO_DIM = new Dimension(-1, -1);
	
	@Override
	protected IFigure createFigure() {
		return GraphicsFactory.createNodeFigure();
	}
	
	@Override
	protected void refreshVisuals() {
		Node model = (Node)getModel();
		GraphicsFactory.update(getFigure(), model);
		((GraphicalEditPart)getParent()).setLayoutConstraint(this, getFigure(), new Rectangle(model.getLocation(), NO_DIM));
	}
	
	@Override
	protected void createEditPolicies() {}

	@Override
	protected List getModelSourceConnections() {
		return ((Node)getModel()).getOutflows();
	}

	@Override
	protected List getModelTargetConnections() {
		return ((Node)getModel()).getInflows();
	}
	
	
	
}