package designexploder.editor.renderers;

import org.eclipse.draw2d.IFigure;

import designexploder.model.event.ModelEventTrigger;

public interface Renderer<M extends ModelEventTrigger, F extends IFigure> {

	void render(M model, F figure);
	
}
