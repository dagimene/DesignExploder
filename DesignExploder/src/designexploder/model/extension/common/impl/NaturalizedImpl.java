package designexploder.model.extension.common.impl;

import designexploder.model.extension.common.CommonModelEventTypes;
import designexploder.model.extension.common.Naturalized;
import designexploder.model.extension.common.Nature;
import designexploder.model.impl.ExtendedModelEventTrigger;

public class NaturalizedImpl extends ExtendedModelEventTrigger implements Naturalized {

	private Nature nature;

	@Override
	public Nature getNature() {
		return nature;
	}

	@Override
	public void setNature(Nature nature) {
		Nature oldValue = this.nature;
		this.nature = nature;
		fireModelPropertyChangeEvent(CommonModelEventTypes.NATURE_CHANGED, oldValue, this.nature);
	}
}
