package designexploder.model.extension.classnode.impl.eclipse.jdt;

import designexploder.model.extension.classnode.*;
import org.eclipse.jdt.core.Flags;
import org.eclipse.jdt.core.IField;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.ILocalVariable;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;

import designexploder.model.Node;
import designexploder.model.extension.classnode.impl.ClassModelFactory;
import designexploder.model.impl.BasicModelFactory;
import designexploder.util.adt.IdUtil;

import static designexploder.model.extension.classnode.ClassModelNatures.*;

public class JDTModelFactory {
	
	private JDTClassTypeFactory typesFactory;
	
	public JDTModelFactory(IJavaProject project) {
		this.typesFactory = JDTClassTypeFactory.getFactoryFor(project);
	}

	public Node createNode(IType type) {
        ClassType objectClass = null;
        try {
            objectClass = typesFactory.typeFor(Object.class);
        } catch (JavaModelException e) {
            // Ignore. Object methods will be listed.
            e.printStackTrace();
        }
        JDTClassType jdtType = typesFactory.typeFor(type);
		ClassNode classNode = ClassModelFactory.getInstance().createClassNode(jdtType);
		Node result = BasicModelFactory.getInstance().createNode();
		try {
			classNode.setNature(jdtType.getNature());
			setModifiers(classNode, type.getFlags());
            JDTClassType classType = jdtType;
            boolean inherited = false;
            do {
                buildMethods(result, classNode, classType.getType(), inherited);
                buildAttributes(result, classNode, classType.getType(), inherited);
                classType = classType.getSuperclass() instanceof JDTClassType ? (JDTClassType) classType.getSuperclass() : null;
                inherited = true;
            } while(classType != null && classType != objectClass);
		} catch (JavaModelException e) {
			e.printStackTrace();
		}
		result.addExtension(classNode);
		result.setId(IdUtil.createTypeId(jdtType).toString());
		return result;
	}

	private void buildAttributes(Node node, ClassNode classNode, IType type, boolean inherited) throws JavaModelException {
		for(IField attribute : type.getFields()) {
			try {
				Type attributeType = typesFactory.typeFor(attribute.getTypeSignature(), type);
				if(attributeType != null) {
					Attribute anAttribute = ClassModelFactory.getInstance().createAttribute(node, attribute.getElementName(), attributeType);
					setModifiers(anAttribute, attribute.getFlags());
					if(attribute.isEnumConstant()) {
						anAttribute.addModifier(ENUM);
					}
                    anAttribute.setInherited(inherited);
					classNode.addAttribute(anAttribute);
				}
			} catch (JavaModelException e) {
				e.printStackTrace();
			}
		}
	}

	private void buildMethods(Node node, ClassNode classNode, IType type, boolean inherited) throws JavaModelException {
		for(IMethod method : type.getMethods()) {
			try {
				Type methodType = typesFactory.typeFor(method.getReturnType(), type);
				ILocalVariable[] parameterElements = method.getParameters();
				Parameter[] parameters = new Parameter[parameterElements.length];
				if(methodType != null && parseParameters(parameterElements, type, parameters)) {
					Method aMethod = ClassModelFactory.getInstance().createMethod(node, method.getElementName(), methodType);
					setModifiers(aMethod, method.getFlags());
					for (Parameter parameter : parameters) {
						aMethod.addParameter(parameter);
					}
                    aMethod.setInherited(inherited);
                    if(!classNode.getMethods().contains(aMethod)) {
					    classNode.addMethod(aMethod);
                    }
				}
			} catch (JavaModelException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean parseParameters(ILocalVariable[] parameterElements, IType type, Parameter[] parameters) throws JavaModelException {
		for (int i = 0; i < parameterElements.length; i++) {
			ILocalVariable parameterElement = parameterElements[i];
			Type parameterType = typesFactory.typeFor(parameterElement.getTypeSignature(), type);
			if(parameterType != null) {
				parameters[i] = ClassModelFactory.getInstance().createParameter(parameterElement.getElementName(), parameterType);
			} else {
				return false;
			}
		}
		return true;
	}
	
	private void setModifiers(Modifiable modifiable, int flags) {
		addModifierIfNotNull(modifiable, DexConstantUtil.getAccessDexContant(flags));
		addModifierIfNotNull(modifiable, Flags.isStatic(flags) ? STATIC : null);
		addModifierIfNotNull(modifiable, Flags.isAbstract(flags) ? ABSTRACT : null);
		addModifierIfNotNull(modifiable, Flags.isFinal(flags) ? FINAL : null);
		addModifierIfNotNull(modifiable, Flags.isNative(flags) ? NATIVE : null);
	}

	private void addModifierIfNotNull(Modifiable modifiable,
			ClassModelNatures modifier) {
		if(modifier != null) {
			modifiable.addModifier(modifier);
		}
	}
}