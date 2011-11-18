package designexploder.model.extension.classnode;

import java.util.*;

public class ClassModelUtil {

	private static Map<Class<? extends ClassType>, ClassModelUtil> instances = new HashMap<Class<? extends ClassType>, ClassModelUtil>();
	
	private final Map<Class<?>, ClassType> knownTypes = new HashMap<Class<?>, ClassType>();
	
	private final ClassTypeFactory factory;
	
	private ClassModelUtil(ClassTypeFactory classTypeFactory) {
		factory = classTypeFactory;
	}
	
	public static boolean isCollection(ClassType type) {
		return getInstance(type).isCollection0(type);
	}

	public static boolean isAssignableFromList(ClassType type) {
		return getInstance(type).isAssignableFromList0(type);
	}
	
    public static boolean isAssignableFromSet(ClassType type) {
        return getInstance(type).isAssignableFromSet0(type);
    }

	public static boolean isSubclass(ClassType type, ClassType supertype) {
		return getInstance(type).isSubclass0(type, supertype);
	}

	private static ClassModelUtil getInstance(ClassType classType) {
		ClassModelUtil instance = instances.get(classType.getClass());
		if(instance == null) {
			instance = new ClassModelUtil(classType.getFactory());
			instances.put(classType.getClass(), instance);
		}
		return instance;
	}
	
	private boolean isCollection0(ClassType type) {
		return isSubclass0(type, getKnownType(Collection.class));
	}

	private boolean isAssignableFromList0(ClassType type) {
		return isSubclass0(getKnownType(List.class), type.getTypeErasure());
	}

    private boolean isAssignableFromSet0(ClassType type) {
        return isSubclass0(getKnownType(Set.class), type.getTypeErasure());
    }

	private boolean isSubclass0(ClassType type, ClassType supertype) {
		Deque<ClassType> hierarchy = new ArrayDeque<ClassType>();
		hierarchy.add(type);
		while(!hierarchy.isEmpty()) {
			type = hierarchy.pop();
			if(supertype.equals(type.getTypeErasure())) {
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
        public boolean isVoid() {
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

		@Override
		public ClassType getTypeErasure() {
			return this;
		}

		@Override
		public boolean isTypeVariable() {
			return false;
		}
	};

	public static Type getClassItemType(ClassItem classItem) {
		Type dependencyType = null;
		if(classItem.isAttribute()) {
			dependencyType = classItem.getType();
		} else {
			Method method = (Method)classItem;
			if(method.isSetter()) {
				dependencyType = method.getParameters().get(0).getType();
			} else if(method.isGetter()) {
				dependencyType = method.getType();
			}
		}
		return dependencyType;
	}

}
