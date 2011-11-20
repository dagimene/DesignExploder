package designexploder.editor.controllers;

import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import designexploder.editor.controllers.listeners.ModelEventListenerDelegate;
import designexploder.editor.controllers.listeners.NodeEventListenerDelegate;
import designexploder.editor.controllers.listeners.RefreshableEditPart;
import designexploder.editor.graphics.GraphicsFactory;
import designexploder.model.Connection;
import designexploder.model.Node;

public class NodeEditPart extends AbstractGraphicalEditPart implements RefreshableEditPart {

	private ModelEventListenerDelegate listenerDelegate;
	
	@Override
	public void activate() {
		super.activate();
		if(listenerDelegate == null) {
			listenerDelegate = createListenerDelegate();
		}
		listenerDelegate.activate();
	}

	@Override
	public void deactivate() {
		super.deactivate();
		listenerDelegate.deactivate();
	}
	
	protected ModelEventListenerDelegate createListenerDelegate() {
		return new NodeEventListenerDelegate(getModel(), this);
	}

	@Override
	protected IFigure createFigure() {
		return GraphicsFactory.createAbstractNodeFigure();
	}
	
	@Override
	public void refreshVisuals() {
		((GraphicalEditPart)getParent()).setLayoutConstraint(this, getFigure(), getModel().getBounds());
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

	public Node getModel() {
		return (Node) super.getModel();
	}

	/**
	 * Redirect Requests to node's edit policies.
	 */
	@Override
	public void performRequest(Request request) {
		Command command = null;
		EditPolicyIterator i = getEditPolicyIterator();
		while (i.hasNext()) {
			if (command != null)
				command = command.chain(i.next().getCommand(request));
			else
				command = i.next().getCommand(request);
		}
		getViewer().getEditDomain().getCommandStack().execute(command);
	}

	// Make public
	
	@Override
	public void refreshChildren() {
		super.refreshChildren();
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