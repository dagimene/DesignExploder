package designexploder.editor.graphics;

import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.geometry.PointList;

public class EndpointDecorationsFactory {

	public static PolygonDecoration createDiamond() {
		PolygonDecoration decoration = new PolygonDecoration();
		PointList decorationPointList = new PointList();
		decorationPointList.addPoint(0,0);
		decorationPointList.addPoint(-2,2);
		decorationPointList.addPoint(-4,0);
		decorationPointList.addPoint(-2,-2);
		decoration.setTemplate(decorationPointList);
		return decoration;		
	}
	
}
