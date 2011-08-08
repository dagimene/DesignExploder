package designexploder.model.extension.classnode.build;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import designexploder.model.Node;
import designexploder.model.NodeContainer;
import designexploder.model.build.ModelBuilder;
import designexploder.model.extension.classnode.Attribute;
import designexploder.model.extension.classnode.ClassNode;
import designexploder.model.extension.classnode.Method;

public class ClassMembersCondensatorBuilder implements ModelBuilder {

	@Override
	public NodeContainer build(NodeContainer diagram) {
		for (Node node : diagram.getNodes()) {
			condensateMembers(node.getExtension(ClassNode.class));
		}
		return diagram;
	}

	private void condensateMembers(ClassNode classNode) {
		Map<String, Attribute> attributes = new HashMap<String, Attribute>();
		for (Attribute attribute : classNode.getAttributes()) {
			attributes.put(attribute.getName(), attribute);
		}
		Set<Method> matched = new HashSet<Method>();
		for (Method method : classNode.getMethods()) {
			String property = method.getProperty();
			if(property != null) {
				Attribute attribute = attributes.get(property);
				if(attribute != null) {
					if(method.isGetter() && method.getType() == attribute.getType()) {
						attribute.setGetter(method);
						matched.add(method);
					} else if(method.isSetter() && method.getParameters().get(0).getType() == attribute.getType()) {
						attribute.setSetter(method);
						matched.add(method);
					}
				}
			}
		}
		for (Method method : matched) {
			classNode.removeMethod(method);
		}
	}
	
}
