package designexploder.editor.controllers.listeners;

import java.util.LinkedList;
import java.util.List;

import designexploder.model.event.ModelEventListener;
import designexploder.model.event.ModelEventTrigger;
import designexploder.model.event.ModelEventType;

public abstract class AbstractModelEventListenerDelegate implements ModelEventListener {

	protected final ModelEventTrigger model;
	protected final RefreshableEditPart editPart;

	public AbstractModelEventListenerDelegate(ModelEventTrigger model, RefreshableEditPart editPart) {
		this.model = model;
		this.editPart = editPart;
	}
	
	public void activate() {
		for(ModelEventType type : getListenedProperties(new LinkedList<ModelEventType>())) {
			model.addListener(type, this);
		}
	}

	public void deactivate() {
		for(ModelEventType type : getListenedProperties(new LinkedList<ModelEventType>())) {
			model.removeListener(type, this);
		}
	}
	
	protected abstract List<ModelEventType> getListenedProperties(List<ModelEventType> properties);

}
