package designexploder.model.extension.IoC.impl;

import designexploder.model.event.ModelEvent;
import designexploder.model.event.ModelEventListener;
import designexploder.model.event.ModelPropertyChangeEvent;
import designexploder.model.extension.IoC.BeanInjection;
import designexploder.model.extension.IoC.Dependency;
import designexploder.model.extension.IoC.IoCModelEventTypes;
import designexploder.model.extension.common.CommonModelEventTypes;
import designexploder.model.extension.common.Nature;
import designexploder.model.impl.ExtendedModelEventTrigger;

class BeanInjectionImpl extends ExtendedModelEventTrigger implements BeanInjection {

	private Dependency dependency;

	public BeanInjectionImpl() {
		addListener(IoCModelEventTypes.DEPENDENCY_CHANGED, selfListener);
	}
	
	public Dependency getDependency() {
		return dependency;
	}

	public void setDependency(Dependency dependency) {
		Dependency oldValue = this.dependency;
		this.dependency = dependency;
		fireModelPropertyChangeEvent(IoCModelEventTypes.DEPENDENCY_CHANGED, oldValue, this.dependency);
	}

	@Override
	public String getName() {
		return dependency.getName();
	}

	@Override
	public Nature getNature() {
		return dependency.getNature();
	}
	
	private final ModelEventListener selfListener = new ModelEventListener() {
		@Override
		public void processModelEvent(ModelEvent e) {
			if(e.getType() == IoCModelEventTypes.DEPENDENCY_CHANGED) {
				@SuppressWarnings("unchecked")
				ModelPropertyChangeEvent<Dependency> event = (ModelPropertyChangeEvent<Dependency>) e;
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
			fowardEvent.setSource(BeanInjectionImpl.this);
			fireEvent(fowardEvent);
		}
	};

	@Override
	@SuppressWarnings("unchecked")
	public Class<BeanInjection> getExtensionClass() {
		return BeanInjection.class;
	}
}
