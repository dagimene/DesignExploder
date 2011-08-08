package designexploder.editor.renderers.extension.classnode;

import designexploder.editor.renderers.ConnectionRendererDecorator;
import designexploder.model.Connection;
import designexploder.model.extension.classnode.ClassRelation;
import designexploder.model.extension.common.Nature;

public class ClassConnectionDecorator implements  ConnectionRendererDecorator {

	public Nature getConnectionMainNature(Connection connection) {
		ClassRelation classConnection = connection.getExtension(ClassRelation.class);
		return classConnection != null ? classConnection.getNature() : null;
	}

	public String getConnectionLabel(Connection connection) {
		ClassRelation classConnection = connection.getExtension(ClassRelation.class);
		return classConnection != null ? classConnection.getName() : null;
	}

	public Nature getConnectionEndpointsNature(Connection connection) {
		ClassRelation classConnection = connection.getExtension(ClassRelation.class);
		return classConnection != null ? classConnection.getNature() : null;
	}

}
