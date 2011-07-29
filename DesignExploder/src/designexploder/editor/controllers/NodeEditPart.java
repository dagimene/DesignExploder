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

public class NodeEditPart extends ModelEventListenerEditPart {
	
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
		return properties;
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
			refresh();
		}
	}
	
	public Node getModel() {
		return (Node) super.getModel();
	}
}