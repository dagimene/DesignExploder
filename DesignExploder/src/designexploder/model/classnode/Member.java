package designexploder.model.classnode;

import java.util.List;

public interface Member {

	String getName();
	
	void setName(String name);
	
	String getSymbol();
	
	void setSymbol(String symbol);
	
	List<Modifier> getModifiers();
	
	void setModifiers(List<Modifier> modifiers);

	String getType();
	
	void setType(String type);
	
	List<String> getStereotypes();
	
	void setStereotypes(List<String> stereotypes);

}
