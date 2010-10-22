package designexploder.model.impl;

import designexploder.model.Named;

public class NamedImpl implements Named {
	public String name;
	
	public NamedImpl(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}