package designexploder.model.classnode;

import java.util.List;

public interface ClassSection {

	SectionType getType();

	void setType(SectionType type);
	
	void setMembers(List<Member> members);
	
	List<Member> getMembers();
	
}
