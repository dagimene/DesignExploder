package designexploder.model.classnode.impl.eclipse.jdt;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.Signature;

import designexploder.model.classnode.ClassTypeFactory;
import designexploder.model.classnode.Type;
import designexploder.model.classnode.impl.ArrayTypeImpl;
import designexploder.model.classnode.impl.TypeImpl;

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

	public JDTClassType typeFor(Class<?> clazz) throws JavaModelException {
		return typeFor(clazz.getName());
	}

	public JDTClassType typeFor(IType type) {
		JDTClassType result = types.get(getFullyQualifiedName(type));
		if(result == null) {
			result = instantiate(type);
		}
		return result;
	}
	
	public JDTClassType typeFor(String fullyQualifiedName) throws JavaModelException {
		JDTClassType result = null;
		if(checkFullyQualifiedName(fullyQualifiedName)) {
			result = types.get(fullyQualifiedName);
			if(result == null) {
				IType type = null;
				type = project.findType(fullyQualifiedName);
				if(type != null) {
					result = typeFor(type);
				}
			}
		}
		return result;
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
			result = typeFor(getFullyQualifiedName(signature, context));
			break;
		default:
			result = null;
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
	 * @param simple An non array signature
	 * @param context
	 * @return
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
			}
		}
		return result;
	}

	private String getFullyQualifiedName(IType type) {
		return type.getFullyQualifiedName();
	}
}
