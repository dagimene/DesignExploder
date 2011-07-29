package designexploder.model.extension.classnode;

import java.util.List;

public interface Parameterized extends Modifiable {

	List<Parameter> getParameters();
	
	void addParameter(Parameter parameter);

	void removeParameter(Parameter parameter);

}
