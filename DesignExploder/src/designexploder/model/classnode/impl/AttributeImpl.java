package designexploder.model.classnode.impl;

import designexploder.model.classnode.Attribute;
import designexploder.model.classnode.DexConstant;
import designexploder.model.classnode.Type;

public class AttributeImpl extends ParameterImpl implements Attribute {

	protected AttributeImpl(String name, Type type) {
		super(name, type);
	}

	public DexConstant getNature() {
		return DexConstant.ATTRIBUTE;
	}
}
