package designexploder.editor.renderers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import designexploder.editor.graphics.ClassConnectionFigure;
import designexploder.model.Connection;
import designexploder.model.extension.common.Nature;

public class BaseConnectionRenderer implements ConnectionRendererDecorator, Renderer<Connection, ClassConnectionFigure> {

	private List<ConnectionRendererDecorator> decorators = new ArrayList<ConnectionRendererDecorator>();

	/**
	 * Adds a decorator. Decorators must be added in order of importance. The former will override the latter.
	 * @param decorator
	 * @return
	 */
	public boolean addDecorator(ConnectionRendererDecorator decorator) {
		return decorators.add(decorator);
	}

	public boolean removeDecorator(ConnectionRendererDecorator decorator) {
		return decorators.remove(decorator);
	}
	
	@Override
	public void render(Connection connection, ClassConnectionFigure figure) {
		figure.setNature(getConnectionMainNature(connection));
		figure.setEndpointsNature(getConnectionEndpointsNature(connection));
		figure.setLabelText(ClassConnectionFigure.SOURCE, getConnectionLabel(connection));
		
		// Prevent connections from and to the same node to be shown 
		figure.setVisible(connection.getSource() != connection.getTarget());
		
		/*figure.setSourceDecoration(getDecoration(connection, Endpoint.SOURCE));
		figure.setTargetDecoration(getDecoration(connection, Endpoint.TARGET));

		figure.setLabelText(ClassConnectionFigure.SOURCE, cardinalityString(getCardinality(connection, Endpoint.SOURCE)));
		figure.setLabelText(ClassConnectionFigure.TARGET, cardinalityString(getCardinality(connection, Endpoint.TARGET)));
		figure.setLabelText(ClassConnectionFigure.CONNECTION, getConnectionLabel(connection));

		figure.setLineDash(getLinedDash(connection));*/
	
	}
	
	public String getConnectionLabel(Connection connection) {
		Iterator<ConnectionRendererDecorator> iterator = decorators.iterator();
		String result = null;
		while(result == null && iterator.hasNext()) {
			result = iterator.next().getConnectionLabel(connection);
		}
		return result != null ? result : "";
	}

	public Nature getConnectionMainNature(Connection connection) {
		Iterator<ConnectionRendererDecorator> iterator = decorators.iterator();
		Nature result = null;
		while(result == null && iterator.hasNext()) {
			result = iterator.next().getConnectionMainNature(connection);
		}
		return result != null ? result : Nature.NONE;
	}

	@Override
	public Nature getConnectionEndpointsNature(Connection connection) {
		Iterator<ConnectionRendererDecorator> iterator = decorators.iterator();
		Nature result = null;
		while(result == null && iterator.hasNext()) {
			result = iterator.next().getConnectionEndpointsNature(connection);
		}
		return result;
	}
	
	/*
	private String cardinalityString(int cardinality) {
		return cardinality >= 0 ? (cardinality == Integer.MAX_VALUE ? "N": String.valueOf(cardinality)) : null;
	}
	*/
}
