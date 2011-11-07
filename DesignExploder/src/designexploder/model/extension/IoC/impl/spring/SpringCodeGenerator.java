package designexploder.model.extension.IoC.impl.spring;

import designexploder.resources.ResourcesLoader;
import designexploder.util.EclipseUtil;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;

import designexploder.model.NodeContainer;
import designexploder.model.build.ModelBuilder;

import java.io.InputStream;

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

    private static final String[] CLASSES = new String[]{"DexRuntime", "DexContextScopeImpl", "DexContextScope", "CustomAutowireBeanFactory", "DexContextInstance"};

	@Override
	public NodeContainer build(NodeContainer diagram) {
        for(String className : CLASSES) {
            createClass(className);
        }
        return diagram;
	}

    private void createClass(String className) {
        InputStream inputStream = ResourcesLoader.loadJavaFile(className);
        assert inputStream != null;
        IFolder dexFolder =  EclipseUtil.createFolder((IContainer) generatedFragmentRoot.getResource(), "dex");
        IFile file = EclipseUtil.createFileHandler(dexFolder, className+".java");
        EclipseUtil.createAndWriteFile(file, inputStream);
    }

}
