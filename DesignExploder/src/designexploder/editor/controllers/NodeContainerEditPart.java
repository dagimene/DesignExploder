package designexploder.editor.controllers;

import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;

import designexploder.editor.controllers.policies.NodeContainerLayoutEditPolicy;
import designexploder.editor.graphics.GraphicsFactory;
import designexploder.model.Node;
import designexploder.model.NodeContainer;
import designexploder.model.event.BasicModelEventTypes;
import designexploder.model.event.ModelCollectionAlterEvent;
import designexploder.model.event.ModelEvent;
import designexploder.model.event.ModelEventType;

public class NodeContainerEditPart extends ModelEventListenerEditPart {
	
	@Override
	protected IFigure createFigure() {
		return GraphicsFactory.createNodeContainerFigure();
	}

	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new NodeContainerLayoutEditPolicy());
	}
	
	@Override
	protected List<Node> getModelChildren() {
		return getModel().getNodes();
	}

	public NodeContainer getModel() {
		return (NodeContainer) super.getModel();
	}

	@Override
	public void processModelEvent(ModelEvent e) {
		ModelEventType type = e.getType();
		if(type == BasicModelEventTypes.NODE_ADDED) {
			EditPart newEditPart = createChild(((ModelCollectionAlterEvent<?>) e).getElement());
			addChild(newEditPart, -1);
		} else if(type == BasicModelEventTypes.NODE_REMOVED) {
			EditPart excedentEditPart = (EditPart) getViewer().getEditPartRegistry().get(((ModelCollectionAlterEvent<?>) e).getElement());
			removeChild(excedentEditPart);
		}
	}

	@Override
	protected List<ModelEventType> getListenedProperties(List<ModelEventType> properties) {
		properties.add(BasicModelEventTypes.NODE_ADDED);
		properties.add(BasicModelEventTypes.NODE_REMOVED);
		return properties;
	}

}
