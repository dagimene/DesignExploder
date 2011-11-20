package designexploder.model.extension.classnode;

import designexploder.model.extension.common.InmutableNaturalized;
import designexploder.model.extension.common.Named;

public interface ClassItem extends Modifiable, Named, InmutableNaturalized {

	boolean isMethod();
	
	boolean isAttribute();

    boolean isInherited();

    void setInherited(boolean inherited);

}
