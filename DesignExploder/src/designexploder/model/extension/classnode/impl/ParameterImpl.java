package designexploder.model.extension.classnode.impl;

import designexploder.model.extension.classnode.Parameter;
import designexploder.model.extension.classnode.Type;
import designexploder.model.extension.common.CommonModelEventTypes;

class ParameterImpl extends ModifiableImpl implements Parameter {

	private String name;
	
	public ParameterImpl(String name, Type type) {
		super(type);
		this.name = name;
	}

	@Override
	public void setName(String name) {
		String oldName = this.name;
		this.name = name;
		fireModelPropertyChangeEvent(CommonModelEventTypes.NAME_CHANGED, oldName, name);
	}

	@Override
	public String getName() {
		return name;
	}
}
