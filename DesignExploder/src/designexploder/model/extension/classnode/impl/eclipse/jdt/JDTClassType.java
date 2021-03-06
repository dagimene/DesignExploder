package designexploder.model.extension.classnode.impl.eclipse.jdt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;

import designexploder.model.extension.classnode.ClassType;
import designexploder.model.extension.classnode.ClassTypeFactory;
import designexploder.model.extension.classnode.ClassModelNatures;
import designexploder.model.extension.classnode.Type;
import designexploder.model.extension.classnode.impl.TypeImpl;

public class JDTClassType extends TypeImpl implements ClassType {

	private final IType type;
	
	private final ClassModelNatures nature;

	private final JDTClassTypeFactory factory;

	JDTClassType(IType type, JDTClassTypeFactory factory) {
		super(type.getFullyQualifiedName());
		this.type = type;
		this.factory = factory;
		this.nature = DexConstantUtil.resolveNature(type);
	}

	@Override
	public ClassType asClassType() {
		return this;
	}

	@Override
	public boolean isClass() {
		return nature == ClassModelNatures.CLASS;
	}

	@Override
	public boolean isInterface() {
		return nature == ClassModelNatures.INTERFACE;
	}

	@Override
	public boolean isEnum() {
		return nature == ClassModelNatures.ENUM;
	}

	@Override
	public ClassType getSuperclass() {
		ClassType result = null;
		try {
			String signature = type.getSuperclassTypeSignature();
			if(signature == null) {
				ClassType objectType = factory.typeFor(Object.class);
				if(this != objectType) {
					result = objectType;
				}
			} else {
				result = obtainType(signature);
			}
		} catch (JavaModelException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<ClassType> getInterfaces() {
		List<ClassType> result = null;
		try {
			String[] interfaces = type.getSuperInterfaceTypeSignatures();
			if(interfaces.length > 0) {
				result = new ArrayList<ClassType>(interfaces.length);
				for (String signature : interfaces) {
					ClassType interfaceType = obtainType(signature);
					if(interfaceType != null) {
						result.add(interfaceType);
					}
				}
			}
		} catch (JavaModelException e) {
			e.printStackTrace();
		}
		return result != null ? result : Collections.<ClassType>emptyList();
	}

	private ClassType obtainType(String signature) throws JavaModelException {
		Type supertype = factory.typeFor(signature, type);
		return supertype.isClassType() ? supertype.asClassType() : null;
	}

	@Override
	public List<Type> getTypeParameters() {
		return Collections.emptyList();
	}
	
	public ClassModelNatures getNature() {
		return nature;
	}

	@Override
	public ClassTypeFactory getFactory() {
		return factory;
	}

	@Override
	public ClassType getTypeErasure() {
		return this;
	}

    IType getType() {
        return type;
    }
}
