package designexploder.editor.controllers;

import designexploder.editor.graphics.ClassConnectionFigure;
import designexploder.editor.renderers.ClassConnectionRenderer;
import designexploder.model.classnode.ClassConnection;

public class ClassConnectionEditPart extends ConnectionEditPart {

	private ClassConnectionRenderer renderer = new ClassConnectionRenderer();
	
	@Override
	protected void refreshVisuals() {
		renderer.render((ClassConnection)getModel(), (ClassConnectionFigure)getFigure());
		super.refreshVisuals();
	}	
}
