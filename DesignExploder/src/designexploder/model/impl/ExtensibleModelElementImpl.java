package designexploder.model.impl;

import java.util.HashMap;
import java.util.Map;

import designexploder.model.ExtensibleModelElement;
import designexploder.model.event.BasicModelEventTypes;

class ExtensibleModelElementImpl extends ExtendedModelEventTrigger implements ExtensibleModelElement {

	private Map<Class<?>, Object> extensions = new HashMap<Class<?>, Object>(5);
	private String id;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public <T> T getExtension(Class<T> clazz) {
		return clazz.cast(extensions.get(clazz));
	}

	@Override
	public <T> void addExtension(Class<T> clazz, T extension) {
		extensions.put(clazz, extension);
		fireModelCollectionAlterEvent(BasicModelEventTypes.EXTENSION_ADDED, extensions.keySet(), clazz);
	}

	@Override
	public <T> T removeExtension(Class<T> clazz) {
		T result = clazz.cast(extensions.remove(clazz));
		fireModelCollectionAlterEvent(BasicModelEventTypes.EXTENSION_REMOVED, extensions.keySet(), clazz);
		return result;
	}

}
