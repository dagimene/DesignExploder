package designexploder.model.extension.IoC.impl;

import designexploder.model.Connection;
import designexploder.model.extension.IoC.Dependency;
import designexploder.model.extension.IoC.IoCModelEventTypes;
import designexploder.model.extension.common.InmutableNaturalized;
import designexploder.model.impl.ExtendedModelEventTrigger;

class DependencyImpl extends ExtendedModelEventTrigger implements Dependency {

	private InmutableNaturalized target;
	private Connection beanInjection;

	@Override
	public InmutableNaturalized getTarget() {
		return target;
	}

	@Override
	public void setTarget(InmutableNaturalized target) {
		InmutableNaturalized oldValue = this.target;
		this.target = target;
		fireModelPropertyChangeEvent(IoCModelEventTypes.TARGET_CHANGED, oldValue, this.target);
	}

	@Override
	public Connection getBeanInjection() {
		return beanInjection;
	}

	@Override
	public void setBeanInjection(Connection beanInjection) {
		Connection oldValue = this.beanInjection;
		this.beanInjection = beanInjection;
		fireModelPropertyChangeEvent(IoCModelEventTypes.BEAN_INJECTION_CHANGED, oldValue, this.beanInjection);
	}

	@Override
	public boolean isResolved() {
		return beanInjection != null;
	}
}
