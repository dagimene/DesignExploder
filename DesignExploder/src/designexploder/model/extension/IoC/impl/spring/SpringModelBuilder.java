package designexploder.model.extension.IoC.impl.spring;

import java.util.Iterator;

import nu.xom.Document;

import org.eclipse.core.resources.IFile;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;

import designexploder.model.Node;
import designexploder.model.NodeContainer;
import designexploder.model.build.BaseModelBuilder;
import designexploder.model.build.ModelBuilder;
import designexploder.model.extension.IoC.ApplicationContext;
import designexploder.model.extension.IoC.impl.IoCModelFactory;
import designexploder.model.impl.BasicModelFactory;
import designexploder.util.Pair;

public class SpringModelBuilder extends BaseModelBuilder {

	private static final String MAIN_CONTEXT_FILE = "main.xml";
	private final IPackageFragmentRoot contextsFragmentRoot;
	private final IJavaProject project;

	public static ModelBuilder create(IPackageFragmentRoot packageFragmentRoot) {
		return new SpringModelBuilder(packageFragmentRoot.getJavaProject(), packageFragmentRoot);
	}

	public SpringModelBuilder(IJavaProject project, IPackageFragmentRoot packageFragmentRoot) {
		this.project = project;
		this.contextsFragmentRoot = packageFragmentRoot;
	}

	@Override
	public NodeContainer build(NodeContainer diagram) {
		diagram = super.build(diagram);
		initializeApplicationContext(diagram, "main.ctx");
		SpingBeansModelFactory factory = new SpingBeansModelFactory(project);
		Iterator<Pair<IFile, Document>> iterator = new ContextsFilesIterator(contextsFragmentRoot);
		while(iterator.hasNext()) {
			Pair<IFile, Document> next = iterator.next();
			NodeContainer context;
			if(next.getFirst().getName().equals(MAIN_CONTEXT_FILE)) {
				context = diagram;
			} else {
				context = BasicModelFactory.getInstance().createContainerNode();
				initializeApplicationContext(context, next.getFirst().getProjectRelativePath().toString());
				super.addNode((Node) context);
			}
			factory.addBeansToContainer(context, next.getSecond(), diagram);
		}
		return diagram;
	}

	private void initializeApplicationContext(NodeContainer diagram, String filename) {
		ApplicationContext context = IoCModelFactory.getInstance().createApplicationContext();
		context.setName(transformFilenameToName(filename));
		diagram.addExtension(ApplicationContext.class, context);
		diagram.setId(filename);
	}

	/**
	 * Creates a default name from filename. This name should be replaced with .dex model information.
	 * @param pathname
	 * @return
	 */
	private String transformFilenameToName(String pathname) {
		int startIx = pathname.lastIndexOf('/');
		if(startIx == -1) startIx = 0;
		int endIx = pathname.lastIndexOf('.');
		if(endIx == -1 ) endIx = pathname.length();
		return pathname.substring(startIx, endIx);
	}
}
