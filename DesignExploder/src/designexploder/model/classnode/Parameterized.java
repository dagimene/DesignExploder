package designexploder.model.classnode;

import java.util.List;

public interface Parameterized extends Modifiable {

	List<Modifiable> getParameters();
	
	void setParameters(List<Modifiable> parameters);

}
