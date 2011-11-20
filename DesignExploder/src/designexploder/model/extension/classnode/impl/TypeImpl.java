package designexploder.model.extension.classnode.impl;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import designexploder.model.extension.classnode.ArrayType;
import designexploder.model.extension.classnode.ClassType;
import designexploder.model.extension.classnode.Type;

public class TypeImpl implements Type {
	
	private static final Map<String, TypeImpl> instances = new HashMap<String, TypeImpl>(200);
	
	private static final Set<String> basicTypes = new HashSet<String>();

    public static final String VOID = "void".intern();
    public static final String EMPTY_STRING = "".intern();

    static {
		basicTypes.add("boolean");
		basicTypes.add("byte");
		basicTypes.add("char");
		basicTypes.add("int");
		basicTypes.add("float");
		basicTypes.add("long");
		basicTypes.add("double");
		basicTypes.add(VOID);
	}
	
	private final String firstname;
	private final String lastname;
	private final boolean basic;

	protected TypeImpl(String name) {
		// checkName(name);
		this.basic = basicTypes.contains(name);
		int dot = name.lastIndexOf('.');
		if(basic || dot == -1) {
			this.lastname = "";
			// Basic type names can be compared with == .
			this.firstname = basic ? name.intern() : name;
		} else {
			this.lastname = name.substring(0, dot);
			this.firstname = name.substring(dot + 1);
		}
	}
	
	public String getName() {
        // Basic type names can be compared with == .
		return this.lastname == EMPTY_STRING ? this.firstname : (this.lastname + "." + this.firstname);
	}
	
	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public boolean isBasic() {
		return basic;
	}

    @Override
    public boolean isVoid() {
        // Basic type names can be compared with == .
        return basic && firstname == VOID;
    }

    public static Type instanceFor(String name) {
		TypeImpl instance = instances.get(name);
		if(instance == null) {
			instance = new TypeImpl(name);
			instances.put(name, instance);
		}
		return instance;
	}

	public boolean isClassType() {
		return !isBasic() && !isArray();
	}

	public ClassType asClassType() {
		if(isClassType()) {
			throw new UnsupportedOperationException("This type implementation doesn't support ClassType interface.");
		}
		return null;
	}

	@Override
	public boolean isArray() {
		return false;
	}

	@Override
	public ArrayType asArrayType() {
		return null;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + " [ "+ getName() +  (basic ? " (primitive)" : " (class)") +" ]";
	}

	@Override
	public boolean isTypeVariable() {
		return false;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TypeImpl type = (TypeImpl) o;

        if (basic != type.basic) return false;
        if (firstname != null ? !firstname.equals(type.firstname) : type.firstname != null) return false;
        if (lastname != null ? !lastname.equals(type.lastname) : type.lastname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = firstname != null ? firstname.hashCode() : 0;
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (basic ? 1 : 0);
        return result;
    }
}
