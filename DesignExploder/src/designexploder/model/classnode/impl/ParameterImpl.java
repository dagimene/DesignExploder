package designexploder.model.classnode.impl;

import designexploder.model.classnode.ClassModelEventTypes;
import designexploder.model.classnode.Parameter;
import designexploder.model.classnode.Type;

public class ParameterImpl extends ModifiableImpl implements Parameter {

	private String name;
	
	protected ParameterImpl(String name, Type type) {
		super(type);
		this.name = name;
	}

	@Override
	public void setName(String name) {
		String oldName = this.name;
		this.name = name;
		fireModelPropertyChangeEvent(ClassModelEventTypes.NAME_CHANGED, oldName, name);
	}

	@Override
	public String getName() {
		return name;
	}
}
