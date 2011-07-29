package designexploder.model.extension.IoC;

import designexploder.model.extension.common.Nature;

public enum IoCModelNatures implements Nature {
	
	// Bean Natures
	COMMON_BEAN("Common Bean"),
	STATELESS_BEAN("Stateless Bean"),
	AUTOIMPLEMENTED_BEAN("Autoimplemented Bean"),
	FACADE_BEAN("Facade Bean"),
	CONTEXT_FACTORY("Context Factory"),

	// IoC Bean Injection Types
	SINGLE("Single"),
	COLLECTION("Collection"),
	PROXY("Proxy"),
	TREE("Tree"),

	// IoC Aware Method
	CONTEXT_INSTANTIATION("Context Instantiation"),
	BEAN_FACTORY_METHOD("Bean Factory Method");
	
	private String name;
	
	IoCModelNatures(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
