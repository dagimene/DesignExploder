package designexploder.editor.renderers;

import org.eclipse.draw2d.RotatableDecoration;

import designexploder.editor.graphics.ClassConnectionFigure;
import designexploder.editor.graphics.EndpointDecorationsFactory;
import designexploder.model.Connection;
import designexploder.model.extension.classnode.ClassConnection;
import designexploder.model.extension.common.Nature;

import static designexploder.model.extension.classnode.ClassModelNatures.*;

public class ClassConnectionRenderer implements Renderer<Connection, ClassConnectionFigure> {

	@Override
	public void render(Connection connection, ClassConnectionFigure figure) {

		figure.setSourceDecoration(getDecoration(connection, Endpoint.SOURCE));
		figure.setTargetDecoration(getDecoration(connection, Endpoint.TARGET));

		figure.setLabelText(ClassConnectionFigure.SOURCE, cardinalityString(getCardinality(connection, Endpoint.SOURCE)));
		figure.setLabelText(ClassConnectionFigure.TARGET, cardinalityString(getCardinality(connection, Endpoint.TARGET)));
		figure.setLabelText(ClassConnectionFigure.CONNECTION, getName(connection));

		figure.setLineDash(getLinedDash(connection));
	
	}
		
	public float[] getLinedDash(Connection connection) {
		ClassConnection classConnection = connection.getExtension(ClassConnection.class);
		return classConnection.getNature() == REALIZATION ? new float[] {5f, 5f} : null;
	}

	public String getName(Connection connection) {
		ClassConnection classConnection = connection.getExtension(ClassConnection.class);
		return classConnection.getName();
	}


	public int getCardinality(Connection connection, Endpoint endpoint) {
		ClassConnection classConnection = connection.getExtension(ClassConnection.class);
		return endpoint == Endpoint.SOURCE ? classConnection.getSourceCardinality() : classConnection.getTargetCardinality();
	}

	public RotatableDecoration getDecoration(Connection connection, Endpoint endpoint) {
		ClassConnection classConnection = connection.getExtension(ClassConnection.class);
		if(getDecoratedEndpoint(classConnection.getNature()) == endpoint) {
			return getDecoration(classConnection.getNature());
		}
		return null;
	}

	private RotatableDecoration getDecoration(Nature nature) {
		if(nature == COMPOSITION) {
			return EndpointDecorationsFactory.createFilledDiamond();
		}
		if(nature == AGREGATION) {
			return EndpointDecorationsFactory.createEmptyDiamond();
		}
		if(nature == HIERARCHY || nature == REALIZATION) {
			return EndpointDecorationsFactory.createClosedArrow();
		}
		return EndpointDecorationsFactory.createOpenArrow();
	}

	private Endpoint getDecoratedEndpoint(Nature nature) {
		if(nature == COMPOSITION || nature == AGREGATION) {
			return Endpoint.SOURCE;
		}
		return Endpoint.TARGET;
	}
	
	private String cardinalityString(int cardinality) {
		return cardinality >= 0 ? (cardinality == Integer.MAX_VALUE ? "N": String.valueOf(cardinality)) : null;
	}
	
	static enum Endpoint {
		TARGET, SOURCE
	}

}
