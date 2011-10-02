package designexploder.model.extension.IoC.impl.spring;

import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;

import designexploder.model.NodeContainer;
import designexploder.model.build.ModelBuilder;

public class SpringCodeGenerator implements ModelBuilder {

	public static ModelBuilder create(IPackageFragmentRoot packageFragmentRoot) {
		return new SpringCodeGenerator(packageFragmentRoot.getJavaProject(), packageFragmentRoot);
	}

	private IJavaProject project;
	private IPackageFragmentRoot contextsFragmentRoot;

	public SpringCodeGenerator(IJavaProject project, IPackageFragmentRoot contextsFragmentRoot) {
		this.project = project;
		this.contextsFragmentRoot = contextsFragmentRoot;
	}

	@Override
	public NodeContainer build(NodeContainer diagram) {
		return diagram;
	}
	
}
