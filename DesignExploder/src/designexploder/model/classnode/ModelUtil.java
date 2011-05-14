package designexploder.model.classnode;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModelUtil {

	private static Map<Class<? extends ClassType>, ModelUtil> instances = new HashMap<Class<? extends ClassType>, ModelUtil>();
	
	private final Map<Class<?>, ClassType> knownTypes = new HashMap<Class<?>, ClassType>();
	
	private final ClassTypeFactory factory;
	
	private ModelUtil(ClassTypeFactory classTypeFactory) {
		factory = classTypeFactory;
	}
	
	public static boolean isCollection(ClassType type) {
		return getInstance(type).isCollection0(type);
	}

	private static ModelUtil getInstance(ClassType classType) {
		ModelUtil instance = instances.get(classType.getClass());
		if(instance == null) {
			instance = new ModelUtil(classType.getFactory());
			instances.put(classType.getClass(), instance);
		}
		return instance;
	}
	
	private boolean isCollection0(ClassType type) {
		Deque<ClassType> hierarchy = new ArrayDeque<ClassType>();
		ClassType collectionType = getKnownType(Collection.class);
		hierarchy.add(type);
		while(!hierarchy.isEmpty()) {
			type = hierarchy.pop();
			if(type.isInterface() && collectionType.equals(type)) {
				return true;
			}
			ClassType superclass = type.getSuperclass();
			if(superclass != null) {
				hierarchy.push(superclass);
			}
			for (ClassType interfaze : type.getInterfaces()) {
				hierarchy.push(interfaze);
			}
		}
		return false;
	}
	
	private ClassType getKnownType(Class<?> clazz) {
		ClassType result = null;
		if(factory != null) {
			result = knownTypes.get(clazz);
			if(result == null) {
				try {
					result = factory.typeFor(clazz);
					knownTypes.put(clazz, result);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return result != null ? result : DUMMY_CLASS_TYPE;
	}
	
	private static final ClassType DUMMY_CLASS_TYPE = new ClassType() {
		@Override
		public String getFirstname() {
			return null;
		}

		@Override
		public String getLastname() {
			return null;
		}

		@Override
		public boolean isBasic() {
			return false;
		}

		@Override
		public boolean isClassType() {
			return false;
		}

		@Override
		public boolean isArray() {
			return false;
		}

		@Override
		public ClassType asClassType() {
			return null;
		}

		@Override
		public String getName() {
			return null;
		}

		@Override
		public boolean isClass() {
			return false;
		}

		@Override
		public boolean isInterface() {
			return false;
		}

		@Override
		public boolean isEnum() {
			return false;
		}

		@Override
		public ClassType getSuperclass() {
			return null;
		}

		@Override
		public List<ClassType> getInterfaces() {
			return null;
		}

		@Override
		public List<Type> getTypeParameters() {
			return null;
		}

		@Override
		public ArrayType asArrayType() {
			return null;
		}

		@Override
		public ClassTypeFactory getFactory() {
			return null;
		}
	};
	
}
