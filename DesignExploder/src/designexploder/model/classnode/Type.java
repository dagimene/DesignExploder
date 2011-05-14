package designexploder.model.classnode;


public interface Type extends InmutableNamed {

	String getFirstname();

	String getLastname();

	boolean isBasic();
	
	boolean isClassType();
	
	ClassType asClassType();
	
	boolean isArray();

	ArrayType asArrayType();
	
}