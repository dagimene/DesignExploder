package designexploder.model.classnode;

import java.util.Set;

public interface Modifiable extends Typed {

	Set<DexConstant> getModifiers();
	
	void addModifier(DexConstant modifier);
	
	void removeModifier(DexConstant modifier);
	
	/* Convenient Methods */
	
	boolean isPublic();
	
	boolean isPrivate();
	
	boolean isProtected();
	
	boolean isStatic();
	
	boolean isFinal();
	
	boolean isTransient();
	
	boolean isVolatile();
	
	boolean isNative();
	
	boolean isAbstract();
	
}