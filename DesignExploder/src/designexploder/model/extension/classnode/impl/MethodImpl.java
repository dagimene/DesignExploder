package designexploder.model.extension.classnode.impl;

import designexploder.model.extension.classnode.ClassModelNatures;
import designexploder.model.extension.classnode.Method;
import designexploder.model.extension.classnode.Type;
import designexploder.model.extension.common.CommonModelEventTypes;
import designexploder.model.extension.common.Nature;

class MethodImpl extends ParameterizedImpl implements Method {

	private String name;

	public MethodImpl(String name, Type type) {
		super(type);
		this.name = name;
	}
	
	public void setName(String name) {
		String oldName = this.name;
		this.name = name;
		fireModelPropertyChangeEvent(CommonModelEventTypes.NAME_CHANGED, oldName, name);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Nature getNature() {
		return isAbstract() ? ClassModelNatures.ABSTRACT_METHOD : ClassModelNatures.METHOD;
	}
	
}
