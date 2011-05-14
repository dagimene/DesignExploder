package designexploder.model.classnode.impl;

import designexploder.model.classnode.ClassModelEventTypes;
import designexploder.model.classnode.Type;
import designexploder.model.classnode.Typed;
import designexploder.model.impl.ExtendedModelEventTrigger;

public class TypedImpl extends ExtendedModelEventTrigger implements Typed {
	
	private Type type;

	public TypedImpl(Type type) {
		this.type = type;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		Type oldType = this.type;
		this.type = type;
		fireModelPropertyChangeEvent(ClassModelEventTypes.TYPE_CHANGED, oldType, type);
	}
}
