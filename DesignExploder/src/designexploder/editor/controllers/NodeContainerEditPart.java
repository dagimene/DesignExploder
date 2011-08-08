package designexploder.editor.controllers;

import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import designexploder.editor.controllers.listeners.ContainerNodeEventListenerDelegate;
import designexploder.editor.controllers.listeners.ModelEventListenerDelegate;
import designexploder.editor.controllers.listeners.RefreshableEditPart;
import designexploder.editor.controllers.policies.NodeContainerLayoutEditPolicy;
import designexploder.editor.graphics.GraphicsFactory;
import designexploder.model.Node;
import designexploder.model.NodeContainer;

public class NodeContainerEditPart extends AbstractGraphicalEditPart implements RefreshableEditPart {
	
	private ModelEventListenerDelegate listenerDelegate;
	
	@Override
	public void activate() {
		super.activate();
		if(listenerDelegate == null) {
			listenerDelegate = createListenerDelegate();
		}
		listenerDelegate.activate();
	}

	private ModelEventListenerDelegate createListenerDelegate() {
		return new ContainerNodeEventListenerDelegate(getModel(), this);
	}

	@Override
	public void deactivate() {
		super.deactivate();
		listenerDelegate.deactivate();
	}

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
	public void refreshChildren() {
		super.refreshChildren();
	}

	@Override
	public void refreshVisuals() {
		super.refreshVisuals();
	}
	
	@Override
	public void refreshSourceConnections() {
		super.refreshSourceConnections();
	}
	
	@Override
	public void refreshTargetConnections() {
		super.refreshTargetConnections();
	}
}
