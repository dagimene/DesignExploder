package designexploder.model.extension.IoC.impl;

import designexploder.model.extension.IoC.*;
import designexploder.model.extension.classnode.ClassItem;

public class IoCModelFactory {
	
	private final static IoCModelFactory instance = new IoCModelFactory();

	public static IoCModelFactory getInstance() {
		return instance;
	}
	
	private IoCModelFactory() {}
	
	public ApplicationContext createApplicationContext() {
		return new ApplicationContextImpl();
	}

	public IoCAwareMethod createIoCAwareMethod(IoCModelNatures nature) {
        IoCAwareMethodImpl result;
        if (nature == IoCModelNatures.IOC_METHOD_FACTORY) {
            result = new TargetedIoCAwareMethodImpl();
            result.setNature(IoCModelNatures.IOC_METHOD_FACTORY_UNRESOLVED);
        } else if(nature == IoCModelNatures.IOC_METHOD_INSTANTIATE) {
            result = new TargetedIoCAwareMethodImpl();
            result.setNature(IoCModelNatures.IOC_METHOD_INSTANTIATE_UNRESOLVED);
        } else {
            result = new IoCAwareMethodImpl();
            result.setNature(nature);
        }

        return result;
    }

	public BeanNode createBeanNode() {
		return new BeanNodeImpl();
	}
	
	public BeanInjection createBeanInjection() {
		return new BeanInjectionImpl();
	}
	
	public Dependency createDependency(ClassItem target) {
		DependencyImpl dependency = new DependencyImpl();
		dependency.setNature(IoCModelNatures.UNRESOLVED_DEPENDENCY);
		dependency.setTarget(target);
		return dependency;
	}

    public IoCInstantiation createBeanInstantiation() {
        return new IoCInstantiationImpl();
    }
}
