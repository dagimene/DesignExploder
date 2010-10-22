package designexploder.model.classnode.impl;

import java.util.List;

import designexploder.model.classnode.ClassNode;
import designexploder.model.classnode.ClassSection;
import designexploder.model.classnode.Member;
import designexploder.model.impl.NodeImpl;

public class ClassNodeImpl extends NodeImpl implements ClassNode{

	private Member header;
	
	private List<ClassSection> sections;

	public Member getHeader() {
		return header;
	}

	public void setHeader(Member header) {
		this.header = header;
	}

	public List<ClassSection> getSections() {
		return sections;
	}

	public void setSections(List<ClassSection> sections) {
		this.sections = sections;
	}
	
}
