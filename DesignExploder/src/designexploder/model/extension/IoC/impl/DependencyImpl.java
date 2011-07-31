package designexploder.model.extension.IoC.impl;

import designexploder.model.Connection;
import designexploder.model.event.ModelEvent;
import designexploder.model.event.ModelEventListener;
import designexploder.model.event.ModelPropertyChangeEvent;
import designexploder.model.extension.IoC.Dependency;
import designexploder.model.extension.IoC.IoCModelEventTypes;
import designexploder.model.extension.classnode.ClassItem;
import designexploder.model.extension.classnode.Method;
import designexploder.model.extension.common.CommonModelEventTypes;
import designexploder.model.extension.common.impl.NaturalizedImpl;

class DependencyImpl extends NaturalizedImpl implements Dependency {

	private ClassItem target;
	private Connection beanInjection;

	public DependencyImpl() {
		addListener(IoCModelEventTypes.TARGET_CHANGED, selfListener);
	}
	
	@Override
	public ClassItem getTarget() {
		return target;
	}

	@Override
	public void setTarget(ClassItem target) {
		if(target.isMethod() && !((Method) target).isSetter()) {
			throw new IllegalArgumentException("Dependency target must be an attribute or setter method.");
		}
		ClassItem oldValue = this.target;
		this.target = target;
		fireModelPropertyChangeEvent(IoCModelEventTypes.TARGET_CHANGED, oldValue, target);
	}

	@Override
	public Connection getBeanInjection() {
		return beanInjection;
	}

	@Override
	public void setBeanInjection(Connection beanInjection) {
		Connection oldValue = this.beanInjection;
		this.beanInjection = beanInjection;
		fireModelPropertyChangeEvent(IoCModelEventTypes.BEAN_INJECTION_CHANGED, oldValue, beanInjection);
	}

	@Override
	public boolean isResolved() {
		return beanInjection != null;
	}

	@Override
	public String getName() {
		return target.isMethod() ? ((Method) target).getProperty() : target.getName();
	}

	private final ModelEventListener selfListener = new ModelEventListener() {
		@Override
		public void processModelEvent(ModelEvent e) {
			if(e.getType() == IoCModelEventTypes.TARGET_CHANGED) {
				@SuppressWarnings("unchecked")
				ModelPropertyChangeEvent<ClassItem> event = (ModelPropertyChangeEvent<ClassItem>) e;
				if(event.getOldValue() != null) {
					event.getOldValue().removeListener(CommonModelEventTypes.NAME_CHANGED, targetListener);
				}
				if(event.getNewValue() != null) {
					event.getNewValue().addListener(CommonModelEventTypes.NAME_CHANGED, targetListener);
				}
			}
		}
	};

	private ModelEventListener targetListener = new ModelEventListener() {
		/**
		 * Forwards a new NAME_CHANGED event to listeners
		 */
		@Override
		public void processModelEvent(ModelEvent e) {
			ModelEvent fowardEvent = e.clone();
			fowardEvent.setSource(DependencyImpl.this);
			fireEvent(fowardEvent);
		}
	};

}
