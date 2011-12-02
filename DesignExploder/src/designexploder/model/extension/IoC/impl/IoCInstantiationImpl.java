package designexploder.model.extension.IoC.impl;

import designexploder.model.event.ModelEvent;
import designexploder.model.event.ModelEventListener;
import designexploder.model.event.ModelPropertyChangeEvent;
import designexploder.model.extension.IoC.*;
import designexploder.model.extension.common.CommonModelEventTypes;
import designexploder.model.extension.common.Nature;
import designexploder.model.impl.ExtendedModelEventTrigger;

class IoCInstantiationImpl extends ExtendedModelEventTrigger implements IoCInstantiation {

	private TargetedIoCAwareMethod targetedIoCAwareMethod;

	public IoCInstantiationImpl() {
		addListener(IoCModelEventTypes.IOC_AWARE_FACTORY_METHOD_CHANGED, selfListener);
	}

    public void setTargetedIoCAwareMethod(TargetedIoCAwareMethod targetedIoCAwareMethod) {
        TargetedIoCAwareMethod oldValue = this.targetedIoCAwareMethod;
        this.targetedIoCAwareMethod = targetedIoCAwareMethod;
        fireModelPropertyChangeEvent(IoCModelEventTypes.IOC_AWARE_FACTORY_METHOD_CHANGED, oldValue, this.targetedIoCAwareMethod);
    }

    public TargetedIoCAwareMethod getTargetedIoCAwareMethod() {
        return targetedIoCAwareMethod;
    }

	@Override
	public String getName() {
		return targetedIoCAwareMethod.getName();
	}

	@Override
	public Nature getNature() {
        return targetedIoCAwareMethod.getNature();
	}

	private final ModelEventListener selfListener = new ModelEventListener() {
		@Override
		public void processModelEvent(ModelEvent e) {
			if(e.getType() == IoCModelEventTypes.IOC_AWARE_FACTORY_METHOD_CHANGED) {
				@SuppressWarnings("unchecked")
				ModelPropertyChangeEvent<TargetedIoCAwareMethod> event = (ModelPropertyChangeEvent<TargetedIoCAwareMethod>) e;
				if(event.getOldValue() != null) {
					event.getOldValue().removeListener(CommonModelEventTypes.NAME_CHANGED, targetListener);
					event.getOldValue().removeListener(CommonModelEventTypes.NATURE_CHANGED, targetListener);
				}
				if(event.getNewValue() != null) {
					event.getNewValue().addListener(CommonModelEventTypes.NAME_CHANGED, targetListener);
					event.getNewValue().addListener(CommonModelEventTypes.NATURE_CHANGED, targetListener);
				}
			}
		}
	};

	private ModelEventListener targetListener = new ModelEventListener() {
		/**
		 * Forwards a new NAME_CHANGED and NATURE_CHANGED event to listeners
		 */
		@Override
		public void processModelEvent(ModelEvent e) {
			ModelEvent fowardEvent = e.clone();
			fowardEvent.setSource(IoCInstantiationImpl.this);
			fireEvent(fowardEvent);
		}
	};

	@Override
	@SuppressWarnings("unchecked")
	public Class<IoCInstantiation> getExtensionClass() {
		return IoCInstantiation.class;
	}
}
