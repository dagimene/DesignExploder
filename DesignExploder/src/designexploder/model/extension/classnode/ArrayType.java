package designexploder.model.extension.classnode;

public interface ArrayType extends Type {

	Type getInnerType();
	
	int getDeepCount();
	
}
