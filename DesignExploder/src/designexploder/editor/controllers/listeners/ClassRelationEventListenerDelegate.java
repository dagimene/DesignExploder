package designexploder.editor.controllers.listeners;

import java.util.List;

import designexploder.model.ExtensibleModelElement;
import designexploder.model.event.ModelEvent;
import designexploder.model.event.ModelEventType;
import designexploder.model.extension.IoC.BeanInjection;
import designexploder.model.extension.classnode.ClassRelation;
import designexploder.model.extension.common.CommonModelEventTypes;

public class ClassRelationEventListenerDelegate extends ExtensibleModelListenerDelegate {

	public ClassRelationEventListenerDelegate(ExtensibleModelElement model, RefreshableEditPart editPart) {
		super(model, editPart);
	}

	@Override
	protected List<ModelEventType> getExtensionListenedProperties(
			Class<?> extension, List<ModelEventType> properties) {
		if(extension == ClassRelation.class ||
				extension == BeanInjection.class) {
			properties.add(CommonModelEventTypes.NAME_CHANGED);
			properties.add(CommonModelEventTypes.NATURE_CHANGED);
		}
		return super.getExtensionListenedProperties(extension, properties);
	}

	@Override
	public void processModelEvent(ModelEvent e) {
		if(e.getType() == CommonModelEventTypes.NAME_CHANGED ||
				e.getType() == CommonModelEventTypes.NATURE_CHANGED) {
			editPart.refreshVisuals();
		}
		super.processModelEvent(e);
	}
	
	

}
