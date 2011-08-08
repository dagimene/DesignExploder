package designexploder.model.extension.IoC.impl;

import designexploder.model.extension.IoC.IoCAwareMethod;
import designexploder.model.extension.IoC.IoCModelEventTypes;
import designexploder.model.extension.classnode.Method;
import designexploder.model.extension.common.impl.NaturalizedImpl;

class IoCAwareMethodImpl extends NaturalizedImpl implements IoCAwareMethod {

	private Method method;

	@Override
	public Method getTarget() {
		return method;
	}

	@Override
	public void setTarget(Method method) {
		Method oldValue = this.method;
		this.method = method;
		fireModelPropertyChangeEvent(IoCModelEventTypes.METHOD_CHANGED, oldValue, this.method);
	}
	
}
