package designexploder.editor.graphics;

import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineDecoration;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.swt.graphics.Color;

public class EndpointDecorationsFactory {

	public static PolygonDecoration createEmptyDiamond() {
		PolygonDecoration decoration = createFilledDiamond();
		decoration.setBackgroundColor(new Color(null, 255, 255, 255));
		return decoration;		
	}

	public static PolygonDecoration createFilledDiamond() {
		PolygonDecoration decoration = new PolygonDecoration();
		PointList decorationPointList = new PointList();
		decorationPointList.addPoint(0,0);
		decorationPointList.addPoint(-2,2);
		decorationPointList.addPoint(-4,0);
		decorationPointList.addPoint(-2,-2);
		decoration.setTemplate(decorationPointList);
		return decoration;		
	}

	public static PolylineDecoration createOpenArrow() {
		PolylineDecoration decoration = new PolylineDecoration();
		PointList decorationPointList = new PointList();
		decorationPointList.addPoint(-3,-3);
		decorationPointList.addPoint(0,0);
		decorationPointList.addPoint(-3,3);
		decoration.setTemplate(decorationPointList);
		return decoration;		
	}

	public static PolygonDecoration createClosedArrow() {
		PolygonDecoration decoration = new PolygonDecoration();
		PointList decorationPointList = new PointList();
		decorationPointList.addPoint(0,0);
		decorationPointList.addPoint(-3,3);
		decorationPointList.addPoint(-3,-3);
		decoration.setTemplate(decorationPointList);
		decoration.setBackgroundColor(new Color(null, 255, 255, 255));
		return decoration;		
	}
}
