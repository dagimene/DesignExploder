package designexploder.model.extension.IoC;

import designexploder.model.Connection;
import designexploder.model.extension.classnode.ClassItem;
import designexploder.model.extension.classnode.InmutableNamed;
import designexploder.model.extension.common.Naturalized;

public interface Dependency extends InmutableNamed, Naturalized {

	/**
	 * The targeted attribute or method where the injection is performed.
	 * @return
	 */
	ClassItem getTarget();
	
	void setTarget(ClassItem target);

	/**
	 * Whether the dependency is resolved or not.
	 * A resolved dependency will have a bean injection associated.
	 * @return
	 */
	boolean isResolved();
	
	/**
	 * A connection with the bean injection associated with this resolved dependency, or null.
	 * @return
	 */
	Connection getBeanInjection(); 
	
	void setBeanInjection(Connection injection); 

}
