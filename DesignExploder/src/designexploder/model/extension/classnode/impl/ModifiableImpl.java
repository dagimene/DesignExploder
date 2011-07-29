package designexploder.model.extension.classnode.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import designexploder.model.extension.classnode.ClassModelEventTypes;
import designexploder.model.extension.classnode.Modifiable;
import designexploder.model.extension.classnode.Type;
import designexploder.model.extension.common.Nature;

import static designexploder.model.extension.classnode.ClassModelNatures.*;

class ModifiableImpl extends TypedImpl implements Modifiable {
	
	private Set<Nature> modifiers;
	
	protected ModifiableImpl(Type type) {
		super(type);
		modifiers = new HashSet<Nature>(3);
	}
	
	@Override
	public Set<Nature> getModifiers() {
		return Collections.unmodifiableSet(modifiers);
	}
	
	@Override
	public void addModifier(Nature modifier) {
		modifiers.add(modifier);
		fireModelCollectionAlterEvent(ClassModelEventTypes.MODIFIER_ADDED, modifiers, modifier);
	}

	@Override
	public void removeModifier(Nature modifier) {
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
	
	private boolean is(Nature modifier) {
		return modifiers.contains(modifier);
	}
}
