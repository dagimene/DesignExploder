package designexploder.model.classnode.impl;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import designexploder.model.Connection;
import designexploder.model.classnode.Attribute;
import designexploder.model.classnode.ClassModelEventTypes;
import designexploder.model.classnode.ClassNode;
import designexploder.model.classnode.DexConstant;
import designexploder.model.classnode.Method;
import designexploder.model.classnode.Type;
import designexploder.model.impl.NodeImpl;

import static designexploder.model.classnode.DexConstant.*;

public class ClassNodeImpl<C extends Connection> extends NodeImpl<C> implements ClassNode<C> {
	
	private Type clazz;
	private DexConstant nature;
	private Set<DexConstant> modifiers;
	private List<Attribute> attributes;
	private List<Method> methods;
	
	protected ClassNodeImpl(Type thisclazz) {
		super(thisclazz.getName());
		this.clazz = thisclazz;
		this.methods = new ArrayList<Method>();
		this.attributes = new ArrayList<Attribute>();
		this.modifiers = new HashSet<DexConstant>(3);
	}
	
	public Type getType() {
		return clazz;
	}

	public void setType(Type clazz) {
		Type oldType = this.clazz;
		this.clazz = clazz;
		fireModelPropertyChangeEvent(ClassModelEventTypes.TYPE_CHANGED, oldType, clazz);
	}

	public DexConstant getNature() {
		DexConstant result;
		if(nature == CLASS) {
			result = isAbstract() ? ABSTRACT_CLASS : CLASS;
		} else {
			result = nature; // ENUM || INTERFACE
		}
		return result;
	}

	public void setNature(DexConstant nature) {
		if(nature == DexConstant.ABSTRACT_CLASS) {
			throw new InvalidParameterException("Attempting to set nature "+nature+" to node "+this+". Valid natures are only CLASS, INTERFACE or ENUM.");
		}
		DexConstant oldNature = this.nature;
		this.nature = nature;
		fireModelPropertyChangeEvent(ClassModelEventTypes.NATURE_CHANGED, oldNature, nature);
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
	public Set<DexConstant> getModifiers() {
		return Collections.unmodifiableSet(modifiers);
	}
	
	public void addModifier(DexConstant modifier) {
		modifiers.add(modifier);
		fireModelCollectionAlterEvent(ClassModelEventTypes.MODIFIER_ADDED, modifiers, modifier);
		if(nature == CLASS && modifier == DexConstant.ABSTRACT) {
			fireModelPropertyChangeEvent(ClassModelEventTypes.NATURE_CHANGED, CLASS, ABSTRACT_CLASS);
		}
	}

	public void removeModifier(DexConstant modifier) {
		modifiers.remove(modifier);
		fireModelCollectionAlterEvent(ClassModelEventTypes.MODIFIER_REMOVED, modifiers, modifier);
		if(nature == CLASS && modifier == DexConstant.ABSTRACT) {
			fireModelPropertyChangeEvent(ClassModelEventTypes.NATURE_CHANGED, ABSTRACT_CLASS, CLASS);
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
	
	private boolean is(DexConstant modifier) {
		return modifiers.contains(modifier);
	}
}
