package designexploder.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceProxy;
import org.eclipse.core.resources.IResourceProxyVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.springframework.ide.eclipse.beans.core.BeansCorePlugin;
import org.springframework.ide.eclipse.beans.core.model.IBeansModel;
import org.springframework.ide.eclipse.beans.core.model.IBeansProject;

public class EclipseUtil {

	public static IJavaProject getJavaProject(IProject project) {
		IJavaProject javaProject = null;
		try {
			if(project.isNatureEnabled("org.eclipse.jdt.core.javanature")) {
				javaProject = JavaCore.create(project);
			}
		} catch (CoreException e) {
			e.printStackTrace();
		}
		return javaProject;
	}
	
	public static IBeansProject getBeansProject(IProject project) {
		IBeansModel beanModel = BeansCorePlugin.getModel();
		IBeansProject beanProject = beanModel.getProject(project);
		return beanProject;
	}

	public static Set<IFile> getFiles(IContainer container, final Set<String> suffixes) {
		final Set<IFile> result = new HashSet<IFile>();
		
		try {
			container.accept(new IResourceProxyVisitor() {
				@Override
				public boolean visit(IResourceProxy proxy) throws CoreException {
					if(proxy.getType() == IResource.FILE && suffixes.contains(extension(proxy.getName()))) {
						result.add((IFile) proxy.requestResource());
					}
					return true;
				}
	
				private String extension(String name) {
					return name.substring(name.lastIndexOf('.'));
				}
			}, IResource.NONE);
		} catch (CoreException e) {
			e.printStackTrace();
		}

		return result;
	}
	
	public static IPackageFragmentRoot createSourceFolder(IJavaProject javaProject, String name) throws CoreException {
		// Create folder
		IFolder folder = javaProject.getProject().getFolder(name);
		if(!folder.exists()) {
			folder.create(true, true, null);
		}
		
		// Add folder to build path (make it a source folder)
		IPackageFragmentRoot packageRoot;
		IClasspathEntry[] classPath = javaProject.getRawClasspath();
		classPath = Arrays.copyOf(classPath, classPath.length + 1);
		classPath[classPath.length - 1] = JavaCore.newSourceEntry(folder.getFullPath());
		javaProject.setRawClasspath(classPath, null);
		
		// Obtain Package Fragment Root from folder
		packageRoot = javaProject.getPackageFragmentRoot(folder);
		return packageRoot;
	}
}
