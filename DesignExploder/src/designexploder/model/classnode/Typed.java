package designexploder.model.classnode;

import designexploder.model.ModelEventTrigger;

public interface Typed extends ModelEventTrigger {
	
	Type getType();
	
	void setType(Type type);

}
