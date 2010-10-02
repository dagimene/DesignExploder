package designexploder.editor.controllers;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;

import designexploder.editor.graphics.GraphicsFactory;

public class ConnectionEditPart extends AbstractConnectionEditPart {
	
	@Override
	protected IFigure createFigure() {
		return GraphicsFactory.createConnectionFigure();
	}

	@Override
	protected void createEditPolicies() {}
}
