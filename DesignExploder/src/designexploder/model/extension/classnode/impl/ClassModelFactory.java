package designexploder.model.extension.classnode.impl;

import designexploder.model.Node;
import designexploder.model.extension.classnode.*;

public class ClassModelFactory {

	private static final ClassModelFactory instance = new ClassModelFactory();

	public static ClassModelFactory getInstance() {
		return instance;
	}
	
	private ClassModelFactory() {}
	
	public ClassNode createClassNode(Type type) {
		return new ClassNodeImpl(type);
	}

	public ClassRelation createClassConnection() {
		return new ClassRelationImpl();
	}

	public Attribute createAttribute(Node parent, String name, Type type) {
		return new AttributeImpl(name, type);
	}

	public Method createMethod(Node parent, String name, Type type) {
		return new MethodImpl(name, type);
	}
	
	public Parameter createParameter(String name, Type type) {
		return new ParameterImpl(name, type);
	}

}
