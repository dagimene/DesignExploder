package designexploder.model;

import designexploder.model.event.ModelEventTrigger;

public interface ModelExtension extends ModelEventTrigger {
	
	<T extends ModelExtension> Class<T> getExtensionClass();

}
