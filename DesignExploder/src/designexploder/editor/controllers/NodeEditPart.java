package designexploder.editor.controllers;

import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.GraphicalEditPart;

import designexploder.editor.graphics.GraphicsFactory;
import designexploder.model.Connection;
import designexploder.model.Node;
import designexploder.model.event.BasicModelEventTypes;
import designexploder.model.event.ModelEvent;
import designexploder.model.event.ModelEventType;

public class NodeEditPart extends ExtensibleModelEditPart {
	
	@Override
	protected IFigure createFigure() {
		return GraphicsFactory.createAbstractNodeFigure();
	}
	
	@Override
	protected void refreshVisuals() {
		((GraphicalEditPart)getParent()).setLayoutConstraint(this, getFigure(), getModel().getBounds());
	}
	
	@Override
	public List<ModelEventType> getListenedProperties(List<ModelEventType> properties) {
		properties.add(BasicModelEventTypes.BOUNDS_CHANGED);
		properties.add(BasicModelEventTypes.OUTFLOW_ADDED);
		properties.add(BasicModelEventTypes.OUTFLOW_REMOVED);
		properties.add(BasicModelEventTypes.INFLOW_ADDED);
		properties.add(BasicModelEventTypes.INFLOW_REMOVED);
		return super.getListenedProperties(properties);
	}
	
	@Override
	protected void createEditPolicies() {}

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
			refreshVisuals();
		} else if(type == BasicModelEventTypes.OUTFLOW_ADDED ||
			type == BasicModelEventTypes.OUTFLOW_REMOVED) {
			refreshSourceConnections();
		} else if(type == BasicModelEventTypes.INFLOW_ADDED ||
			type == BasicModelEventTypes.INFLOW_REMOVED) {
			refreshTargetConnections();
		} else {
			super.processModelEvent(e);
		}
	}
	
	public Node getModel() {
		return (Node) super.getModel();
	}
}