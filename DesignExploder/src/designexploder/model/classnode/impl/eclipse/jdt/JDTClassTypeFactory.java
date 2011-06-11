package designexploder.model.classnode.impl.eclipse.jdt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.Signature;

import designexploder.model.classnode.ClassType;
import designexploder.model.classnode.ClassTypeFactory;
import designexploder.model.classnode.Type;
import designexploder.model.classnode.impl.ArrayTypeImpl;
import designexploder.model.classnode.impl.ParameterizedClassTypeImpl;
import designexploder.model.classnode.impl.TypeImpl;
import designexploder.model.classnode.impl.UnresolvedClassTypeImpl;

public class JDTClassTypeFactory implements ClassTypeFactory {

	/* STATIC */
	
	private static Map<IJavaProject, JDTClassTypeFactory> factories = new HashMap<IJavaProject, JDTClassTypeFactory>();

	public static JDTClassTypeFactory getFactoryFor(IJavaProject project) {
		JDTClassTypeFactory result = factories.get(project);
		if(result == null) {
			result = new JDTClassTypeFactory(project);
			factories.put(project, result);
		}
		return result;
	}
	
	/* INSTANCE */
	
	private final IJavaProject project;
	private Map<String, JDTClassType> types = new HashMap<String, JDTClassType>();
	
	private JDTClassTypeFactory(IJavaProject project) {
		this.project = project;
	}
	
	/* PUBLIC */

	public ClassType typeFor(Class<?> clazz) throws JavaModelException {
		return typeFor(clazz.getName());
	}

	public JDTClassType typeFor(IType type) {
		JDTClassType result = types.get(getFullyQualifiedName(type));
		if(result == null) {
			result = instantiate(type);
		}
		return result;
	}
	
	public ClassType typeFor(String fullyQualifiedName) throws JavaModelException {
		ClassType result = null;
		if(checkFullyQualifiedName(fullyQualifiedName)) {
			result = types.get(fullyQualifiedName);
			if(result == null) {
				IType type = null;
				type = project.findType(fullyQualifiedName);
				if(type != null) {
					result = typeFor(type);
				} else {
					result = unresolvedTypeFor(fullyQualifiedName);
				}
			}
		}
		return result;
	}
	
	private ClassType unresolvedTypeFor(String name) {
		return UnresolvedClassTypeImpl.instanceForUnknownType(name, this);
	}

	public Type typeFor(String signature, IType context) throws JavaModelException {
		Type result;
		switch (Signature.getTypeSignatureKind(signature)) {
		case Signature.ARRAY_TYPE_SIGNATURE:
			Type innerType = typeFor(Signature.getElementType(signature), context);
			result = innerType != null ? ArrayTypeImpl.instanceFor(innerType, Signature.getArrayCount(signature)) : null;
			break;
		case Signature.BASE_TYPE_SIGNATURE:
			result = TypeImpl.instanceFor(Signature.toString(signature));
			break;
		case Signature.CLASS_TYPE_SIGNATURE:
			result = buildClassType(signature, context);
			break;
		case Signature.TYPE_VARIABLE_SIGNATURE:
		case Signature.WILDCARD_TYPE_SIGNATURE:
		case Signature.CAPTURE_TYPE_SIGNATURE:
			result = UnresolvedClassTypeImpl.instanceForTypeVariable(Signature.toString(signature), this);
			break;
		default:
			throw new IllegalArgumentException("Invalid signature type for '"+signature+"': "+Signature.getTypeSignatureKind(signature));
		}
		return result;
	}

	private ClassType buildClassType(String signature, IType context) throws JavaModelException {
		ClassType result = null;
		Type type = typeFor(getFullyQualifiedName(signature, context));
		if(type.isClassType()) {
			result = type.asClassType();
			String[] parametersSignatures = Signature.getTypeArguments(signature);
			if(parametersSignatures.length != 0) {
				List<Type> typeParameters = new ArrayList<Type>(parametersSignatures.length);
				for (String parameterSignature : parametersSignatures) {
					typeParameters.add(typeFor(parameterSignature, context));
				}
				result = new ParameterizedClassTypeImpl(result, typeParameters); 
			}
		}
		return result;
	}

	public Type typeFor(String lastname, String firstname) throws JavaModelException {
		return checkFirstname(firstname) ? typeFor(Signature.toQualifiedName(new String[] {lastname, firstname})) : null;
	}

	/* PRIVATE */

	private JDTClassType instantiate(IType type) {
		JDTClassType result = new JDTClassType(type, this);
		types.put(result.getName(), result);
		return result;
	}

	private boolean checkFirstname(String firstname) {
		return !firstname.contains(".");
	}

	private boolean checkFullyQualifiedName(String fullyQualifiedName) {
		return !fullyQualifiedName.contains("$");
	}

	/**
	 * 
	 * @param signature A type signature
	 * @param context
	 * @return the fully qualified type signature 
	 * @throws JavaModelException 
	 */
	private String getFullyQualifiedName(String signature, IType context) throws JavaModelException {
		String result = null;
		String erasure = Signature.getTypeErasure(signature);
		if(erasure.charAt(0) == Signature.C_RESOLVED) {
			result = Signature.toString(erasure);
		} else {
			String[][] posibleFullyQualifiedNames = context.resolveType(Signature.toString(erasure));
			if(posibleFullyQualifiedNames != null) {
				result = Signature.toQualifiedName(posibleFullyQualifiedNames[0]);
			} else {
				result = Signature.toString(erasure);
			}
		}
		return result;
	}

	private String getFullyQualifiedName(IType type) {
		return type.getFullyQualifiedName();
	}
}
