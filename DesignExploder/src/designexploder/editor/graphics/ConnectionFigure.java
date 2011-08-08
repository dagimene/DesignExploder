package designexploder.editor.graphics;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.PolylineConnection;

public class ConnectionFigure extends PolylineConnection {

	public ConnectionFigure(ClassFigure source, ClassFigure target) {
		this();
		ChopboxAnchor sourceAnchor = new ChopboxAnchor(source);
		ChopboxAnchor targetAnchor = new ChopboxAnchor(target);
		setSourceAnchor(sourceAnchor);
		setTargetAnchor(targetAnchor);
		if(source == target) {
			setVisible(false);
		}
	}
	
	public ConnectionFigure() {}

}
