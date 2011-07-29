package designexploder.model.extension.common.impl;

import designexploder.model.extension.common.CommonModelEventTypes;
import designexploder.model.extension.common.Named;
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
		fireModelPropertyChangeEvent(CommonModelEventTypes.NAME_CHANGED, oldName, name);
	}
}