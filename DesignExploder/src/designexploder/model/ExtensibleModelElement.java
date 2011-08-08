package designexploder.model;

import designexploder.model.event.ModelEventTrigger;
import java.util.Set;

public interface ExtensibleModelElement extends ModelEventTrigger {

	String getId();

	void setId(String id);

	<T extends ModelExtension> void addExtension(T extension);
	
	<T extends ModelExtension> T removeExtension(Class<T> clazz);

	<T extends ModelExtension> T getExtension(Class<T> clazz);
	
	Set<Class<? extends ModelExtension>> getExtensions();
	
}
