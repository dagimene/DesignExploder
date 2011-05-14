package designexploder.model.classnode;

import designexploder.model.ModelEventType;

public class ClassModelEventTypes {

	/* Named */
	public static final ModelEventType NAME_CHANGED = new ModelEventType.NamedModelEvent("NAME_CHANGED");
	
	/* Typed */
	public static final ModelEventType TYPE_CHANGED = new ModelEventType.NamedModelEvent("TYPE_CHANGED");

	/* Naturalized */
	public static final ModelEventType NATURE_CHANGED  = new ModelEventType.NamedModelEvent("NATURE_CHANGED");
	
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

}
