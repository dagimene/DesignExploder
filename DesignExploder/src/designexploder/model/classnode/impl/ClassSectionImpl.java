package designexploder.model.classnode.impl;

import java.util.ArrayList;
import java.util.List;

import designexploder.model.Naturalized;
import designexploder.model.classnode.ClassSection;
import designexploder.model.classnode.DexConstant;

public class ClassSectionImpl implements ClassSection {
	
	private List<? extends Naturalized> members;
	private DexConstant nature;
	
	public ClassSectionImpl() {
		members = new ArrayList<Naturalized>(10);
	}

	public List<? extends Naturalized> getMembers() {
		return members;
	}

	public void setMembers(List<? extends Naturalized> members) {
		this.members = members;
	}

	public DexConstant getNature() {
		return nature;
	}

	public void setNature(DexConstant nature) {
		this.nature = nature;
	}
}
