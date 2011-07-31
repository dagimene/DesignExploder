package designexploder.model.extension.classnode.impl;

import designexploder.model.extension.classnode.Attribute;
import designexploder.model.extension.classnode.ClassModelNatures;
import designexploder.model.extension.classnode.Type;

class AttributeImpl extends ParameterImpl implements Attribute {

	public AttributeImpl(String name, Type type) {
		super(name, type);
	}

	public ClassModelNatures getNature() {
		return ClassModelNatures.ATTRIBUTE;
	}

	@Override
	public boolean isMethod() {
		return false;
	}

	@Override
	public boolean isAttribute() {
		return true;
	}
}
