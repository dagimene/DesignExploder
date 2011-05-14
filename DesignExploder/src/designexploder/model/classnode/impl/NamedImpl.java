package designexploder.model.classnode.impl;

import designexploder.model.classnode.ClassModelEventTypes;
import designexploder.model.classnode.Named;
import designexploder.model.impl.ExtendedModelEventTrigger;

public class NamedImpl extends ExtendedModelEventTrigger implements Named {
	
	public String name;
	
	protected NamedImpl() {}

	public NamedImpl(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		String oldName = this.name;
		this.name = name;
		fireModelPropertyChangeEvent(ClassModelEventTypes.NAME_CHANGED, oldName, name);
	}
}