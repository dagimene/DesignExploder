package designexploder.model.extension.IoC;

import designexploder.model.extension.classnode.ClassItem;
import designexploder.model.extension.common.Naturalized;

public interface ClassItemTargeted extends Naturalized {

	/**
	 * The targeted attribute or method where the injection is performed.
	 * @return
	 */
	ClassItem getTarget();
	
}
