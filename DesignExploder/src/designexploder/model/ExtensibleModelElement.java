package designexploder.model;

import designexploder.model.event.ModelEventTrigger;

public interface ExtensibleModelElement extends ModelEventTrigger {

	String getId();

	void setId(String id);

	<T extends ModelEventTrigger> void addExtension(Class<T> clazz, T extension);
	
	<T extends ModelEventTrigger> T removeExtension(Class<T> clazz);

	<T extends ModelEventTrigger> T getExtension(Class<T> clazz);
	
}
