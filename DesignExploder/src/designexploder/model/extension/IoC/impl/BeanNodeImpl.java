package designexploder.model.extension.IoC.impl;

import java.util.HashSet;
import java.util.Set;

import designexploder.model.Node;
import designexploder.model.event.ModelEvent;
import designexploder.model.event.ModelEventListener;
import designexploder.model.extension.IoC.BeanNode;
import designexploder.model.extension.IoC.Dependency;
import designexploder.model.extension.IoC.IoCAwareMethod;
import designexploder.model.extension.IoC.IoCModelEventTypes;
import designexploder.model.extension.IoC.IoCModelNatures;
import designexploder.model.extension.IoC.IoCModelUtil;
import designexploder.model.extension.classnode.ClassNode;
import designexploder.model.extension.common.impl.NamedNaturalizedImpl;

class BeanNodeImpl extends NamedNaturalizedImpl implements BeanNode {

	private Set<IoCAwareMethod> IoCAwareMethods = new HashSet<IoCAwareMethod>();
	private Set<Dependency> dependencies = new HashSet<Dependency>();
	private Node node;

	public BeanNodeImpl() {
		addListener(IoCModelEventTypes.IOC_AWARE_METHOD_ADDED, abstractAutoimplementationListener);
		addListener(IoCModelEventTypes.IOC_AWARE_METHOD_REMOVED, abstractAutoimplementationListener);
	}

	@Override
	public Set<IoCAwareMethod> getIoCAwareMethods() {
		return IoCAwareMethods;
	}

	@Override
	public Set<Dependency> getDependencies() {
		return dependencies;
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
	public void addIoCAwareMethod (IoCAwareMethod method) {
		IoCAwareMethods.add(method);
		fireModelCollectionAlterEvent(IoCModelEventTypes.IOC_AWARE_METHOD_ADDED, IoCAwareMethods, method);
	}

	
	@Override
	public void removeIoCAwareMethod(IoCAwareMethod method) {
		IoCAwareMethods.remove(method);
		fireModelCollectionAlterEvent(IoCModelEventTypes.IOC_AWARE_METHOD_REMOVED, IoCAwareMethods, method);
	}

	@Override
	@SuppressWarnings("unchecked")
	public Class<BeanNode> getExtensionClass() {
		return BeanNode.class;
	}

	@Override
	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}

	private final ModelEventListener abstractAutoimplementationListener = new ModelEventListener() {
		@Override
		public void processModelEvent(ModelEvent e) {
			if(getNature() != IoCModelNatures.BEAN_FACADE) {
                if(node != null) {
                    IoCModelNatures newNature = IoCModelUtil.getBeanNatureFor(node.getExtension(ClassNode.class), BeanNodeImpl.this);
                    if(getNature() !=  newNature) {
                        setNature(newNature);
                    }
                }
			}
		}
	};

}
