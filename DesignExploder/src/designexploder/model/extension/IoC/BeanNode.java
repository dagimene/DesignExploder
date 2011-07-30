package designexploder.model.extension.IoC;

import java.util.Set;

import designexploder.model.extension.common.Named;
import designexploder.model.extension.common.Naturalized;

public interface BeanNode extends Named, Naturalized {

	Set<Dependency> getDependencies();
	
	void addDependency(Dependency dependency);

	void removeDependency(Dependency dependency);
	
	Set<IoCAwareMethod> getIoCAwareMethods();

	void addIoCAwareMethod(IoCAwareMethod method);

	void removeIoCAwareMethod(IoCAwareMethod method);

}
