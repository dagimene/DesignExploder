package designexploder.model.extension.classnode;

import designexploder.model.extension.common.InmutableNaturalized;
import designexploder.model.extension.common.Named;


public interface Method extends Parameterized, Named, InmutableNaturalized {
	
	// Helper methods
	
	boolean isGetter();
	
	boolean isSetter();
	
	/**
	 * @return Property name as an internalized string.
	 */
	String getProperty();
	
}
