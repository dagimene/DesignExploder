package designexploder.editor.graphics;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.XYLayout;

public class GraphicsFactory {

	public static IFigure createDiagramFigure() {
		Figure diagram = new Figure();
		diagram.setOpaque(true);
		diagram.setLayoutManager(new XYLayout() {
			@Override
			public void layout(IFigure parent) {
				super.layout(parent);
			}
		});
		return diagram;
	}
	
	public static IFigure createClassFigure() {
		return new ClassFigure();
	}
	
	public static IFigure createConnectionFigure() {
		return new ClassConnectionFigure();
	}
}
