package designexploder.editor.controllers;

import org.eclipse.draw2d.IFigure;

import designexploder.editor.graphics.ClassFigure;
import designexploder.editor.graphics.GraphicsFactory;
import designexploder.editor.renderers.ClassFigureRenderer;
import designexploder.model.extension.classnode.ClassNode;

public class ClassNodeEditPart extends NodeEditPart {

	private ClassFigureRenderer renderer = new ClassFigureRenderer();
	
	@Override
	protected IFigure createFigure() {
		return GraphicsFactory.createClassFigure();
	}
	
	@Override
	protected void refreshVisuals() {
		renderer.render(getModel(), getFigure());
		super.refreshVisuals();
	}

	@Override
	public ClassFigure getFigure() {
		return (ClassFigure) super.getFigure();
	}

	public ClassNode getClassModel() {
		return getModel().getExtension(ClassNode.class);
	}
}
