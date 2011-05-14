package designexploder.model.classnode;

public interface ArrayType extends Type {

	Type getInnerType();
	
	int getDeepCount();
	
}
