package designexploder.editor.controllers;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import designexploder.model.ModelEventListener;
import designexploder.model.ModelEventTrigger;
import designexploder.model.ModelEventType;

public abstract class ModelEventListenerEditPart extends AbstractGraphicalEditPart implements ModelEventListener {

	@Override
	public void activate() {
		super.activate();
		for(ModelEventType type : getListenedProperties(new LinkedList<ModelEventType>())) {
			((ModelEventTrigger)getModel()).addListener(type, this);
		}
	}

	public void deactivate() {
		super.deactivate();
		for(ModelEventType type : getListenedProperties(new LinkedList<ModelEventType>())) {
			((ModelEventTrigger)getModel()).removeListener(type, this);
		}
	}

	protected abstract List<ModelEventType> getListenedProperties(List<ModelEventType> properties);
	
}
