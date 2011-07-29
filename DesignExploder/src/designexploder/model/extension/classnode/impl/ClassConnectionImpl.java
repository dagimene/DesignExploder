package designexploder.model.extension.classnode.impl;

import designexploder.model.extension.classnode.ClassConnection;
import designexploder.model.extension.common.Nature;
import designexploder.model.impl.ExtendedModelEventTrigger;

class ClassConnectionImpl extends ExtendedModelEventTrigger implements ClassConnection {

	private Nature nature;
	private String name;
	private int originCardinality;
	private int targetCardinality;
	
	public Nature getNature() {
		return nature;
	}
	
	public void setNature(Nature nature) {
		this.nature = nature;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getSourceCardinality() {
		return originCardinality;
	}
	
	public void setSourceCardinality(int originCardinality) {
		this.originCardinality = originCardinality;
	}
	
	public int getTargetCardinality() {
		return targetCardinality;
	}
	
	public void setTargetCardinality(int targetCardinality) {
		this.targetCardinality = targetCardinality;
	}
}
