package designexploder.model.classnode;

import java.util.List;

public interface ClassType extends Type {
	
	boolean isClass();

	boolean isInterface();
	
	boolean isEnum();
	
	ClassType getSuperclass();
	
	List<ClassType> getInterfaces();
	
	/**
	 * @return list of ClassTypes and ArrayTypes.
	 */
	List<Type> getTypeParameters();
	
	/**
	 * @return a factory for more types of this class.
	 */
	ClassTypeFactory getFactory();
	
}