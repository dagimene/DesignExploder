package designexploder.editor.graphics;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.XYLayout;

import designexploder.model.Node;

public class GraphicsFactory {

	public static IFigure createDiagramFigure() {
		Figure diagram = new Figure();
		diagram.setOpaque(true);
		diagram.setLayoutManager(new XYLayout());
		return diagram;
	}
	
	public static IFigure createNodeFigure() {
		return new ClassBox();
	}
	
	public static void update(IFigure f, Node n) {
		((ClassBox)f).setLabel(n.getLabel());
	}

	public static IFigure createConnectionFigure() {
		return new ConnectionFigure();
	}
}
