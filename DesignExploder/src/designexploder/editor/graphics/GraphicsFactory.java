package designexploder.editor.graphics;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.XYLayout;

public class GraphicsFactory {

	public static IFigure createDiagramFigure() {
		Figure diagram = new Figure();
		diagram.setOpaque(true);
		diagram.setLayoutManager(new XYLayout());
		return diagram;
	}
	
	public static IFigure createAbstractNodeFigure() {
		return new ClassFigure();
	}

	public static IFigure createClassFigure() {
		return new ClassFigure();
	}
	
	public static IFigure createConnectionFigure() {
		return new ClassConnectionFigure();
	}

	public static IFigure createContainerNodeFigure() {
		ContainerNodeFigure result = new ContainerNodeFigure();
		result.setLayoutManager(new XYLayout());
		return result;
	}

	public static IFigure createApplicationContextFigure() {
		return new ApplicationContextFigure();
	}

	public static IFigure createNodeContainerFigure() {
		return new ContainerNodeFigure();
	}
}
