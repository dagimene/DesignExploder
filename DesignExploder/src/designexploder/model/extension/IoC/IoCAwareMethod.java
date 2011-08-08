package designexploder.model.extension.IoC;

import designexploder.model.extension.classnode.Method;

public interface IoCAwareMethod extends ClassItemTargeted {

	/**
	 * The ClassModel method that is IoC aware.
	 * @return
	 */
	Method getTarget();
	
	void setTarget(Method method);

}
