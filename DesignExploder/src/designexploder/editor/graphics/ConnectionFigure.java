package designexploder.editor.graphics;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.PolylineConnection;

public class ConnectionFigure extends PolylineConnection {

	public ConnectionFigure(ClassBox source, ClassBox target) {
		
		ChopboxAnchor sourceAnchor = new ChopboxAnchor(source);
		ChopboxAnchor targetAnchor = new ChopboxAnchor(target);
		setSourceAnchor(sourceAnchor);
		setTargetAnchor(targetAnchor);
		setSourceDecoration(EndpointDecorationsFactory.createDiamond());
	}

}
