package designexploder.model.extension.IoC.impl.spring;

import designexploder.resources.ResourcesLoader;
import designexploder.util.EclipseUtil;
import dex.DexRuntime;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;

import designexploder.model.NodeContainer;
import designexploder.model.build.ModelBuilder;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SpringCodeGenerator implements ModelBuilder {

	public static ModelBuilder create(IPackageFragmentRoot packageFragmentRoot) {
		return new SpringCodeGenerator(packageFragmentRoot.getJavaProject(), packageFragmentRoot);
	}

	private IJavaProject project;
	private IPackageFragmentRoot generatedFragmentRoot;

	public SpringCodeGenerator(IJavaProject project, IPackageFragmentRoot generatedFragmentRoot) {
		this.project = project;
		this.generatedFragmentRoot = generatedFragmentRoot;
	}

	@Override
	public NodeContainer build(NodeContainer diagram) {
        InputStream inputStream = ResourcesLoader.loadJavaFile("DexRuntime");
        IFolder dexFolder =  EclipseUtil.createFolder((IContainer) generatedFragmentRoot.getResource(), "dex");
        IFile file = EclipseUtil.createFileHandler(dexFolder, "DexRuntime.java");
        EclipseUtil.createAndWriteFile(file, inputStream);
        return diagram;
	}
	
}
