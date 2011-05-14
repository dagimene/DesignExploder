package designexploder.model.classnode.impl.eclipse.jdt;

import org.eclipse.jdt.core.Flags;
import org.eclipse.jdt.core.IField;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;

import designexploder.model.classnode.Attribute;
import designexploder.model.classnode.ClassConnection;
import designexploder.model.classnode.DexConstant;
import designexploder.model.classnode.Method;
import designexploder.model.classnode.Modifiable;
import designexploder.model.classnode.Type;
import designexploder.model.classnode.impl.ClassNodeModelFactoryImpl;

import static designexploder.model.classnode.DexConstant.*;

public class JDTModelFactory extends ClassNodeModelFactoryImpl<JDTClassNode, ClassConnection> {
	
	private JDTClassTypeFactory typesFactory;
	
	public JDTModelFactory(IJavaProject project) {
		this.typesFactory = JDTClassTypeFactory.getFactoryFor(project);
	}

	@Override
	public JDTClassNodeBuilder createNode() {
		return new JDTClassNodeBuilder();
	}

	public class JDTClassNodeBuilder extends NodeBuilderImpl {
		private IType type;
		
		public void setType(IType type) {
			this.type = type;
		}

		@Override
		protected JDTClassNode instantiate() {
			result = new JDTClassNode(typesFactory.typeFor(type));
			try {
				setMofiers(result, type.getFlags());
				buildMethods(result, type);
				buildAttributes(result, type);
			} catch (JavaModelException e) {
				e.printStackTrace();
			}
			return result;
		}

	}
	
	private void buildAttributes(JDTClassNode node, IType type) throws JavaModelException {
		for(IField attribute : type.getFields()) {
			try {
				Type attributeType = typesFactory.typeFor(attribute.getTypeSignature(), type);
				if(attributeType != null) {
					Attribute anAttribute = createAttribute(attribute.getElementName(), attributeType);
					setMofiers(anAttribute, attribute.getFlags());
					node.addAttribute(anAttribute);
				}
			} catch (JavaModelException e) {
				e.printStackTrace();
			}
		}
	}

	private void buildMethods(JDTClassNode node, IType type) throws JavaModelException {
		for(IMethod method : type.getMethods()) {
			try {
				Type methodType = typesFactory.typeFor(method.getReturnType(), type);
				if(methodType != null) {
					Method aMethod = createMethod(method.getElementName(), methodType);
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
			DexConstant modifier) {
		if(modifier != null) {
			modifiable.addModifier(modifier);
		}
	}
}