package designexploder.model.extension.common;

import designexploder.model.event.ModelEventType;

public interface CommonModelEventTypes {

	/* Named */
	public static final ModelEventType NAME_CHANGED = new ModelEventType.NamedModelEvent("NAME_CHANGED");
	
	/* Naturalized */
	public static final ModelEventType NATURE_CHANGED  = new ModelEventType.NamedModelEvent("NATURE_CHANGED");

}
