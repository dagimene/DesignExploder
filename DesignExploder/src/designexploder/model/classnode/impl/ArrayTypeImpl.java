package designexploder.model.classnode.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import designexploder.model.classnode.ArrayType;
import designexploder.model.classnode.ClassType;
import designexploder.model.classnode.Type;

public class ArrayTypeImpl implements ArrayType {

	private static Map<Type, ArrayType[]> instances = new HashMap<Type, ArrayType[]>(); 
	
	public static Type instanceFor(Type innerType, int deepCount) {
		ArrayType[] typeDeeps = instances.get(innerType);
		if(typeDeeps == null) {
			typeDeeps = new ArrayType[deepCount];
			instances.put(innerType, typeDeeps);
		} else if(typeDeeps.length < deepCount) {
			typeDeeps = Arrays.copyOf(typeDeeps, deepCount);
			instances.put(innerType, typeDeeps);
		}
		ArrayType result = typeDeeps[deepCount - 1];
		if(result == null) {
			result = new ArrayTypeImpl(innerType, deepCount);
			typeDeeps[deepCount - 1] = result;
		}
		return result;
	}

	private final Type inner;
	private final int deepCount;
	private final String compoundName;

	private ArrayTypeImpl(Type inner, int deepCount) {
		this.inner = inner;
		this.deepCount = deepCount;
		compoundName = createCompoundName(inner.getName(), deepCount); 
	}
	
	public String getName() {
		return compoundName;
	}

	public String getFirstname() {
		return inner.getFirstname();
	}

	public String getLastname() {
		return inner.getLastname();
	}

	public boolean isBasic() {
		return false;
	}

	public boolean isClassType() {
		return false;
	}

	public ClassType asClassType() {
		return null;
	}

	public boolean isArray() {
		return true;
	}

	public ArrayType asArrayType() {
		return this;
	}

	@Override
	public Type getInnerType() {
		return inner;
	}

	@Override
	public int getDeepCount() {
		return deepCount;
	}

	private static String createCompoundName(String name, int deepCount) {
		StringBuilder builder = new StringBuilder(name);
		while(deepCount > 0) {
			builder.append("[]");
			deepCount--;
		}
		return builder.toString();
	}
	@Override

	public String toString() {
		return getClass().getSimpleName() + " ("+ getDeepCount() +") [ " + inner.toString() +" ]";
	}
}
