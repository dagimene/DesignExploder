package designexploder.model.extension.IoC.impl.spring;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.CharBuffer;

import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaModelException;

import designexploder.model.NodeContainer;
import designexploder.model.build.ModelBuilder;
import designexploder.resources.ResourcesLoader;

public class DexRuntimeGenerator implements ModelBuilder {

	private static final String DEX_RUNTIME_CU = "DexRuntime";
	private static final String DEX_PACKAGE = "dex";

	private static String DEX_RUNTIME_TEMPLATE;

	public static ModelBuilder create(IPackageFragmentRoot packageFragmentRoot) {
		return new DexRuntimeGenerator(packageFragmentRoot.getJavaProject(), packageFragmentRoot);
	}

	private IJavaProject project;
	private IPackageFragmentRoot generatedFragmentRoot;

	public DexRuntimeGenerator(IJavaProject project, IPackageFragmentRoot generatedFragmentRoot) {
		this.project = project;
		this.generatedFragmentRoot = generatedFragmentRoot;
	}

	@Override
	public NodeContainer build(NodeContainer diagram) {
		try {
			IPackageFragment dexPackage = generatedFragmentRoot.createPackageFragment(DEX_PACKAGE, true, null);
			dexPackage.createCompilationUnit(DEX_RUNTIME_CU, getTemplateCode(), true, null);
		} catch (JavaModelException e) {
			e.printStackTrace();
		}
		return diagram;
	}
	
	private String getTemplateCode() {
		if(DEX_RUNTIME_TEMPLATE == null) {
			Reader in = new BufferedReader(ResourcesLoader.loadJava(DEX_RUNTIME_CU));
			StringBuilder builder = new StringBuilder();
			int count;
			char[] cbuf = new char[1024];
			try {
				while((count = in.read(cbuf)) != -1) {
					builder.append(cbuf, 0, count);
				}
				DEX_RUNTIME_TEMPLATE = builder.toString();
			} catch (IOException e) {
				e.printStackTrace();
				DEX_RUNTIME_TEMPLATE = "";
			}
		}
		return DEX_RUNTIME_TEMPLATE;
	}

}
