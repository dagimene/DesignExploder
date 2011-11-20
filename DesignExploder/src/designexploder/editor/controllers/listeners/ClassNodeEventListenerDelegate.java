package designexploder.editor.controllers.listeners;

import java.util.List;

import designexploder.editor.controllers.ClassNodeEditPart;
import designexploder.model.ExtensibleModelElement;
import designexploder.model.ModelExtension;
import designexploder.model.event.*;
import designexploder.model.extension.IoC.BeanNode;
import designexploder.model.extension.IoC.ClassItemTargeted;
import designexploder.model.extension.IoC.Dependency;
import designexploder.model.extension.IoC.IoCModelEventTypes;
import designexploder.model.extension.classnode.ClassItem;
import designexploder.model.extension.classnode.ClassModelEventTypes;
import designexploder.model.extension.common.CommonModelEventTypes;
import designexploder.model.extension.common.Naturalized;
import designexploder.model.extension.common.NodeDesignProperties;

public class ClassNodeEventListenerDelegate extends NodeEventListenerDelegate {

	public ClassNodeEventListenerDelegate(ExtensibleModelElement model, RefreshableEditPart editPart) {
		super(model, editPart);
	}
	
	@Override
	public void processModelEvent(ModelEvent e) {
        if(e.getType() == CommonModelEventTypes.NATURE_CHANGED || e.getType() == ClassModelEventTypes.SHOW_INHERITED_MEMBERS_CHANGED) {
			editPart.refreshVisuals();
		} else if(e.getType() == IoCModelEventTypes.DEPENDENCY_ADDED || 
				e.getType() == IoCModelEventTypes.IOC_AWARE_METHOD_ADDED) {
			ClassItemTargeted element = (ClassItemTargeted) ((ModelCollectionAlterEvent<?>)e).getElement();
			installNatureListener(element);
			updateClassItem(element.getTarget());
		} else if(e.getType() == IoCModelEventTypes.DEPENDENCY_REMOVED ||
				e.getType() == IoCModelEventTypes.IOC_AWARE_METHOD_REMOVED) {
			ClassItemTargeted element = (ClassItemTargeted) ((ModelCollectionAlterEvent<?>)e).getElement();
			removeNatureListener(element);
			updateClassItem(element.getTarget());
		} else {
			super.processModelEvent(e);
		}
	}

    @Override
	protected void installExtensionListeners(ModelExtension extension, boolean extensionAdded) {
		super.installExtensionListeners(extension, extensionAdded);
		if((Class<?>)extension.getExtensionClass() == BeanNode.class) {
			for (Dependency dependency : ((BeanNode) extension).getDependencies()) {
				if(extensionAdded) {
					installNatureListener(dependency);
				} else {
					removeNatureListener(dependency);
				}
			}
		}
	}

	private void removeNatureListener(Naturalized naturalized) {
		naturalized.removeListener(CommonModelEventTypes.NATURE_CHANGED, classItemTargetedListener);
	}

	private void installNatureListener(Naturalized naturalized) {
		naturalized.addListener(CommonModelEventTypes.NATURE_CHANGED, classItemTargetedListener);
	}

	@Override
	protected List<ModelEventType> getExtensionListenedProperties(
			Class<?> extension, List<ModelEventType> properties) {
		if(extension == BeanNode.class) {
			properties.add(IoCModelEventTypes.DEPENDENCY_ADDED);
			properties.add(IoCModelEventTypes.DEPENDENCY_REMOVED);
			properties.add(IoCModelEventTypes.IOC_AWARE_METHOD_ADDED);
			properties.add(IoCModelEventTypes.IOC_AWARE_METHOD_REMOVED);
			properties.add(CommonModelEventTypes.NATURE_CHANGED);
		} else if (extension == NodeDesignProperties.class) {
            properties.add(ClassModelEventTypes.SHOW_INHERITED_MEMBERS_CHANGED);
        }
		return super.getExtensionListenedProperties(extension, properties);
	}
	
	private ModelEventListener classItemTargetedListener = new ModelEventListener() {
		@Override
		public void processModelEvent(ModelEvent e) {
			if(e.getType() == CommonModelEventTypes.NATURE_CHANGED) {
				ClassItemTargeted element = (ClassItemTargeted) e.getSource();
				updateClassItem(element.getTarget());
			}
		}
	};

	private void updateClassItem(ClassItem target) {
		((ClassNodeEditPart)editPart).refreshClassItem(target);
	}
}