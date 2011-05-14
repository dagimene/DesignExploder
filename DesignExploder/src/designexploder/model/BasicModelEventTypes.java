package designexploder.model;

public class BasicModelEventTypes {

	/* Node */
	public static final ModelEventType LOCATION_CHANGED = new ModelEventType.NamedModelEvent("LOCATION_CHANGED");
	public static final ModelEventType SIZE_CHANGED = new ModelEventType.NamedModelEvent("SIZE_CHANGED");
	public static final ModelEventType BOUNDS_CHANGED = new ModelEventType.NamedModelEvent("BOUNDS_CHANGED");
	public static final ModelEventType OUTFLOW_ADDED = new ModelEventType.NamedModelEvent("OUTFLOWS_ADDED");
	public static final ModelEventType OUTFLOW_REMOVED = new ModelEventType.NamedModelEvent("OUTFLOWS_REMOVED");
	public static final ModelEventType INFLOW_ADDED = new ModelEventType.NamedModelEvent("INFLOW_ADDED");
	public static final ModelEventType INFLOW_REMOVED = new ModelEventType.NamedModelEvent("INFLOW_REMOVED");

	/* Diagram */
	public static final ModelEventType NODE_ADDED  = new ModelEventType.NamedModelEvent("NODE_ADDED");
	public static final ModelEventType NODE_REMOVED  = new ModelEventType.NamedModelEvent("NODE_REMOVED");
	
	/* Connection */
	public static final ModelEventType SOURCE_CHANGED  = new ModelEventType.NamedModelEvent("SOURCE_CHANGED");
	public static final ModelEventType TARGET_CHANGED  = new ModelEventType.NamedModelEvent("TARGET_CHANGED");

}
