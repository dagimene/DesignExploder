package designexploder.editor.controllers;

import designexploder.editor.graphics.ClassConnectionFigure;
import designexploder.editor.renderers.BaseConnectionRenderer;
import designexploder.editor.renderers.extension.IoC.BeanInjectionDecorator;
import designexploder.editor.renderers.extension.classnode.ClassConnectionDecorator;
import designexploder.model.Connection;

public class ClassConnectionEditPart extends ConnectionEditPart {

	private BaseConnectionRenderer renderer = new BaseConnectionRenderer();
	
	public ClassConnectionEditPart() {
		renderer.addDecorator(new BeanInjectionDecorator());
		renderer.addDecorator(new ClassConnectionDecorator());
	}

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
