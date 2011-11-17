package designexploder.model.extension.IoC;

import designexploder.model.extension.common.Nature;

public enum IoCModelNatures implements Nature {
	
	// Bean Natures
	BEAN("Bean"),
	BEAN_STATELESS("Stateless Bean"),
	BEAN_AUTO("Autoimplemented Bean"),
	BEAN_FACADE("Facade Bean"),
	BEAN_FACTORY("Context Factory Bean"),

	// Dependency Natures
	UNRESOLVED_DEPENDENCY("Dependency"),
	INJECTION_BEAN("Bean Dependency"),
	INJECTION_COLLECTION("Collecion Dependency"),
	INJECTION_TREE("Tree Dependency"),
	INJECTION_PROXY("Proxy Dependency"),

	// IoCAwareMethod natures
	IOC_METHOD_FACTORY("Factory Method"),
	IOC_METHOD_INIT("Init Method"),
	IOC_METHOD_INSTANTIATE("Instantiate Context Method"),
    IOC_METHOD_DESTROY("Destroy Context Method"),
    IOC_METHOD_ACTIVATE("Activate Context Method"),

	// Misc
	INDIFFERENT_CONNECTION("Indifferent Connection");

	private String name;

    IoCModelNatures(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
