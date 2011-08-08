package designexploder.model.extension.classnode;

import java.util.Set;

import designexploder.model.extension.common.Nature;

public interface Modifiable extends Typed {
	
	Set<Nature> getModifiers();
	
	void addModifier(Nature modifier);
	
	void removeModifier(Nature modifier);
	
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
	
	Nature getAccessModifier();
	
}