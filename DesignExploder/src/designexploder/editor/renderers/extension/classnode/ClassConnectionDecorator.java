package designexploder.editor.renderers.extension.classnode;

import designexploder.editor.renderers.ConnectionRendererDecorator;
import designexploder.model.Connection;
import designexploder.model.extension.classnode.ClassConnection;
import designexploder.model.extension.common.Nature;

public class ClassConnectionDecorator implements  ConnectionRendererDecorator {

	public Nature getConnectionNature(Connection connection) {
		ClassConnection classConnection = connection.getExtension(ClassConnection.class);
		return classConnection != null ? classConnection.getNature() : null;
	}

	public String getConnectionLabel(Connection connection) {
		ClassConnection classConnection = connection.getExtension(ClassConnection.class);
		return classConnection != null ? classConnection.getName() : null;
	}
	
}
