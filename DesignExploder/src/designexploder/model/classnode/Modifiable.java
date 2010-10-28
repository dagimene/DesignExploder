package designexploder.model.classnode;

import java.util.List;

public interface Modifiable extends Typed {

	List<DexConstant> getModifiers();
	
	void setModifiers(List<DexConstant> modifiers);
	
}
