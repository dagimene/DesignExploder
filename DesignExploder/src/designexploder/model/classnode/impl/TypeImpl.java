package designexploder.model.classnode.impl;

import designexploder.model.classnode.Type;

public class TypeImpl implements Type {
	
	private String firstname;
	private String lastname;
	private boolean basic;

	public TypeImpl(String name) {
		setName(name);
	}
	
	public String getName() {
		return basic ? this.firstname : this.lastname + "." + this.firstname;
	}
	
	public void setName(String name) {
		int dot = name.lastIndexOf('.');
		this.basic = (dot == -1);
		this.lastname = basic ? null : name.substring(0, dot);
		this.firstname = basic ? name : name.substring(dot + 1);
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public boolean isBasic() {
		return basic;
	}
}
