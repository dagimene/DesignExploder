package designexploder.editor.renderers;

import org.eclipse.draw2d.IFigure;

import designexploder.model.ExtensibleModelElement;

public interface Renderer<M extends ExtensibleModelElement, F extends IFigure> {

	void render(M model, F figure);
	
}
