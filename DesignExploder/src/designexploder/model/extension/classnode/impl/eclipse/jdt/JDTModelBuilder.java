package designexploder.model.extension.classnode.impl.eclipse.jdt;

import java.util.Iterator;

import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;

import designexploder.model.Node;
import designexploder.model.NodeContainer;
import designexploder.model.build.BaseModelBuilder;

public class JDTModelBuilder extends BaseModelBuilder {

	private final IJavaProject project;
	private final IPackageFragmentRoot packageRoot;

	public static JDTModelBuilder create(IJavaProject project) {
		return new JDTModelBuilder(project, null);
	}
	
	public static JDTModelBuilder create(IPackageFragmentRoot beansPackageRoot) {
		return new JDTModelBuilder(beansPackageRoot.getJavaProject(), beansPackageRoot);
	}

	public JDTModelBuilder(IJavaProject project, IPackageFragmentRoot packageRoot) {
		this.project = project;
		this.packageRoot = packageRoot;
	}

	@Override
	public NodeContainer build(NodeContainer diagram) {
		diagram = super.build(diagram);
		JDTModelFactory factory = new JDTModelFactory(project);
		try {
			Iterator<IType> iterator = packageRoot != null ? new ITypesIterator(packageRoot) : new ITypesIterator(project);
			while(iterator.hasNext()) {
				Node newNode = factory.createNode(iterator.next());
				if(diagram.findNode(newNode.getId()) == null) {
					super.addNode(newNode);
				}
			}
		} catch (JavaModelException e) {
			e.printStackTrace();
		}
		return diagram;
	}

}
