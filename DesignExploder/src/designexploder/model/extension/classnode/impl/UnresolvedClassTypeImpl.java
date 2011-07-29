package designexploder.model.extension.classnode.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import designexploder.model.extension.classnode.ClassType;
import designexploder.model.extension.classnode.ClassTypeFactory;
import designexploder.model.extension.classnode.Type;

public class UnresolvedClassTypeImpl extends TypeImpl implements ClassType {

	private final boolean typeVariable;

	protected UnresolvedClassTypeImpl(String name, boolean typeVariable, ClassTypeFactory factory) {
		super(name);
		this.typeVariable = typeVariable;
		this.factory = factory;
	}

	private static final Map<String, UnresolvedClassTypeImpl> unkownTypes = new HashMap<String, UnresolvedClassTypeImpl>(200);
	private static final Map<String, UnresolvedClassTypeImpl> typeVariables = new HashMap<String, UnresolvedClassTypeImpl>(26); // English characters
	
	public static ClassType instanceForTypeVariable(String name, ClassTypeFactory factory) {
		UnresolvedClassTypeImpl instance = typeVariables.get(name);
		if(instance == null) {
			instance = new UnresolvedClassTypeImpl(name, true, factory);
			typeVariables.put(name, instance);
		}
		return instance;
	}

	public static ClassType instanceForUnknownType(String name, ClassTypeFactory factory) {
		UnresolvedClassTypeImpl instance = unkownTypes.get(name);
		if(instance == null) {
			instance = new UnresolvedClassTypeImpl(name, false, factory);
			unkownTypes.put(name, instance);
		}
		return instance;
	}

	private final ClassTypeFactory factory;

	@Override
	public boolean isTypeVariable() {
		return typeVariable;
	}

	@Override
	public boolean isClass() {
		return !typeVariable;
	}

	@Override
	public boolean isInterface() {
		return false;
	}

	@Override
	public boolean isEnum() {
		return false;
	}

	@Override
	public ClassType getSuperclass() {
		ClassType result = null;
		try {
			result = factory.typeFor(Object.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<ClassType> getInterfaces() {
		return Collections.emptyList();
	}

	@Override
	public ClassType getTypeErasure() {
		return this;
	}

	@Override
	public List<Type> getTypeParameters() {
		return Collections.emptyList();
	}

	@Override
	public ClassTypeFactory getFactory() {
		return factory;
	}
	
	public String toString() {
		return getClass().getSimpleName() + " [ "+ getName() +  (typeVariable ? " (variable)" : " (unknown)") +" ]";
	}

	@Override
	public ClassType asClassType() {
		return this;
	}
}