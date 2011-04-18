package designexploder.model;

public class BasicModelEventTypes {

	/* Node */
	public static final ModelEventType LOCATION_CHANGED = new ModelEventType.NamedModelEvent("LOCATION_CHANGED");
	public static final ModelEventType SIZE_CHANGED = new ModelEventType.NamedModelEvent("SIZE_CHANGED");
	public static final ModelEventType BOUNDS_CHANGED = new ModelEventType.NamedModelEvent("BOUNDS_CHANGED");
	public static final ModelEventType OUTFLOWS_CHANGED = new ModelEventType.NamedModelEvent("OUTFLOWS_CHANGED");
	public static final ModelEventType INFLOWS_CHANGED = new ModelEventType.NamedModelEvent("INFLOWS_CHANGED");
	public static final ModelEventType CONNECTIONS_CHANGED = new ModelEventType.NamedModelEvent("CONNECTIONS_CHANGED");

	/* Named */
	public static final ModelEventType NAME_CHANGED = new ModelEventType.NamedModelEvent("NAME_CHANGED");
	
	/* Naturalized */
	public static final ModelEventType NATURE_CHANGED  = new ModelEventType.NamedModelEvent("NATURE_CHANGED");
	
	/* Diagram */
	public static final ModelEventType NODES_CHANGED  = new ModelEventType.NamedModelEvent("NODES_CHANGED");
	
	/* Connection */
	public static final ModelEventType SOURCE_CHANGED  = new ModelEventType.NamedModelEvent("SOURCE_CHANGED");
	public static final ModelEventType TARGET_CHANGED  = new ModelEventType.NamedModelEvent("TARGET_CHANGED");

}
