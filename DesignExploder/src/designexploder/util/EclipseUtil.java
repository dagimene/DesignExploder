package designexploder.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.NodeFactory;
import nu.xom.ParsingException;

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
					int ix = name.lastIndexOf('.');
					return ix != -1 ? name.substring(ix + 1) : "";
				}
			}, IResource.NONE);
		} catch (CoreException e) {
			e.printStackTrace();
		}

		return result;
	}
	
	public static Document readXMLDocument(IFile file) throws CoreException {
		return readXMLDocument(file, null);
	}
	
	public static Document readXMLDocument(IFile file, NodeFactory factory) throws CoreException {
		InputStream contents = null;
		Document document = null; 
		try {
			contents = file.getContents();
			Builder builder = factory != null ? new Builder(factory) : new Builder();
			document = builder.build(contents);
		} catch (ParsingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(contents != null) {
				try {
					contents.close();
				} catch (IOException e) {}
			}
		}
		return document;
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