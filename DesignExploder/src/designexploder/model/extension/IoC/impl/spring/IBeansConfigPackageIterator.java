package designexploder.model.extension.IoC.impl.spring;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.springframework.ide.eclipse.beans.core.model.IBeansConfig;
import org.springframework.ide.eclipse.beans.core.model.IBeansProject;

import designexploder.util.EclipseUtil;

public class IBeansConfigPackageIterator implements Iterator<IBeansConfig> {

	private final IBeansProject project;
	private final Iterator<IFile> configFiles;
	private Queue<IBeansConfig> configs = new ArrayDeque<IBeansConfig>(); 
	private IBeansConfig next;

	public IBeansConfigPackageIterator(IBeansProject project, IPackageFragmentRoot iocRoot) {
		this.project = project;
		this.configFiles = EclipseUtil.getFiles((IContainer)iocRoot.getResource(), project.getConfigSuffixes()).iterator();
		findNext();
	}

	@Override
	public boolean hasNext() {
		return next != null;
	}

	@Override
	public IBeansConfig next() {
		IBeansConfig temp = next;
		findNext();
		return temp;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	public void findNext() {
		/* Convert to BeansConfigs */
		while(configs.isEmpty() && configFiles.hasNext()) {
			configs.addAll(project.getConfigs(configFiles.next(), true));
		}
		
		next = configs.isEmpty() ? null : configs.poll();
	}
	
}