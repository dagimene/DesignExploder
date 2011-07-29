package designexploder.model.extension.classnode;

import designexploder.model.event.ModelEventTrigger;

public interface Typed extends ModelEventTrigger {
	
	Type getType();
	
	void setType(Type type);

}
