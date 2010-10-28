package designexploder.model.classnode.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import designexploder.model.classnode.DexConstant;
import designexploder.model.classnode.Modifiable;
import designexploder.model.classnode.Type;

public class ModifiableImpl extends TypedImpl implements Modifiable {
	
	public ModifiableImpl(String name, Type type) {
		super(name, type);
		modifiers = new ArrayList<DexConstant>(3);
	}
	
	private List<DexConstant> modifiers = Collections.emptyList();
	
	public List<DexConstant> getModifiers() {
		return modifiers;
	}
	public void setModifiers(List<DexConstant> modifiers) {
		this.modifiers = modifiers;
	}
}
