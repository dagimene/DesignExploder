package designexploder.model.extension.classnode;

public interface Attribute extends ClassItem {
	
	Method getGetter();
	
	Method getSetter();
	
	void setGetter(Method getter);
	
	void setSetter(Method setter);
	
	boolean isEnumConstant();

}
