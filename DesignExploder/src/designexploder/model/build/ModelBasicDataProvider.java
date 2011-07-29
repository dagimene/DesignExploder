package designexploder.model.build;

import java.util.Iterator;

import designexploder.model.Connection;
import designexploder.model.Node;
import designexploder.model.NodeContainer;

public interface ModelBasicDataProvider {

	Iterator<? extends NodeBasicDataProvider> getNodes();
	
	Iterator<? extends ConnectionBasicDataProvider> getConnections();
	
	public interface NodeBasicDataProvider {

		/**
		 * @return An id, or null if no id is specified.
		 */
		String getId();
		
		void setBasicData(Node node, NodeContainer root);

	}

	public interface ConnectionBasicDataProvider { 
		
		/**
		 * @return An id, or null if no id is specified.
		 */
		String getId();
		
		String getSourceID();
		
		String getTargetID();

		void setBasicData(Connection connection, Node source, Node target, NodeContainer root);

	}
}
