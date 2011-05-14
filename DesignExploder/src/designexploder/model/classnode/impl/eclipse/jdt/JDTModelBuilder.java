package designexploder.model.classnode.impl.eclipse.jdt;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;

import designexploder.model.build.ModelDataProvider.NodeDataProvider;
import designexploder.model.build.ModelFactory.NodeBuilder;
import designexploder.model.classnode.ClassConnection;
import designexploder.model.classnode.build.ClassNodeModelBuilder;
import designexploder.model.classnode.impl.eclipse.jdt.JDTModelFactory.JDTClassNodeBuilder;

public class JDTModelBuilder extends ClassNodeModelBuilder<JDTClassNode, ClassConnection> {
	@Override
	protected void loadNodeData(NodeDataProvider nodeData,
			NodeBuilder<JDTClassNode, ClassConnection> nodeBuilder) {
		((JDTClassNodeBuilder)nodeBuilder).setType(((JDTNodeDataProvider)nodeData).getIType());
		super.loadNodeData(nodeData, nodeBuilder);
	}

	public JDTModelBuilder(IJavaProject project) {
		super(new JDTModelDataProvider(project), new JDTModelFactory(project));
	}

	public static JDTModelBuilder create() {
		IJavaProject javaProject = null;
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot root = workspace.getRoot();
		for (IProject project : root.getProjects()) {
			try {
				if(project.isNatureEnabled("org.eclipse.jdt.core.javanature")) {
					javaProject = JavaCore.create(project);
				}
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		return javaProject != null ? new JDTModelBuilder(javaProject) : null;
	}
}
