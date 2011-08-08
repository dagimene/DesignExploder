package designexploder.model.extension.IoC.impl.spring;

import java.util.Iterator;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;

import designexploder.model.Node;
import designexploder.model.NodeContainer;
import designexploder.model.build.BaseModelBuilder;
import designexploder.model.build.ModelBuilder;
import designexploder.model.extension.IoC.ApplicationContext;
import designexploder.model.extension.IoC.impl.IoCModelFactory;
import designexploder.model.extension.IoC.impl.spring.parsing.SpringConfigFile;
import designexploder.model.impl.BasicModelFactory;
import designexploder.util.adt.IdUtil;
import designexploder.util.adt.Pair;

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
		SpringBeansModelFactory factory = new SpringBeansModelFactory(project);
		Iterator<Pair<IFile, SpringConfigFile>> iterator = new SpringConfigFilesIterator(contextsFragmentRoot);
		while(iterator.hasNext()) {
			Pair<IFile, SpringConfigFile> next = iterator.next();
			NodeContainer context;
			if(next.getFirst().getName().equals(MAIN_CONTEXT_FILE)) {
				context = diagram;
			} else {
				context = BasicModelFactory.getInstance().createContainerNode();
				initializeApplicationContext(context, next.getFirst().getFullPath().makeRelativeTo(contextsFragmentRoot.getPath()).toString());
				((Node)context).setResizeable(true);
				super.addNode((Node) context);
			}
			Set<Node> beans = factory.getBeans(context, next.getSecond(), diagram);
			for (Node bean : beans) {
				super.addNode(bean, context);
			}
		}
		return diagram;
	}

	private void initializeApplicationContext(NodeContainer contextNode, String filename) {
		ApplicationContext context = IoCModelFactory.getInstance().createApplicationContext();
		contextNode.setId(IdUtil.createContextId(filename).toString());
		context.setName(transformFilenameToName(filename));
		contextNode.addExtension(context);
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
