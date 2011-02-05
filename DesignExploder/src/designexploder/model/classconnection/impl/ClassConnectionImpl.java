package designexploder.model.classconnection.impl;

import designexploder.model.Node;
import designexploder.model.classconnection.ClassConnection;
import designexploder.model.classnode.DexConstant;
import designexploder.model.impl.ConnectionImpl;

public class ClassConnectionImpl extends ConnectionImpl implements ClassConnection {

	private DexConstant nature;
	private String name;
	private int originCardinality;
	private int targetCardinality;
	
	public ClassConnectionImpl() {
		this(DexConstant.ASSOCIATION);
	}

	public ClassConnectionImpl(DexConstant nature) {
		this.nature = nature;
	}

	public ClassConnectionImpl(DexConstant nature, Node from, Node to) {
		super(from, to);
		this.nature = nature;
	}
	
	public DexConstant getNature() {
		return nature;
	}
	public void setNature(DexConstant nature) {
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
	public void setOriginCardinality(int originCardinality) {
		this.originCardinality = originCardinality;
	}
	public int getTargetCardinality() {
		return targetCardinality;
	}
	public void setTargetCardinality(int targetCardinality) {
		this.targetCardinality = targetCardinality;
	}
}
