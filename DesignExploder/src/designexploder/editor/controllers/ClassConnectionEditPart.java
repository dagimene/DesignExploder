package designexploder.editor.controllers;

import designexploder.editor.graphics.ClassConnectionFigure;
import designexploder.editor.renderers.ClassConnectionRenderer;
import designexploder.model.Connection;

public class ClassConnectionEditPart extends ConnectionEditPart {

	private ClassConnectionRenderer renderer = new ClassConnectionRenderer();
	
	@Override
	protected void refreshVisuals() {
		renderer.render(getModel(), (ClassConnectionFigure)getFigure());
		super.refreshVisuals();
	}	
	
	@Override
	public Connection getModel() {
		return (Connection) super.getModel();
	}
}
