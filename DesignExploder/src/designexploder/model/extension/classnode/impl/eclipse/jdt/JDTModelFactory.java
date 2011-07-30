package designexploder.model.extension.classnode.impl.eclipse.jdt;

import org.eclipse.jdt.core.Flags;
import org.eclipse.jdt.core.IField;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;

import designexploder.model.Node;
import designexploder.model.extension.classnode.Attribute;
import designexploder.model.extension.classnode.ClassNode;
import designexploder.model.extension.classnode.ClassModelNatures;
import designexploder.model.extension.classnode.Method;
import designexploder.model.extension.classnode.Modifiable;
import designexploder.model.extension.classnode.Type;
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
		JDTClassType jdtType = typesFactory.typeFor(type);
		ClassNode classNode = ClassModelFactory.getInstance().createClassNode(jdtType);
		try {
			classNode.setNature(jdtType.getNature());
			setMofiers(classNode, type.getFlags());
			buildMethods(classNode, type);
			buildAttributes(classNode, type);
		} catch (JavaModelException e) {
			e.printStackTrace();
		}
		Node result = BasicModelFactory.getInstance().createNode();
		result.addExtension(ClassNode.class, classNode);
		result.setId(IdUtil.createClassId(jdtType.getName()).toString());
		return result;
	}
	
	private void buildAttributes(ClassNode node, IType type) throws JavaModelException {
		for(IField attribute : type.getFields()) {
			try {
				Type attributeType = typesFactory.typeFor(attribute.getTypeSignature(), type);
				if(attributeType != null) {
					Attribute anAttribute = ClassModelFactory.getInstance().createAttribute(attribute.getElementName(), attributeType);
					setMofiers(anAttribute, attribute.getFlags());
					node.addAttribute(anAttribute);
				}
			} catch (JavaModelException e) {
				e.printStackTrace();
			}
		}
	}

	private void buildMethods(ClassNode node, IType type) throws JavaModelException {
		for(IMethod method : type.getMethods()) {
			try {
				Type methodType = typesFactory.typeFor(method.getReturnType(), type);
				if(methodType != null) {
					Method aMethod = ClassModelFactory.getInstance().createMethod(method.getElementName(), methodType);
					setMofiers(aMethod, method.getFlags());
					node.addMethod(aMethod);
				}
			} catch (JavaModelException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void setMofiers(Modifiable modifiable, int flags) {
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