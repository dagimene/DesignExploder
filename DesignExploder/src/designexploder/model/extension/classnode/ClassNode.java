package designexploder.model.extension.classnode;

import java.util.List;

import designexploder.model.extension.common.Naturalized;

public interface ClassNode extends Naturalized, Modifiable {
	
	List<Method> getMethods();
	
	List<Attribute> getAttributes();
	
	void addMethod(Method method);

	void removeMethod(Method method);
	
	void addAttribute(Attribute attribute);

	void removeAttribute(Attribute attribute);
	
}