package designexploder.model.extension.IoC.impl;

import designexploder.model.extension.IoC.*;

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
	
	public Dependency createDependency() {
		return new DependencyImpl();
	}

}
