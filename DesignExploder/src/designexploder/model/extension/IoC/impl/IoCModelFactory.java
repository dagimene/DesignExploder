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
	
	public IoCAwareMethod createIoCAwareMethod() {
		return new IoCAwareMethodImpl();
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

}
