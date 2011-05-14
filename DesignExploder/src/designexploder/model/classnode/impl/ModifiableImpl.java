package designexploder.model.classnode.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import designexploder.model.classnode.ClassModelEventTypes;
import designexploder.model.classnode.DexConstant;
import designexploder.model.classnode.Modifiable;
import designexploder.model.classnode.Type;

import static designexploder.model.classnode.DexConstant.*;

public class ModifiableImpl extends TypedImpl implements Modifiable {
	
	private Set<DexConstant> modifiers;
	
	protected ModifiableImpl(Type type) {
		super(type);
		modifiers = new HashSet<DexConstant>(3);
	}
	
	@Override
	public Set<DexConstant> getModifiers() {
		return Collections.unmodifiableSet(modifiers);
	}
	
	public void addModifier(DexConstant modifier) {
		modifiers.add(modifier);
		fireModelCollectionAlterEvent(ClassModelEventTypes.MODIFIER_ADDED, modifiers, modifier);
	}

	public void removeModifier(DexConstant modifier) {
		modifiers.remove(modifier);
		fireModelCollectionAlterEvent(ClassModelEventTypes.MODIFIER_REMOVED, modifiers, modifier);
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
