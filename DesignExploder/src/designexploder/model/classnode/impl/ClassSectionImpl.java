package designexploder.model.classnode.impl;

import java.util.Collections;
import java.util.List;

import designexploder.model.classnode.ClassSection;
import designexploder.model.classnode.Member;
import designexploder.model.classnode.SectionType;

public class ClassSectionImpl implements ClassSection {
	
	private List<Member> members = Collections.emptyList();
	private SectionType type;

	public List<Member> getMembers() {
		return members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}

	public SectionType getType() {
		return type;
	}

	public void setType(SectionType type) {
		this.type = type;
	}
}
