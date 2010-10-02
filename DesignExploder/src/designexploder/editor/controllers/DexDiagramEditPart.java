package designexploder.editor.controllers;

import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import designexploder.editor.graphics.GraphicsFactory;
import designexploder.model.Diagram;

public class DexDiagramEditPart extends AbstractGraphicalEditPart {
	
	@Override
	protected IFigure createFigure() {
		return GraphicsFactory.createDiagramFigure();
	}
	
	@Override
	protected void createEditPolicies() {}
	
	@SuppressWarnings("rawtypes")
	@Override
	protected List getModelChildren() {
		return ((Diagram)getModel()).getNodes();
	}
}
