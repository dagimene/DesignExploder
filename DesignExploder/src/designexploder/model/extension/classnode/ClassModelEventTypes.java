package designexploder.model.extension.classnode;

import designexploder.model.event.ModelEventType;

public interface ClassModelEventTypes {

	/* Typed */
	public static final ModelEventType TYPE_CHANGED = new ModelEventType.NamedModelEvent("TYPE_CHANGED");

	/* Modifiable */
	public static final ModelEventType MODIFIER_ADDED  = new ModelEventType.NamedModelEvent("MODIFIER_ADDED");
	public static final ModelEventType MODIFIER_REMOVED  = new ModelEventType.NamedModelEvent("MODIFIER_REMOVED");

	/* Parameterized */
	public static final ModelEventType PARAMETER_ADDED  = new ModelEventType.NamedModelEvent("PARAMETER_ADDED");
	public static final ModelEventType PARAMETER_REMOVED  = new ModelEventType.NamedModelEvent("PARAMETER_REMOVED");
	
	/* ClassNode */
	public static final ModelEventType ATTRIBUTE_ADDED  = new ModelEventType.NamedModelEvent("ATTRIBUTE_ADDED");
	public static final ModelEventType ATTRIBUTE_REMOVED  = new ModelEventType.NamedModelEvent("ATTRIBUTE_REMOVED");
	
	public static final ModelEventType METHOD_ADDED  = new ModelEventType.NamedModelEvent("METHOD_ADDED");
	public static final ModelEventType METHOD_REMOVED  = new ModelEventType.NamedModelEvent("METHOD_REMOVED");

	/* Connection */
	public static final ModelEventType ORIGIN_CHANGED = new ModelEventType.NamedModelEvent("ORIGIN_CHANGED");

}
