package designexploder.model.extension.classnode.impl;

import designexploder.model.extension.classnode.ClassModelEventTypes;
import designexploder.model.extension.classnode.Type;
import designexploder.model.extension.classnode.Typed;
import designexploder.model.impl.ExtendedModelEventTrigger;

class TypedImpl extends ExtendedModelEventTrigger implements Typed {
	
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
