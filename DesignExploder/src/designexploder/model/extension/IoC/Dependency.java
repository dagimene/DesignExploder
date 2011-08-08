package designexploder.model.extension.IoC;

import java.util.Set;

import designexploder.model.Connection;
import designexploder.model.extension.classnode.ClassItem;
import designexploder.model.extension.classnode.InmutableNamed;

public interface Dependency extends InmutableNamed, ClassItemTargeted {

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
	Set<Connection> getBeanInjections(); 
	
	void addBeanInjection(Connection injection); 

	void removeBeanInjection(Connection injection); 

}
