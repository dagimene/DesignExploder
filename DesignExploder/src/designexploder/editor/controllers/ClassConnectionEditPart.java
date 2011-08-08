package designexploder.editor.controllers;

import designexploder.editor.controllers.listeners.ClassRelationEventListenerDelegate;
import designexploder.editor.controllers.listeners.ExtensibleModelListenerDelegate;
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
	public void refreshVisuals() {
		renderer.render(getModel(), (ClassConnectionFigure)getFigure());
		super.refreshVisuals();
	}	
	
	@Override
	public Connection getModel() {
		return (Connection) super.getModel();
	}
	
	protected ExtensibleModelListenerDelegate createListenerDelegate() {
		return new ClassRelationEventListenerDelegate(getModel(), this);
	}
}
