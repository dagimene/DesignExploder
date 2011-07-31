package designexploder.editor.renderers.extension.classnode;

import java.util.List;

import designexploder.editor.renderers.NodeRendererDecorator;
import designexploder.model.Node;
import designexploder.model.extension.classnode.Attribute;
import designexploder.model.extension.classnode.ClassItem;
import designexploder.model.extension.classnode.ClassNode;
import designexploder.model.extension.classnode.Method;
import designexploder.model.extension.classnode.Modifiable;
import designexploder.model.extension.classnode.Parameter;
import designexploder.model.extension.common.Nature;

public class ClassNodeDecorator implements NodeRendererDecorator {

	public String getNodeLabel(Node node) {
		ClassNode classNode = node.getExtension(ClassNode.class);
		return classNode.getType().getName();		
	}

	public Nature getNodeNature(Node node) {
		ClassNode classNode = node.getExtension(ClassNode.class);
		return classNode.getNature();
	}

	public String getItemLabel(int index, Node node, ClassItem item) {
		if(item.isAttribute()) {
			return createLabelForAttribute((Attribute) item);
		} else {
			return createLabelForMethod((Method) item);
		}
	}

	public Nature getItemNature(int index, Node node, ClassItem item) {
		return item.getNature();
	}

	public List<Nature> getItemIcons(int index, Node node, ClassItem item, List<Nature> natures) {
		natures.add(item.getNature());
		return natures;
	}
	
	private String createLabelForAttribute(Attribute attribute) {
		return getSymbolFor(attribute) + " " + attribute.getName() + " : " + attribute.getType().getFirstname();
	}

	private String createLabelForMethod(Method method) {
		return getSymbolFor(method) + " " + method.getName() + getParams(method.getParameters()) + " : " + method.getType().getFirstname();
	}
	
	private String getSymbolFor(Modifiable modifiable) {
		return modifiable.isPublic() ? "+" :
			(modifiable.isPrivate() ? "-" : "#");
	}

	private String getParams(List<Parameter> parameters) {
		StringBuilder builder = new StringBuilder("(");
		if(!parameters.isEmpty()) {
			Parameter parameter = parameters.get(0);
			builder.append(parameter.getName());
			builder.append(" : ");
			builder.append(parameter.getType().getFirstname());
			for (int i = 1; i < parameters.size(); i++) {
				parameter = parameters.get(i);
				builder.append(", ");
				builder.append(parameter.getName());
				builder.append(" : ");
				builder.append(parameter.getType().getFirstname());
			}
		}
		builder.append(")");
		return builder.toString();
	}	
}
