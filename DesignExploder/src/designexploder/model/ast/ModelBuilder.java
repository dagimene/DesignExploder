package designexploder.model.ast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.ui.IEditorInput;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.Flags;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IField;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;

import designexploder.model.Diagram;
import designexploder.model.ModelFactory;
import designexploder.model.classnode.Attribute;
import designexploder.model.classnode.ClassModelFactory;
import designexploder.model.classnode.ClassNode;
import designexploder.model.classnode.ClassSection;
import designexploder.model.classnode.DexConstant;
import designexploder.model.classnode.Method;

public class ModelBuilder {

	private List<ClassNode> nodes = new ArrayList<ClassNode>();
	private ClassModelFactory cmf;
	
	public Diagram buildModel(IEditorInput input) {

		// Get all projects in the workspace
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot root = workspace.getRoot();
		IProject[] projects = root.getProjects();
		
		cmf = ClassModelFactory.getFactory();
		
		for (IProject project : projects) {
			try {
				if (project.isNatureEnabled("org.eclipse.jdt.core.javanature")) {
					loadProject(JavaCore.create(project));
				}
			} catch (CoreException e) {
				e.printStackTrace();
				// TODO: Ignore the exception. (The project does not exists or its not opened.)
			}
		}
		
		Diagram diagram = ModelFactory.getFactory().createDiagram();
		diagram.setNodes(nodes);
		//AutolayoutFactory.getAutolayout().autolayout(diagram);
		return diagram;
		//return ModelUtils.createClassDummyModel();
	}

	private void loadProject(IJavaProject project) {
		try {
			for (IPackageFragment pkg : project.getPackageFragments()) {
				if(pkg.getKind() == IPackageFragmentRoot.K_SOURCE) {
					for(ICompilationUnit cu : pkg.getCompilationUnits()) {
						for (IType type : cu.getTypes()) {
							loadType(type);
						}
					}
				}
			}
		} catch (JavaModelException e) {
			e.printStackTrace();
			// TODO: Log and ignore
		}
	}

	private void loadType(IType type) throws JavaModelException {
		String className = type.getFullyQualifiedName('$');
		if(!className.contains("$")) { //Discard inner classes
			IType supertype = type.newSupertypeHierarchy(null).getSuperclass(type);
			ClassNode node = cmf.createClassNode(cmf.createType(className), supertype != null ?
									cmf.createType(supertype.getFullyQualifiedName()) :
									null, resolveNature(type));
			ClassSection methods = createMethodsMember(type);
			ClassSection attributes = createAttributesMember(type);
			node.setSections(Arrays.asList(new ClassSection[]{attributes, methods}));
			nodes.add(node);
		}
	}
	
	private ClassSection createMethodsMember(IType type) throws JavaModelException {
		ClassSection methods = cmf.createSection();
		methods.setNature(DexConstant.METHOD);
		List<Method> methodsList = new ArrayList<Method>(type.getMethods().length);
		for(IMethod method : type.getMethods()) {
			Method aMethod = cmf.createMethod(method.getElementName(), cmf.createType(method.getReturnType()), DexConstant.METHOD);
			aMethod.setModifiers(resolveNature(method.getFlags()));
			/*Modifiable param1 = cmf.createModifiable("column", new TypeImpl("dex.base.Column"));
			p1.setParameters(Arrays.asList(new Modifiable[] {param1}));
			p1.setModifiers(Arrays.asList(new DexConstant[]{DexConstant.PUBLIC, DexConstant.STATIC}));*/
			methodsList.add(aMethod);
		}
		methods.setMembers(methodsList);
		return methods;
	}

	private ClassSection createAttributesMember(IType type) throws JavaModelException {
		ClassSection attributes = cmf.createSection();
		attributes.setNature(DexConstant.ATTRIBUTE);
		List<Attribute> attributeList = new ArrayList<Attribute>();
		for (IField field : type.getFields()) {
			Attribute attribute = cmf.createAttribute(field.getElementName(), cmf.createType(field.getTypeSignature()));
			attribute.setModifiers(resolveNature(field.getFlags()));
			attributeList.add(attribute);
		}
		attributes.setMembers(attributeList);
		return attributes;
	}

	private DexConstant resolveNature(IType type) throws JavaModelException {
		int flags = type.getFlags();
		if(Flags.isInterface(flags)) {
			return DexConstant.INTERFACE;
		}
		if(Flags.isEnum(flags)) {
			return DexConstant.ENUM;
		}
		if(Flags.isAbstract(flags)) {
			return DexConstant.ABSTRACT_CLASS;
		}
		return DexConstant.CLASS;
	}
	
	private List<DexConstant> resolveNature(int flags) throws JavaModelException {
		List<DexConstant> modifiers = new ArrayList<DexConstant>(5);
		if(Flags.isStatic(flags)) {
			modifiers.add(DexConstant.STATIC);
		}
		if(Flags.isAbstract(flags)) {
			modifiers.add(DexConstant.ABSTRACT);
		}
		if(Flags.isFinal(flags)) {
			modifiers.add(DexConstant.FINAL);
		}
		if(Flags.isNative(flags)) {
			modifiers.add(DexConstant.NATIVE);
		}
		if(Flags.isPublic(flags)) {
			modifiers.add(DexConstant.PUBLIC);
		} else if(Flags.isPrivate(flags)) {
			modifiers.add(DexConstant.PRIVATE);
		} else if(Flags.isProtected(flags)) {
			modifiers.add(DexConstant.PROTECTED);
		}
		return modifiers;
	}
}

class HalfDefinedRelation {
	
	ClassNode node;
	IType missing;
	boolean sourceMissing;
	
}

