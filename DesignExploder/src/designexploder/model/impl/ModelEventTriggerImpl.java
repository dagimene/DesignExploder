package designexploder.model.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import designexploder.model.ModelEvent;
import designexploder.model.ModelEventListener;
import designexploder.model.ModelEventTrigger;
import designexploder.model.ModelEventType;

public class ModelEventTriggerImpl implements ModelEventTrigger {

	private Map<ModelEventType, List<ModelEventListener>> listeners = new HashMap<ModelEventType, List<ModelEventListener>>();
	
	public void addListener(ModelEventType type, ModelEventListener listener) {
		List<ModelEventListener> typeListeners = listeners.get(type);
		if(typeListeners == null) {
			typeListeners = new ArrayList<ModelEventListener>();
			listeners.put(type, typeListeners);
		}
		typeListeners.add(listener);
	}

	public boolean removeListener(ModelEventType type, ModelEventListener listener) {
		List<ModelEventListener> typeListeners = listeners.get(type);
		if(typeListeners != null) {
			return typeListeners.remove(listener);
		}
		return false;
	}
	
	protected void fireEvent(ModelEvent event) {
		List<ModelEventListener> typeListeners = listeners.get(event.getType());
		if(typeListeners != null) {
			for (ModelEventListener listener : typeListeners) {
				listener.processModelEvent(event);
			}
		}
	}
	
	protected void fireEvent(ModelEventType type) {
		fireEvent(new ModelEvent(type));
	}
}
