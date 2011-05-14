package designexploder.model.classnode.impl;

import designexploder.model.classnode.ClassModelEventTypes;
import designexploder.model.classnode.DexConstant;
import designexploder.model.classnode.Method;
import designexploder.model.classnode.Type;

public class MethodImpl extends ParameterizedImpl implements Method {

	private String name;

	protected MethodImpl(String name, Type type) {
		super(type);
		this.name = name;
	}
	
	public void setName(String name) {
		String oldName = this.name;
		this.name = name;
		fireModelPropertyChangeEvent(ClassModelEventTypes.NAME_CHANGED, oldName, name);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public DexConstant getNature() {
		return isAbstract() ? DexConstant.ABSTRACT_METHOD : DexConstant.METHOD;
	}
	
}
