package designexploder.model.classnode;

import java.util.List;

import designexploder.model.Naturalized;

public interface ClassSection extends Naturalized {

	void setMembers(List<? extends Naturalized> members);
	
	List<? extends Naturalized> getMembers();
	
}
