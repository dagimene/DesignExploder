package designexploder.editor.renderers;

import org.eclipse.draw2d.IFigure;

public interface Renderer<M, F extends IFigure> {

	void render(M model, F figure);
	
}
