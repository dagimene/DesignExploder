package designexploder.model.build;

import java.util.Iterator;

import org.eclipse.draw2d.geometry.Rectangle;

public interface ModelDataProvider {

	Iterator<? extends NodeDataProvider> getNodes();
	
	Iterator<? extends ConnectionDataProvider> getConnections();
	
	public interface NodeDataProvider {
		Rectangle getBounds();
	}

	public interface ConnectionDataProvider { 
		
		String getSourceID();
		
		String getTargetID();
		
	}
}
