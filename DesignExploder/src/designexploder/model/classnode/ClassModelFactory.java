package designexploder.model.classnode;

import designexploder.model.Node;
import designexploder.model.classconnection.ClassConnection;
import designexploder.model.classconnection.impl.ClassConnectionImpl;
import designexploder.model.classnode.impl.AttributeImpl;
import designexploder.model.classnode.impl.ClassNodeImpl;
import designexploder.model.classnode.impl.ClassSectionImpl;
import designexploder.model.classnode.impl.ModifiableImpl;
import designexploder.model.classnode.impl.MethodImpl;

public class ClassModelFactory {
	private static ClassModelFactory INSTANCE;
	private ClassModelFactory() {}
	public static ClassModelFactory getFactory() {
		if(INSTANCE == null) {
			INSTANCE = new ClassModelFactory();
		}
		return INSTANCE; 
	}
	
	public ClassNode createClassNode(Type clazz, Type type, DexConstant nature) {
		return new ClassNodeImpl(clazz, type, nature);
	}
	
	public ClassSection createSection() {
		return new ClassSectionImpl();
	}

	public Modifiable createModifiable(String name, Type type) {
		return new ModifiableImpl(name, type);
	}

	public Attribute createAttribute(String name, Type type) {
		return new AttributeImpl(name, type, DexConstant.ATTRIBUTE);
	}
	
	public Method createMethod(String name, Type type, DexConstant nature) {
		return new MethodImpl(name, type, nature);
	}
	
	public ClassConnection createConnection(Node from, Node to, DexConstant nature) {
		return new ClassConnectionImpl(nature, from, to);
	}
}
