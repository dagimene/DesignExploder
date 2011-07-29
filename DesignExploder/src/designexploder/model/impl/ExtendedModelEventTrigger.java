package designexploder.model.impl;

import java.util.Collection;

import designexploder.model.event.ModelCollectionAlterEvent;
import designexploder.model.event.ModelEventType;
import designexploder.model.event.ModelPropertyChangeEvent;

public class ExtendedModelEventTrigger extends ModelEventTriggerImpl {

	protected void fireModelPropertyChangeEvent(ModelEventType type, Object oldValue, Object newValue) {
		fireEvent(new ModelPropertyChangeEvent(type, this, oldValue, newValue));
	}

	protected <T> void fireModelCollectionAlterEvent(ModelEventType type, Collection<T> collection, T element) {
		fireEvent(new ModelCollectionAlterEvent<T>(type, this, collection, element));
	}
}
