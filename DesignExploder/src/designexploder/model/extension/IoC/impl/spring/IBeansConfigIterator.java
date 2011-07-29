package designexploder.model.extension.IoC.impl.spring;

import java.util.Iterator;

import org.springframework.ide.eclipse.beans.core.model.IBeansConfig;
import org.springframework.ide.eclipse.beans.core.model.IBeansProject;

public class IBeansConfigIterator implements Iterator<IBeansConfig> {

	private Iterator<IBeansConfig> configs; 

	public IBeansConfigIterator(IBeansProject project) {
		this.configs = project.getConfigs().iterator();
	}

	@Override
	public boolean hasNext() {
		return configs.hasNext();
	}

	@Override
	public IBeansConfig next() {
		return configs.next();
	}

	@Override
	public void remove() {
		configs.remove();
	}
	
}
