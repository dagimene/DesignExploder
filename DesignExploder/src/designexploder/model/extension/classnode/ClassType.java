package designexploder.model.extension.classnode;

import java.util.List;

public interface ClassType extends Type {
	
	boolean isClass();

	boolean isInterface();
	
	boolean isEnum();
	
	ClassType getSuperclass();
	
	List<ClassType> getInterfaces();
	
	/**
	 * @return A class type corresponding to this type without type parameters.
	 */
	ClassType getTypeErasure();
	
	/**
	 * @return list of ClassTypes and ArrayTypes corresponding to type parameters, if the type is parameterized, or an empty list.
	 */
	List<Type> getTypeParameters();
	
	/**
	 * @return a factory for more types of this class.
	 */
	ClassTypeFactory getFactory();
	
}