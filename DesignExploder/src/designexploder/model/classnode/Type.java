package designexploder.model.classnode;

import designexploder.model.Named;

public interface Type extends Named {

	String getFirstname();

	void setFirstname(String firstname);

	String getLastname();

	void setLastname(String lastname);
	
	boolean isBasic();
	
}