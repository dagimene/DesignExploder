package designexploder.editor.controllers;

import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.GraphicalEditPart;

import designexploder.editor.graphics.ContainerNodeFigure;
import designexploder.editor.graphics.GraphicsFactory;
import designexploder.model.Connection;
import designexploder.model.ContainerNode;

public class ContainerNodeEditPart extends NodeContainerEditPart {

	@Override
	protected IFigure createFigure() {
		return GraphicsFactory.createContainerNodeFigure();
	}
	
	@Override
	public void refreshVisuals() {
		((GraphicalEditPart)getParent()).setLayoutConstraint(this, getFigure(), getModel().getBounds());
	}
	
	@Override
	protected List<Connection> getModelSourceConnections() {
		return getModel().getOutflows();
	}

	@Override
	protected List<Connection> getModelTargetConnections() {
		return getModel().getInflows();
	}

	public ContainerNode getModel() {
		return (ContainerNode) super.getModel();
	}

	@Override
	public IFigure getContentPane() {
		return getFigure().getChildrenLayer();
	}

	@Override
	public ContainerNodeFigure getFigure() {
		return ((ContainerNodeFigure)super.getFigure());
	}	

}
