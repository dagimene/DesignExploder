package designexploder.model.extension.IoC;

import designexploder.model.event.ModelEventType;

public interface IoCModelEventTypes {

	// BeanNode
	public static final ModelEventType DEPENDENCY_ADDED  = new ModelEventType.NamedModelEvent("DEPENDENCY_ADDED");
	public static final ModelEventType DEPENDENCY_REMOVED  = new ModelEventType.NamedModelEvent("DEPENDENCY_REMOVED");

	public static final ModelEventType IOC_AWARE_METHOD_ADDED = new ModelEventType.NamedModelEvent("IOC_AWARE_METHOD_ADDED");
	public static final ModelEventType IOC_AWARE_METHOD_REMOVED = new ModelEventType.NamedModelEvent("IOC_AWARE_METHOD_REMOVED");

	// Dependency
	public static final ModelEventType TARGET_CHANGED = new ModelEventType.NamedModelEvent("TARGET_CHANGED");
	public static final ModelEventType BEAN_INJECTION_CHANGED = new ModelEventType.NamedModelEvent("BEAN_INJECTION_CHANGED");

	// IoCAwareMethod
	public static final ModelEventType METHOD_CHANGED = new ModelEventType.NamedModelEvent("METHOD_CHANGED");

}
