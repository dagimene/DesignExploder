package designexploder.model.extension.IoC.impl.spring;

import nu.xom.Document;

import org.eclipse.jdt.core.IJavaProject;

import designexploder.model.NodeContainer;

public class SpingBeansModelFactory {

	@SuppressWarnings("unused")
	private final IJavaProject project;

	public SpingBeansModelFactory(IJavaProject project) {
		this.project = project;
	}

	public void addBeansToContainer(NodeContainer container, Document second, NodeContainer diagram) {
		
	}

}
