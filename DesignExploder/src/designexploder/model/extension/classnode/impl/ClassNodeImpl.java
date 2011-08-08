package designexploder.model.extension.classnode.impl;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import designexploder.model.extension.classnode.Attribute;
import designexploder.model.extension.classnode.ClassModelEventTypes;
import designexploder.model.extension.classnode.ClassNode;
import designexploder.model.extension.classnode.ClassModelNatures;
import designexploder.model.extension.classnode.Method;
import designexploder.model.extension.classnode.Type;
import designexploder.model.extension.common.CommonModelEventTypes;
import designexploder.model.extension.common.Nature;
import designexploder.model.extension.common.impl.NaturalizedImpl;

import static designexploder.model.extension.classnode.ClassModelNatures.*;

class ClassNodeImpl extends NaturalizedImpl implements ClassNode {
	
	private Type clazz;
	private Set<Nature> modifiers;
	private List<Attribute> attributes;
	private List<Method> methods;
	
	public ClassNodeImpl(Type thisclazz) {
		this.clazz = thisclazz;
		this.methods = new ArrayList<Method>();
		this.attributes = new ArrayList<Attribute>();
		this.modifiers = new HashSet<Nature>(3);
	}
	
	public Type getType() {
		return clazz;
	}

	public void setType(Type clazz) {
		Type oldType = this.clazz;
		this.clazz = clazz;
		fireModelPropertyChangeEvent(ClassModelEventTypes.TYPE_CHANGED, oldType, clazz);
	}

	public Nature getNature() {
		Nature result = super.getNature();
		if(result == CLASS) {
			result = isAbstract() ? ABSTRACT_CLASS : CLASS;
		}
		return result;
	}

	public void setNature(Nature nature) {
		if(nature == ClassModelNatures.ABSTRACT_CLASS) {
			throw new InvalidParameterException("Attempting to set nature "+nature+" to node "+this+". Valid natures are only CLASS, INTERFACE or ENUM.");
		}
		super.setNature(nature);
	}
	
	@Override
	public List<Method> getMethods() {
		return Collections.unmodifiableList(methods);
	}

	@Override
	public void addMethod(Method method) {
		methods.add(method);
		fireModelCollectionAlterEvent(ClassModelEventTypes.METHOD_ADDED, methods, method);
	}

	@Override
	public void removeMethod(Method method) {
		methods.remove(method);
		fireModelCollectionAlterEvent(ClassModelEventTypes.METHOD_REMOVED, methods, method);
	}

	@Override
	public List<Attribute> getAttributes() {
		return Collections.unmodifiableList(attributes);
	}

	@Override
	public void addAttribute(Attribute attribute) {
		attributes.add(attribute);
		fireModelCollectionAlterEvent(ClassModelEventTypes.ATTRIBUTE_ADDED, attributes, attribute);
	}

	@Override
	public void removeAttribute(Attribute attribute) {
		attributes.remove(attribute);
		fireModelCollectionAlterEvent(ClassModelEventTypes.ATTRIBUTE_REMOVED, attributes, attribute);
	}
	
	@Override
	public Set<Nature> getModifiers() {
		return Collections.unmodifiableSet(modifiers);
	}
	
	public void addModifier(Nature modifier) {
		modifiers.add(modifier);
		fireModelCollectionAlterEvent(ClassModelEventTypes.MODIFIER_ADDED, modifiers, modifier);
		if(getNature() == CLASS && modifier == ClassModelNatures.ABSTRACT) {
			fireModelPropertyChangeEvent(CommonModelEventTypes.NATURE_CHANGED, CLASS, ABSTRACT_CLASS);
		}
	}

	public void removeModifier(Nature modifier) {
		modifiers.remove(modifier);
		fireModelCollectionAlterEvent(ClassModelEventTypes.MODIFIER_REMOVED, modifiers, modifier);
		if(getNature() == CLASS && modifier == ClassModelNatures.ABSTRACT) {
			fireModelPropertyChangeEvent(CommonModelEventTypes.NATURE_CHANGED, ABSTRACT_CLASS, CLASS);
		}
	}

	@Override
	public boolean isPublic() {
		return is(PUBLIC);
	}
	@Override
	public boolean isPrivate() {
		return is(PRIVATE);
	}
	@Override
	public boolean isProtected() {
		return is(PROTECTED);
	}
	@Override
	public boolean isStatic() {
		return is(STATIC);
	}
	@Override
	public boolean isFinal() {
		return is(FINAL);
	}
	@Override
	public boolean isTransient() {
		return is(TRANSIENT);
	}
	@Override
	public boolean isVolatile() {
		return is(VOLATILE);
	}
	@Override
	public boolean isNative() {
		return is(NATIVE);
	}
	@Override
	public boolean isAbstract() {
		return is(ABSTRACT);
	}
	
	private boolean is(Nature modifier) {
		return modifiers.contains(modifier);
	}
	
	@Override
	public Nature getAccessModifier() {
		if(isPublic()) {
			return PUBLIC;
		}
		if(isProtected()) {
			return PROTECTED;
		}
		if(isPrivate()) {
			return PRIVATE;
		}
		return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Class<ClassNode> getExtensionClass() {
		return ClassNode.class;
	}
	
}
