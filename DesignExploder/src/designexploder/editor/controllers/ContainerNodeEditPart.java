package designexploder.editor.controllers;

import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.GraphicalEditPart;

import designexploder.editor.graphics.ContainerNodeFigure;
import designexploder.editor.graphics.GraphicsFactory;
import designexploder.model.Connection;
import designexploder.model.ContainerNode;
import designexploder.model.event.BasicModelEventTypes;
import designexploder.model.event.ModelEvent;
import designexploder.model.event.ModelEventType;

public class ContainerNodeEditPart extends NodeContainerEditPart {

	@Override
	protected IFigure createFigure() {
		return GraphicsFactory.createContainerNodeFigure();
	}
	
	@Override
	protected void refreshVisuals() {
		((GraphicalEditPart)getParent()).setLayoutConstraint(this, getFigure(), getModel().getBounds());
	}
	
	@Override
	public List<ModelEventType> getListenedProperties(List<ModelEventType> properties) {
		super.getListenedProperties(properties);
		properties.add(BasicModelEventTypes.BOUNDS_CHANGED);
		return properties;
	}
	
	@Override
	protected List<Connection> getModelSourceConnections() {
		return getModel().getOutflows();
	}

	@Override
	protected List<Connection> getModelTargetConnections() {
		return getModel().getInflows();
	}

	@Override
	public void processModelEvent(ModelEvent e) {
		ModelEventType type = e.getType();
		if(type == BasicModelEventTypes.BOUNDS_CHANGED) {
			refresh();
		} else {
			super.processModelEvent(e);
		}
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
