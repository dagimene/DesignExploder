package designexploder.editor.renderers;

import java.util.ArrayList;
import java.util.List;

import designexploder.editor.graphics.ClassFigure;
import designexploder.editor.graphics.SectionFigure;
import designexploder.model.Node;
import designexploder.model.extension.classnode.Attribute;
import designexploder.model.extension.classnode.ClassNode;
import designexploder.model.extension.classnode.InmutableNamed;
import designexploder.model.extension.classnode.Method;
import designexploder.model.extension.classnode.Modifiable;
import designexploder.model.extension.classnode.Parameter;
import designexploder.model.extension.common.InmutableNaturalized;
import designexploder.model.extension.common.Named;
import designexploder.model.extension.common.Nature;

public class ClassFigureRenderer implements Renderer<Node, ClassFigure> {

	public void render(Node node, ClassFigure figure) {
		updateHeader(figure, node);
		ClassNode classNode = node.getExtension(ClassNode.class);
		updateCompartment(figure.getAttributesCompartment(), classNode.getAttributes(), Attribute.class);
		updateCompartment(figure.getMethodsCompartment(), classNode.getMethods(), Method.class);
	}

	private void updateHeader(ClassFigure figure, Node node) {
		figure.setNature(getNodeNature(node));
		figure.setLabel(getNodeLabel(node));
	}
	
	private <T> void updateCompartment(SectionFigure compartment, List<T> rows, Class<T> clazz) {
		int count = rows.size();
		compartment.setLabelsQuantity(count);
		for(int index = 0; index < count; index++) {
			T row = rows.get(index);
			compartment.setLabelContent(index, getRowLabel(index, row, clazz), getRowNature(index, row, clazz), getRowIcons(index, row, clazz));
		}
	}
	
	public <T> String getRowLabel(int index, T row, Class<T> clazz) {
		if(Attribute.class.isAssignableFrom(clazz)) {
			return createLabelForAttribute((Attribute) row);
		} else if(Method.class.isAssignableFrom(clazz)) {
			return createLabelForMethod((Method) row);
		} else if(row instanceof InmutableNamed) {
			return ((Named) row).getName();
		}
		return "";
	}

	public <T> Nature getRowNature(int index, T row, Class<T> clazz) {
		if(row instanceof InmutableNaturalized) {
			return ((InmutableNaturalized) row).getNature();
		}
		return Nature.NONE;
	}

	public <T> List<Nature> getRowIcons(int index, T row, Class<T> clazz) {
		List<Nature> natures = new ArrayList<Nature>();
		if(row instanceof InmutableNaturalized) {
			natures.add(((InmutableNaturalized) row).getNature());
		}
		return natures;
	}
	
	public String getNodeLabel(Node node) {
		ClassNode classNode = node.getExtension(ClassNode.class);
		return classNode.getType().getName();		
	}

	public Nature getNodeNature(Node node) {
		ClassNode classNode = node.getExtension(ClassNode.class);
		return classNode.getNature();
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