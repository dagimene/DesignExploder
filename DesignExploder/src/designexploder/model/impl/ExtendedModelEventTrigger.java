package designexploder.model.impl;

import java.util.Collection;

import designexploder.model.event.ModelCollectionAlterEvent;
import designexploder.model.event.ModelEventType;
import designexploder.model.event.ModelPropertyChangeEvent;

public class ExtendedModelEventTrigger extends ModelEventTriggerImpl {

	protected <T> void fireModelPropertyChangeEvent(ModelEventType type, T oldValue, T newValue) {
		fireEvent(new ModelPropertyChangeEvent<T>(type, this, oldValue, newValue));
	}

	protected <T> void fireModelCollectionAlterEvent(ModelEventType type, Collection<T> collection, T element) {
		fireEvent(new ModelCollectionAlterEvent<T>(type, this, collection, element));
	}
}
