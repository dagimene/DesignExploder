package designexploder.model.classnode.impl;

import designexploder.model.classnode.Attribute;
import designexploder.model.classnode.DexConstant;
import designexploder.model.classnode.Type;

public class AttributeImpl extends ModifiableImpl implements Attribute {
	private DexConstant nature;

	public AttributeImpl(String name, Type type, DexConstant nature) {
		super(name, type);
		this.nature = nature;
	}

	public DexConstant getNature() {
		return nature;
	}

	public void setNature(DexConstant nature) {
		this.nature = nature;
	}
}
