package designexploder.model;

import designexploder.model.event.ModelEventTrigger;

public interface ExtensibleModelElement extends ModelEventTrigger {

	String getId();

	void setId(String id);

	<T> void addExtension(Class<T> clazz, T extension);
	
	<T> T removeExtension(Class<T> clazz);

	<T> T getExtension(Class<T> clazz);
	
}
