package designexploder.model.extension.IoC.impl.spring;

import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IPackageFragmentRoot;

import designexploder.model.extension.IoC.impl.spring.parsing.SpringConfigFile;
import designexploder.model.extension.IoC.impl.spring.parsing.SpringXMLNodeFactory;
import designexploder.util.EclipseUtil;
import designexploder.util.adt.Pair;

import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Node;
import nu.xom.NodeFactory;

public class SpringConfigFilesIterator implements Iterator<Pair<IFile, SpringConfigFile>> {

	private Pair<IFile, SpringConfigFile> next;
	private Iterator<IFile> candidates;
	private NodeFactory factory = new SpringXMLNodeFactory();
	
	public SpringConfigFilesIterator(IPackageFragmentRoot contextsFragmentRoot) {
		IContainer sourceFolder = (IContainer) contextsFragmentRoot.getResource();
		Set<IFile> xmlFiles = EclipseUtil.getFiles(sourceFolder, Collections.singleton("xml"));
		candidates = xmlFiles.iterator();
		findNext();
	}

	private void findNext() {
		next = null;
		while(next == null && candidates.hasNext()) {
			IFile candidate = candidates.next();
			SpringConfigFile config = parse(candidate);
			if(config != null && config.getBeans() != null) {
				next = new Pair<IFile, SpringConfigFile>(candidate, config);
			}
		}
	}

	private SpringConfigFile parse(IFile candidate) {
		SpringConfigFile result = null;
		try {
			result = (SpringConfigFile) EclipseUtil.readXMLDocument(candidate, factory);
		} catch (CoreException e) {
			e.printStackTrace();
		}
		if(result != null && !isConfigurationFileDocument(result)) {
			result = null;
		}
		return result;
	}

	/**
	 * Checks that xml is rooted by a beans element with corresponding namespaces
	 * @param document
	 * @return
	 */
	private boolean isConfigurationFileDocument(Document document) {
		if(document.getChildCount() != 1) {
			return false;
		}
		Node rootNode = document.getChild(0);
		if(!(rootNode instanceof Element)) {
			return false;
		}
		Element root = (Element) rootNode;
		if(!root.getLocalName().equals("beans") ||
			!root.getNamespaceURI().equals("http://www.springframework.org/schema/beans")) {
			return false;
		}
		return true;
	}

	@Override
	public boolean hasNext() {
		return next != null;
	}

	@Override
	public Pair<IFile, SpringConfigFile> next() {
		Pair<IFile, SpringConfigFile> next = this.next;
		findNext();
		return next;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

}
