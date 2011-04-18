package designexploder.model.impl;

import designexploder.model.BasicModelEventTypes;
import designexploder.model.Named;

public class NamedImpl extends ModelPropertyChangeEventTrigger implements Named {
	
	public String name;
	
	public NamedImpl(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		String oldName = this.name;
		this.name = name;
		fireModelPropertyChangeEvent(BasicModelEventTypes.NAME_CHANGED, oldName, name);
	}
}