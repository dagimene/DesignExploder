package designexploder.model.classnode;

import java.util.List;

import designexploder.model.Node;

public interface ClassNode extends Node {
	
	Member getHeader();
	
	void setHeader(Member header);
	
	List<ClassSection> getSections();
	
	void setSections(List<ClassSection> sections);

}
