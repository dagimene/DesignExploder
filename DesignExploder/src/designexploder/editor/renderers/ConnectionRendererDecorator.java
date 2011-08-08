package designexploder.editor.renderers;

import designexploder.model.Connection;
import designexploder.model.extension.common.Nature;

public interface ConnectionRendererDecorator {

	String getConnectionLabel(Connection connection);
	
	Nature getConnectionMainNature(Connection connection);

	Nature getConnectionEndpointsNature(Connection connection);
	
}
