package designexploder.model.classnode;

import java.util.List;

import designexploder.model.Naturalized;
import designexploder.model.Node;

public interface ClassNode extends Node, Naturalized, Modifiable, Type {
	
	List<ClassSection> getSections();
	
	void setSections(List<ClassSection> sections);

}