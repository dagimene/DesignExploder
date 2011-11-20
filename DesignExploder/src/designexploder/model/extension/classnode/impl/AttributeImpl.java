package designexploder.model.extension.classnode.impl;

import designexploder.model.extension.classnode.Attribute;
import designexploder.model.extension.classnode.ClassModelNatures;
import designexploder.model.extension.classnode.Method;
import designexploder.model.extension.classnode.Type;

class AttributeImpl extends ParameterImpl implements Attribute {

	private Method setter;
	
	private Method getter;

    private boolean inherited;

	public AttributeImpl(String name, Type type) {
		super(name.intern(), type);
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

    @Override
	public Method getSetter() {
		return setter;
	}

	@Override
	public void setSetter(Method setter) {
		this.setter = setter;
		// TODO: Fire event
	}

	@Override
	public Method getGetter() {
		return getter;
	}

	@Override
	public void setGetter(Method getter) {
		this.getter = getter;
		// TODO: Fire event
	}

	@Override
	public boolean isEnumConstant() {
		return getModifiers().contains(ClassModelNatures.ENUM);
	}

	@Override
	public boolean isPublic() {
		return super.isPublic() || isEnumConstant();
	}

	@Override
	public boolean isStatic() {
		return super.isStatic() || isEnumConstant();
	}

	@Override
	public boolean isFinal() {
		return super.isFinal() || isEnumConstant();
	}


    public boolean isInherited() {
        return inherited;
    }

    public void setInherited(boolean inherited) {
        this.inherited = inherited;
    }
}
