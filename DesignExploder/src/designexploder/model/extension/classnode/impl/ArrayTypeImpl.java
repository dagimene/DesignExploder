package designexploder.model.extension.classnode.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import designexploder.model.extension.classnode.ArrayType;
import designexploder.model.extension.classnode.ClassType;
import designexploder.model.extension.classnode.Type;

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
	private final String compoundFirstname;

	private ArrayTypeImpl(Type inner, int deepCount) {
		this.inner = inner;
		this.deepCount = deepCount;
		StringBuilder builder = new StringBuilder();
		while(deepCount > 0) {
			builder.append("[]");
			deepCount--;
		}
		compoundName = inner.getName() + builder.toString();
		compoundFirstname = inner.getFirstname() + builder.toString();
	}
	
	public String getName() {
		return compoundName;
	}

	public String getFirstname() {
		return compoundFirstname;
	}

	public String getLastname() {
		return inner.getLastname();
	}

	public boolean isBasic() {
		return false;
	}

    @Override
    public boolean isVoid() {
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

	@Override
	public String toString() {
		return getClass().getSimpleName() + " ("+ getDeepCount() +") [ " + inner.toString() +" ]";
	}

	@Override
	public boolean isTypeVariable() {
		return false;
	}
}
