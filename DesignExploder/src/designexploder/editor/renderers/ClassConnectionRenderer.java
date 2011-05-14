package designexploder.editor.renderers;

import org.eclipse.draw2d.RotatableDecoration;

import designexploder.editor.graphics.ClassConnectionFigure;
import designexploder.editor.graphics.EndpointDecorationsFactory;
import designexploder.model.classnode.ClassConnection;
import designexploder.model.classnode.DexConstant;

public class ClassConnectionRenderer implements Renderer<ClassConnection, ClassConnectionFigure> {

	@Override
	public void render(ClassConnection model, ClassConnectionFigure figure) {
		
		RotatableDecoration decoration;
		
		switch (model.getNature()) {
		case COMPOSITION:
			decoration = EndpointDecorationsFactory.createFilledDiamond();
			break;
		case AGREGATION:
			decoration = EndpointDecorationsFactory.createEmptyDiamond();
			break;
		case HIERARCHY:
		case REALIZATION:
			decoration = EndpointDecorationsFactory.createClosedArrow();
			break;
		//case ASSOCIATION:
		default:
			decoration = EndpointDecorationsFactory.createOpenArrow();
		}
		
		switch (model.getNature()) {
		case COMPOSITION:
		case AGREGATION:
			figure.setTargetDecoration(null);
			figure.setSourceDecoration(decoration);
			break;
		//case ASSOCIATION:
		//case HIERARCHY:
		//case REALIZATION:
		default:
			figure.setSourceDecoration(null);
			figure.setTargetDecoration(decoration);
		}
			
		figure.setLineDash(model.getNature() == DexConstant.REALIZATION ? new float[] {5f, 5f} : null);
		
		figure.setLabelText(ClassConnectionFigure.SOURCE, cardinalityString(model.getSourceCardinality()));
		figure.setLabelText(ClassConnectionFigure.TARGET, cardinalityString(model.getTargetCardinality()));
		figure.setLabelText(ClassConnectionFigure.CONNECTION, model.getName());
	}
	
	private String cardinalityString(int cardinality) {
		return cardinality >= 0 ? (cardinality == Integer.MAX_VALUE ? "N": String.valueOf(cardinality)) : null;
	}

}
