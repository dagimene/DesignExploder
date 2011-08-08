package designexploder.editor.controllers.listeners;

import java.util.List;

import designexploder.model.ExtensibleModelElement;
import designexploder.model.event.BasicModelEventTypes;
import designexploder.model.event.ModelEvent;
import designexploder.model.event.ModelEventType;

public class ContainerNodeEventListenerDelegate extends NodeEventListenerDelegate {

	public ContainerNodeEventListenerDelegate(ExtensibleModelElement model, RefreshableEditPart editPart) {
		super(model, editPart);
	}
	
	@Override
	public void processModelEvent(ModelEvent e) {
		ModelEventType type = e.getType();
		if(type == BasicModelEventTypes.NODE_ADDED || type == BasicModelEventTypes.NODE_REMOVED) {
			editPart.refreshChildren();
		} else {
			super.processModelEvent(e);
		}
	}

	@Override
	public List<ModelEventType> getListenedProperties(List<ModelEventType> properties) {
		properties.add(BasicModelEventTypes.NODE_ADDED);
		properties.add(BasicModelEventTypes.NODE_REMOVED);
		return super.getListenedProperties(properties);
	}

}
