package designexploder.model.extension.classnode;

public interface Method extends Parameterized, ClassItem {
	
	// Helper methods
	
	boolean isGetter();
	
	boolean isSetter();
	
	/**
	 * @return Property name as an internalized string.
	 */
	String getProperty();
	
}
