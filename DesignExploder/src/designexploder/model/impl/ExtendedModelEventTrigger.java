package designexploder.model.impl;

import java.util.Collection;

import designexploder.model.ModelCollectionAlterEvent;
import designexploder.model.ModelEventType;
import designexploder.model.ModelPropertyChangeEvent;

public class ExtendedModelEventTrigger extends ModelEventTriggerImpl {

	protected void fireModelPropertyChangeEvent(ModelEventType type, Object oldValue, Object newValue) {
		fireEvent(new ModelPropertyChangeEvent(type, oldValue, newValue));
	}

	protected <T> void fireModelCollectionAlterEvent(ModelEventType type, Collection<T> collection, T element) {
		fireEvent(new ModelCollectionAlterEvent<T>(type, collection, element));
	}
}
