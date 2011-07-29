package designexploder.model.extension.IoC;

import designexploder.model.extension.classnode.Method;
import designexploder.model.extension.common.Naturalized;

public interface IoCAwareMethod extends Naturalized {

	/**
	 * The ClassModel method that is IoC aware.
	 * @return
	 */
	Method getMethod();
	
	void setMethod(Method method);

}
