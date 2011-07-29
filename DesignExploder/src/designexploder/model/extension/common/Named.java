package designexploder.model.extension.common;

import designexploder.model.event.ModelEventTrigger;
import designexploder.model.extension.classnode.InmutableNamed;

public interface Named extends InmutableNamed,  ModelEventTrigger {

	void setName(String name);

}