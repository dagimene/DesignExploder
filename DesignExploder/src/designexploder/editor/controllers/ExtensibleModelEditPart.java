package designexploder.editor.controllers;

import java.util.LinkedList;
import java.util.List;

import designexploder.model.ExtensibleModelElement;
import designexploder.model.event.BasicModelEventTypes;
import designexploder.model.event.ModelCollectionAlterEvent;
import designexploder.model.event.ModelEvent;
import designexploder.model.event.ModelEventTrigger;
import designexploder.model.event.ModelEventType;

public abstract class ExtensibleModelEditPart extends ModelEventListenerEditPart {

	@Override
	public List<ModelEventType> getListenedProperties(List<ModelEventType> properties) {
		properties.add(BasicModelEventTypes.EXTENSION_ADDED);
		properties.add(BasicModelEventTypes.BEFORE_EXTENSION_REMOVED);
		properties.add(BasicModelEventTypes.EXTENSION_REMOVED);
		return properties;
	}
	
	@Override
	public void processModelEvent(ModelEvent e) {
		boolean added = e.getType() == BasicModelEventTypes.EXTENSION_ADDED;
		if(added || e.getType() == BasicModelEventTypes.BEFORE_EXTENSION_REMOVED) {
			@SuppressWarnings("unchecked")
			ModelCollectionAlterEvent<Class<? extends ModelEventTrigger>> event = (ModelCollectionAlterEvent<Class<? extends ModelEventTrigger>>) e;
			Class<? extends ModelEventTrigger> clazz = event.getElement();
			ModelEventTrigger extension = ((ExtensibleModelElement) getModel()).getExtension(clazz);
			for(ModelEventType type : getExtensionListenedProperties(clazz, new LinkedList<ModelEventType>())) {
				if(added) {
					extension.addListener(type, this);
				} else {
					extension.removeListener(type, this);
				}
			}
		}
		if(added || e.getType() == BasicModelEventTypes.EXTENSION_REMOVED) { 
			refreshVisuals();
		}
	}

	public List<ModelEventType> getExtensionListenedProperties(Class<?> extension, List<ModelEventType> properties) {
		return properties; 
	}
	
}
