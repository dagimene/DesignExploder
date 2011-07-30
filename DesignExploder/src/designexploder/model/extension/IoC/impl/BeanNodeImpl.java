package designexploder.model.extension.IoC.impl;

import java.util.HashSet;
import java.util.Set;

import designexploder.model.extension.IoC.BeanNode;
import designexploder.model.extension.IoC.Dependency;
import designexploder.model.extension.IoC.IoCAwareMethod;
import designexploder.model.extension.IoC.IoCModelEventTypes;
import designexploder.model.extension.common.impl.NamedNaturalizedImpl;

class BeanNodeImpl extends NamedNaturalizedImpl implements BeanNode {

	private Set<IoCAwareMethod> IoCAwareMethods = new HashSet<IoCAwareMethod>();
	private Set<Dependency> dependencies = new HashSet<Dependency>();

	@Override
	public Set<IoCAwareMethod> getIoCAwareMethods() {
		return IoCAwareMethods;
	}

	@Override
	public Set<Dependency> getDependencies() {
		return dependencies;
	}

	@Override
	public void addIoCAwareMethod (IoCAwareMethod method) {
		IoCAwareMethods.add(method);
		fireModelCollectionAlterEvent(IoCModelEventTypes.IOC_AWARE_METHOD_ADDED, IoCAwareMethods, method);
	}

	@Override
	public void addDependency(Dependency dependency) {
		dependencies.add(dependency);
		fireModelCollectionAlterEvent(IoCModelEventTypes.DEPENDENCY_ADDED, dependencies, dependency);
	}

	@Override
	public void removeDependency(Dependency dependency) {
		dependencies.remove(dependency);
		fireModelCollectionAlterEvent(IoCModelEventTypes.DEPENDENCY_REMOVED, dependencies, dependency);
	}
	
	@Override
	public void removeIoCAwareMethod(IoCAwareMethod method) {
		IoCAwareMethods.remove(method);
		fireModelCollectionAlterEvent(IoCModelEventTypes.IOC_AWARE_METHOD_REMOVED, IoCAwareMethods, method);
	}

}
