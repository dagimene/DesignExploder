package designexploder.model.classnode;

import designexploder.model.ModelEventTrigger;


public interface Naturalized extends InmutableNaturalized, ModelEventTrigger {

	void setNature(DexConstant nature);
	
}
