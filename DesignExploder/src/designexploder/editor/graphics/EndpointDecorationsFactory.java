package designexploder.editor.graphics;

import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineDecoration;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.swt.graphics.Color;

public enum EndpointDecorationsFactory {

	OPEN_ARROW {
		public RotatableDecoration create() {
			PolylineDecoration decoration = new PolylineDecoration();
			PointList decorationPointList = new PointList();
			decorationPointList.addPoint(-3,-3);
			decorationPointList.addPoint(0,0);
			decorationPointList.addPoint(-3,3);
			decoration.setTemplate(decorationPointList);
			return decoration;
		}
	},
	CLOSED_ARROW {
		public RotatableDecoration create() {
			PolygonDecoration decoration = new PolygonDecoration();
			PointList decorationPointList = new PointList();
			decorationPointList.addPoint(0,0);
			decorationPointList.addPoint(-3,3);
			decorationPointList.addPoint(-3,-3);
			decoration.setTemplate(decorationPointList);
			decoration.setBackgroundColor(new Color(null, 255, 255, 255));
			return decoration;
		}
	},
	EMPTY_DIAMOND {
		public RotatableDecoration create() {
			RotatableDecoration decoration = FILLED_DIAMOND.create();
			decoration.setBackgroundColor(new Color(null, 255, 255, 255));
			return decoration;
		}
	},
	FILLED_DIAMOND {
		public RotatableDecoration create() {
			PolygonDecoration decoration = new PolygonDecoration();
			PointList decorationPointList = new PointList();
			decorationPointList.addPoint(0,0);
			decorationPointList.addPoint(-2,2);
			decorationPointList.addPoint(-4,0);
			decorationPointList.addPoint(-2,-2);
			decoration.setTemplate(decorationPointList);
			return decoration;
		}
	};

    public abstract RotatableDecoration create();
	
}
