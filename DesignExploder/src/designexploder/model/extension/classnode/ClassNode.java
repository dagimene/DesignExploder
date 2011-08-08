package designexploder.model.extension.classnode;

import java.util.List;

import designexploder.model.ModelExtension;
import designexploder.model.extension.common.Naturalized;

public interface ClassNode extends Naturalized, Modifiable, ModelExtension {
	
	List<Method> getMethods();
	
	List<Attribute> getAttributes();
	
	void addMethod(Method method);

	void removeMethod(Method method);
	
	void addAttribute(Attribute attribute);

	void removeAttribute(Attribute attribute);
	
}