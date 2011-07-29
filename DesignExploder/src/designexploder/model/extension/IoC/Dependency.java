package designexploder.model.extension.IoC;

import designexploder.model.Connection;
import designexploder.model.event.ModelEventTrigger;
import designexploder.model.extension.common.Naturalized;

public interface Dependency extends ModelEventTrigger {

	/**
	 * The targeted attribute or method where the injection is performed.
	 * @return
	 */
	Naturalized getTarget();
	
	void setTarget(Naturalized target);

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
