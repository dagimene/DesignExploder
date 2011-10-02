package designexploder.util;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaModelException;

public class DexUtils {

	public static final String IOC_CONTEXTS_PACKAGE = "dex.ioc.contexts";
	public static final String BEANS_FOLDER = "beans";
	public static final String CONTEXTS_FOLDER = "contexts";
	public static final String GENERATED_FOLDER = "generated";
	public static final String EMPTY_XML_CONTAINER_CONTENT_FILE = "designexploder/resources/xml/emptyContainer.xml";

	public static void initializeDexProjectStructure(IJavaProject project) {
		findOrCreatePackageFragmentRoot(project, BEANS_FOLDER);
		findOrCreatePackageFragmentRoot(project, CONTEXTS_FOLDER);
		findOrCreatePackageFragmentRoot(project, GENERATED_FOLDER);
	}

	public static IPackageFragmentRoot getBeansPackageRoot(IJavaProject project) {
		return findOrCreatePackageFragmentRoot(project, BEANS_FOLDER);
	}
	
	public static IPackageFragmentRoot getContextsPackageRoot(IJavaProject project) {
		return findOrCreatePackageFragmentRoot(project, CONTEXTS_FOLDER);
	}

	public static IPackageFragmentRoot getGeneratedPackageRoot(IJavaProject project) {
		return findOrCreatePackageFragmentRoot(project, GENERATED_FOLDER);
	}

	public static IPackageFragmentRoot findOrCreatePackageFragmentRoot(IJavaProject javaProject, String name) {
		IPackageFragmentRoot result = null;
		try {
			for (IPackageFragmentRoot aPackageRoot : javaProject.getAllPackageFragmentRoots()) {
				if(!aPackageRoot.isArchive()
					&& aPackageRoot.getKind() == IPackageFragmentRoot.K_SOURCE
					&& aPackageRoot.getElementName().equals(name)) {
						result = aPackageRoot;
						break;
				}
			}
			if(result == null) {
				result = EclipseUtil.createSourceFolder(javaProject, name);
			}
		} catch (JavaModelException e) {
			e.printStackTrace();
		} catch (CoreException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static IPackageFragment findOrCreatePackage(IPackageFragmentRoot root, String packageName) {
		IPackageFragment result = root.getPackageFragment(packageName);
		if(!result.exists()) {
			try {
				root.createPackageFragment(packageName, true, null);
			} catch (JavaModelException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public static IPackageFragment getDefaultAppsContextPackage(IProject project) {
		IJavaProject javaProject = EclipseUtil.getJavaProject(project);
		IPackageFragmentRoot root = findOrCreatePackageFragmentRoot(javaProject, CONTEXTS_FOLDER);
		IPackageFragment iocPackage = findOrCreatePackage(root, DexUtils.IOC_CONTEXTS_PACKAGE);
		return iocPackage;
	}

	public static IFolder getDefaultAppsContextFolder(IProject project) {
		IPackageFragment iocPackage = getDefaultAppsContextPackage(project);
		return (IFolder) iocPackage.getResource();
	}


	/*
	 		InputStream emptyIoC = DexUtils.class.getClassLoader().getResourceAsStream(EMPTY_XML_CONTAINER_CONTENT_FILE);
			try {
				file.create(emptyIoC, true, null);
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		return EclipseUtil.getBeansProject(project.getProject()).getConfig(file);*/
	//}

}
