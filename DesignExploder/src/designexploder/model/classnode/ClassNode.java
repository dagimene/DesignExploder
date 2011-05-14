package designexploder.model.classnode;

import java.util.List;

import designexploder.model.Connection;
import designexploder.model.Node;

public interface ClassNode<C extends Connection> extends Node<C>, Naturalized, Modifiable {
	
	List<Method> getMethods();
	
	List<Attribute> getAttributes();
	
	void addMethod(Method method);

	void removeMethod(Method method);
	
	void addAttribute(Attribute attribute);

	void removeAttribute(Attribute attribute);
	
}