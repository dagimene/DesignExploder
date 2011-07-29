package designexploder.model.extension.classnode.impl;

import java.util.Collections;
import java.util.List;

import designexploder.model.extension.classnode.ArrayType;
import designexploder.model.extension.classnode.ClassType;
import designexploder.model.extension.classnode.ClassTypeFactory;
import designexploder.model.extension.classnode.Type;

public class ParameterizedClassTypeImpl implements ClassType {
	
	private ClassType typeErasure;
	
	private List<Type> typeParameters;
	
	private final String compoundName;
	private final String compoundFirstname;

	public ParameterizedClassTypeImpl(ClassType typeErasure, List<Type> typeParameters) {
		this.typeErasure = typeErasure;
		this.typeParameters = Collections.unmodifiableList(typeParameters);
		StringBuilder nameBuilder = new StringBuilder("<");
		StringBuilder firstnameBuilder = new StringBuilder("<");
		if(typeParameters.size() > 0) {
			nameBuilder.append(typeParameters.get(0).getName());
			firstnameBuilder.append(typeParameters.get(0).getFirstname());
			for(int i = 1; i < typeParameters.size(); i++) {
				nameBuilder.append(", ");
				nameBuilder.append(typeParameters.get(i).getName());
				firstnameBuilder.append(", ");
				firstnameBuilder.append(typeParameters.get(i).getFirstname());
			}
		}
		nameBuilder.append(">");
		firstnameBuilder.append(">");
		compoundName = typeErasure.getName() + nameBuilder.toString();
		compoundFirstname = typeErasure.getFirstname() + firstnameBuilder.toString();
	}

	@Override
	public ClassType getTypeErasure() {
		return typeErasure;
	}

	@Override
	public List<Type> getTypeParameters() {
		return typeParameters;
	}

	public String getName() {
		return compoundName;
	}

	public String getFirstname() {
		return compoundFirstname;
	}

	public ClassType asClassType() {
		return this;
	}

	/* Delegate Methods */
	
	public boolean isClass() {
		return typeErasure.isClass();
	}

	public String getLastname() {
		return typeErasure.getLastname();
	}

	public boolean isInterface() {
		return typeErasure.isInterface();
	}

	public boolean isBasic() {
		return typeErasure.isBasic();
	}

	public boolean isEnum() {
		return typeErasure.isEnum();
	}

	public boolean isClassType() {
		return typeErasure.isClassType();
	}

	public ClassType getSuperclass() {
		return typeErasure.getSuperclass();
	}

	public List<ClassType> getInterfaces() {
		return typeErasure.getInterfaces();
	}

	public boolean isTypeVariable() {
		return typeErasure.isTypeVariable();
	}

	public boolean isArray() {
		return typeErasure.isArray();
	}

	public ArrayType asArrayType() {
		return typeErasure.asArrayType();
	}

	public ClassTypeFactory getFactory() {
		return typeErasure.getFactory();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((typeErasure == null) ? 0 : typeErasure.hashCode());
		result = prime * result
				+ ((typeParameters == null) ? 0 : typeParameters.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ParameterizedClassTypeImpl other = (ParameterizedClassTypeImpl) obj;
		if (typeErasure == null) {
			if (other.typeErasure != null)
				return false;
		} else if (!typeErasure.equals(other.typeErasure))
			return false;
		if (typeParameters == null) {
			if (other.typeParameters != null)
				return false;
		} else if (!typeParameters.equals(other.typeParameters))
			return false;
		return true;
	}
}
