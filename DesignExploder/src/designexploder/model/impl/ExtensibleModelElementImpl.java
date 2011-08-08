package designexploder.model.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import designexploder.model.ExtensibleModelElement;
import designexploder.model.ModelExtension;
import designexploder.model.event.BasicModelEventTypes;

abstract class ExtensibleModelElementImpl extends ExtendedModelEventTrigger implements ExtensibleModelElement {

	private Map<Class<? extends ModelExtension>, ModelExtension> extensions = new HashMap<Class<? extends ModelExtension>, ModelExtension>(2);
	private String id;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public <T extends ModelExtension> T getExtension(Class<T> clazz) {
		return clazz.cast(extensions.get(clazz));
	}

	@Override
	public <T extends ModelExtension> void addExtension(T extension) {
		extensions.put(extension.getExtensionClass(), extension);
		fireModelCollectionAlterEvent(BasicModelEventTypes.EXTENSION_ADDED, extensions.values(), extension);
	}

	@Override
	public <T extends ModelExtension> T removeExtension(Class<T> clazz) {
		T result = clazz.cast(extensions.remove(clazz));
		fireModelCollectionAlterEvent(BasicModelEventTypes.EXTENSION_REMOVED, extensions.values(), result);
		return result;
	}
	
	@Override
	public Set<Class<? extends ModelExtension>> getExtensions() {
		return extensions.keySet();
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName() + "[" + getId() + "]";
	}

}
