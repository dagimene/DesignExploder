package designexploder.editor.controllers;

import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.GraphicalEditPart;

import designexploder.editor.graphics.GraphicsFactory;
import designexploder.model.BasicModelEventTypes;
import designexploder.model.ModelEvent;
import designexploder.model.ModelEventListener;
import designexploder.model.ModelEventType;
import designexploder.model.Node;

public class NodeEditPart extends ModelEventListenerEditPart implements ModelEventListener {
	
	@Override
	protected IFigure createFigure() {
		return GraphicsFactory.createClassFigure();
	}
	
	@Override
	protected void refreshVisuals() {
		Node<?> model = (Node<?>)getModel();
		((GraphicalEditPart)getParent()).setLayoutConstraint(this, getFigure(), model.getBounds());
	}
	
	@Override
	public List<ModelEventType> getListenedProperties(List<ModelEventType> properties) {
		properties.add(BasicModelEventTypes.BOUNDS_CHANGED);
		return properties;
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

	@Override
	public void processModelEvent(ModelEvent e) {
		ModelEventType type = e.getType();
		if(type == BasicModelEventTypes.BOUNDS_CHANGED) {
			refresh();
		}
	}
}