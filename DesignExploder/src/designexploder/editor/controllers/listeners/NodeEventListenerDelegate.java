package designexploder.editor.controllers.listeners;

import java.util.List;

import designexploder.model.ExtensibleModelElement;
import designexploder.model.event.BasicModelEventTypes;
import designexploder.model.event.ModelEvent;
import designexploder.model.event.ModelEventType;

public class NodeEventListenerDelegate extends ExtensibleModelListenerDelegate {

	public NodeEventListenerDelegate(ExtensibleModelElement model, RefreshableEditPart editPart) {
		super(model, editPart);
	}

	@Override
	public List<ModelEventType> getListenedProperties(List<ModelEventType> properties) {
		properties.add(BasicModelEventTypes.BOUNDS_CHANGED);
		properties.add(BasicModelEventTypes.OUTFLOW_ADDED);
		properties.add(BasicModelEventTypes.OUTFLOW_REMOVED);
		properties.add(BasicModelEventTypes.INFLOW_ADDED);
		properties.add(BasicModelEventTypes.INFLOW_REMOVED);
		return super.getListenedProperties(properties);
	}
	
	@Override
	public void processModelEvent(ModelEvent e) {
		ModelEventType type = e.getType();
		if(type == BasicModelEventTypes.BOUNDS_CHANGED) {
			editPart.refreshVisuals();
		} else if(type == BasicModelEventTypes.OUTFLOW_ADDED ||
			type == BasicModelEventTypes.OUTFLOW_REMOVED) {
			editPart.refreshSourceConnections();
		} else if(type == BasicModelEventTypes.INFLOW_ADDED ||
			type == BasicModelEventTypes.INFLOW_REMOVED) {
			editPart.refreshTargetConnections();
		} else {
			super.processModelEvent(e);
		}
	}
	

}
