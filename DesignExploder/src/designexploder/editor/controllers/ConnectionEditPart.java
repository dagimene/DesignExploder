package designexploder.editor.controllers;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;

import designexploder.editor.controllers.listeners.ExtensibleModelListenerDelegate;
import designexploder.editor.controllers.listeners.ModelEventListenerDelegate;
import designexploder.editor.controllers.listeners.RefreshableEditPart;
import designexploder.editor.graphics.GraphicsFactory;
import designexploder.model.Connection;

public class ConnectionEditPart extends AbstractConnectionEditPart implements RefreshableEditPart {
	
	private ModelEventListenerDelegate listenerDelegate;
	
	@Override
	public Connection getModel() {
		return (Connection) super.getModel();
	}
	
	@Override
	public void activate() {
		super.activate();
		if(listenerDelegate == null) {
			listenerDelegate = createListenerDelegate();
		}
		listenerDelegate.activate();
	}

	protected ExtensibleModelListenerDelegate createListenerDelegate() {
		return new ExtensibleModelListenerDelegate(getModel(), this);
	}
	
	@Override
	public void deactivate() {
		super.deactivate();
		listenerDelegate.deactivate();
	}
	
	@Override
	protected IFigure createFigure() {
		return GraphicsFactory.createConnectionFigure();
	}

	@Override
	protected void createEditPolicies() {}

	@Override
	public void refreshSourceConnections() {
		super.refreshSourceConnections();
	}

	@Override
	public void refreshTargetConnections() {
		super.refreshTargetConnections();
	}

	@Override
	public void refreshChildren() {
		super.refreshChildren();
	}

	@Override
	public void refreshVisuals() {
		super.refreshVisuals();
	}
}
