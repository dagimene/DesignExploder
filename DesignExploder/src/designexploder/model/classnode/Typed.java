package designexploder.model.classnode;

import designexploder.model.Named;

public interface Typed extends Named {
	
	Type getType();
	
	void setType(Type type);

}
