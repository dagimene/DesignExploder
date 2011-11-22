package designexploder.editor.controllers.listeners;

import java.util.LinkedList;
import java.util.List;

import designexploder.model.ExtensibleModelElement;
import designexploder.model.ModelExtension;
import designexploder.model.event.BasicModelEventTypes;
import designexploder.model.event.ModelCollectionAlterEvent;
import designexploder.model.event.ModelEvent;
import designexploder.model.event.ModelEventType;

public class ExtensibleModelListenerDelegate extends AbstractModelEventListenerDelegate {
	
	public ExtensibleModelListenerDelegate(ExtensibleModelElement model, RefreshableEditPart editPart) {
		super(model, editPart);
	}

	@Override
	public void activate() {
		super.activate();
		for (Class<? extends ModelExtension> clazz : ((ExtensibleModelElement) model).getExtensions()) {
			installExtensionListeners(((ExtensibleModelElement) model).getExtension(clazz), true);
		}
	}

	@Override
	public void deactivate() {
		super.deactivate();
		for (Class<? extends ModelExtension> clazz : ((ExtensibleModelElement) model).getExtensions()) {
			installExtensionListeners(((ExtensibleModelElement) model).getExtension(clazz), false);
		}
	}

	@Override
	protected List<ModelEventType> getListenedProperties(List<ModelEventType> properties) {
		properties.add(BasicModelEventTypes.EXTENSION_ADDED);
		properties.add(BasicModelEventTypes.EXTENSION_REMOVED);
		return properties;
	}
	
	@Override
	public void processModelEvent(ModelEvent e) {
		boolean extensionAdded = e.getType() == BasicModelEventTypes.EXTENSION_ADDED;
		if(extensionAdded || e.getType() == BasicModelEventTypes.EXTENSION_REMOVED) {
			@SuppressWarnings("unchecked")
			ModelCollectionAlterEvent<ModelExtension> event = (ModelCollectionAlterEvent<ModelExtension>) e;
			installExtensionListeners(event.getElement(), extensionAdded);
			editPart.refreshVisuals();
		}
	}

	protected void installExtensionListeners(ModelExtension extension, boolean extensionAdded) {
		for(ModelEventType type : getExtensionListenedProperties(extension.getExtensionClass(), new LinkedList<ModelEventType>())) {
			if(extensionAdded) {
				extension.addListener(type, this);
			} else {
				extension.removeListener(type, this);
			}
		}
	}

	protected List<ModelEventType> getExtensionListenedProperties(Class<?> extension, List<ModelEventType> properties) {
		return properties; 
	}
	
}
