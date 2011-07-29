package designexploder.model.extension.IoC.impl.spring;

import java.util.Iterator;

import org.eclipse.core.resources.IFile;
import org.eclipse.jdt.core.IPackageFragmentRoot;

import designexploder.util.Pair;

import nu.xom.Document;

public class ContextsFilesIterator implements Iterator<Pair<IFile, Document>> {

	public ContextsFilesIterator(IPackageFragmentRoot contextsFragmentRoot) {
		
	}

	@Override
	public boolean hasNext() {
		return false;
	}

	@Override
	public Pair<IFile, Document> next() {
		return null;
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub

	}

}
