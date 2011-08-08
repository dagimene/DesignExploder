package designexploder.model.extension.classnode.impl;

import designexploder.model.extension.classnode.ClassModelNatures;
import designexploder.model.extension.classnode.Method;
import designexploder.model.extension.classnode.Type;
import designexploder.model.extension.common.CommonModelEventTypes;
import designexploder.model.extension.common.Nature;

class MethodImpl extends ParameterizedImpl implements Method {

	private String name;
	private Boolean hasProperty;
	private boolean getter;
	private String property;

	public MethodImpl(String name, Type type) {
		super(type);
		this.name = name;
	}
	
	public void setName(String name) {
		String oldName = this.name;
		this.name = name;
		fireModelPropertyChangeEvent(CommonModelEventTypes.NAME_CHANGED, oldName, name);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Nature getNature() {
		return isAbstract() ? ClassModelNatures.ABSTRACT_METHOD : ClassModelNatures.METHOD;
	}
	
	@Override
	public boolean isGetter() {
		if(hasProperty == null) {
			analize();
		}
		return hasProperty && getter;
	}

	@Override
	public boolean isSetter() {
		if(hasProperty == null) {
			analize();
		}
		return hasProperty && !getter;
	}

	@Override
	public String getProperty() {
		if(hasProperty == null) {
			analize();
		}
		return property;
	}

	private void analize() {
		hasProperty = false;
		if(getType().isBasic() && getType().getName().equals("boolean")
			&& name.startsWith("is") && isUppercase(name.charAt(2))) {
			hasProperty = true;
			getter = true;
			property = (toLowerCase(name.charAt(2)) + name.substring(3)).intern();
		} else if(name.length() >= 4 && isUppercase(name.charAt(3)) && (name.startsWith("get") || name.startsWith("set"))) {
			getter = name.charAt(0) == 'g';
			// Check return type and arguments
			if((getter && (!getType().isBasic() || !getType().getName().equals("void")) && getParameters().isEmpty()) 
				|| (!getter && getType().isBasic() && getType().getName().equals("void") && getParameters().size() == 1)) {
				hasProperty = true;
				property = (toLowerCase(name.charAt(3)) + name.substring(4)).intern();
			}
		} 
	}

	private char toLowerCase(char aChar) {
		return (char) (aChar - 'A' + 'a');
	}

	private boolean isUppercase(char aChar) {
		return 'A' <= aChar && aChar <= 'Z';
	}

	@Override
	public boolean isMethod() {
		return true;
	}

	@Override
	public boolean isAttribute() {
		return false;
	}
}
