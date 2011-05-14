package designexploder.model.classnode;

import designexploder.model.ModelEventTrigger;

public interface Named extends InmutableNamed,  ModelEventTrigger {

	void setName(String name);

}