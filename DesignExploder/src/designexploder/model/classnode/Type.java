package designexploder.model.classnode;


public interface Type extends InmutableNamed {

	String getFirstname();

	String getLastname();

	boolean isBasic();
	
	boolean isArray();

	boolean isClassType();
	
	boolean isTypeVariable();

	ClassType asClassType();
	
	ArrayType asArrayType();
	
}