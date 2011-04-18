package designexploder.model.impl;

import designexploder.model.ModelEventType;
import designexploder.model.ModelPropertyChangeEvent;

public class ModelPropertyChangeEventTrigger extends ModelEventTriggerImpl {

	protected void fireModelPropertyChangeEvent(ModelEventType type, Object oldValue, Object newValue) {
		fireEvent(new ModelPropertyChangeEvent(type, oldValue, newValue));
	}
}
