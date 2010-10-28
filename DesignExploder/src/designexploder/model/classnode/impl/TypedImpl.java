package designexploder.model.classnode.impl;

import designexploder.model.classnode.Type;
import designexploder.model.classnode.Typed;
import designexploder.model.impl.NamedImpl;

public class TypedImpl extends NamedImpl implements Typed {
	
	private Type type;

	public TypedImpl(String name, Type type) {
		super(name);
		this.type = type;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
}
