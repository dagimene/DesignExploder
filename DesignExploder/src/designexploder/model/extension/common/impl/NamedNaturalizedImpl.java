package designexploder.model.extension.common.impl;

import designexploder.model.extension.common.CommonModelEventTypes;
import designexploder.model.extension.common.Named;

public class NamedNaturalizedImpl extends NaturalizedImpl implements Named {

	public String name;
	
	protected NamedNaturalizedImpl() {}

	public NamedNaturalizedImpl(String name) {
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
