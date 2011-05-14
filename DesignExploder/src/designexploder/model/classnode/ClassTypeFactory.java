package designexploder.model.classnode;

public interface ClassTypeFactory {

	ClassType typeFor(Class<?> clazz) throws Exception;

	ClassType typeFor(String fullyQualifiedName) throws Exception;
	
}
