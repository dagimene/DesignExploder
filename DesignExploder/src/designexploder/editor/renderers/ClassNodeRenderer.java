package designexploder.editor.renderers;

import java.util.ArrayList;
import java.util.List;

import designexploder.editor.graphics.ClassFigure;
import designexploder.model.classnode.Attribute;
import designexploder.model.classnode.ClassNode;
import designexploder.model.classnode.DexConstant;
import designexploder.model.classnode.Method;
import designexploder.model.classnode.Modifiable;
import designexploder.model.classnode.Parameter;
import designexploder.util.Pair;

public class ClassNodeRenderer implements Renderer<ClassNode<?>, ClassFigure> {

	public void render(ClassNode<?> model, ClassFigure figure) {
		updateHeader(figure, model);
		
		figure.getAttributesCompartment().setLabels(createLabelsForAttributes(model.getAttributes()));
		figure.getMethodsCompartment().setLabels(createLabelsForMethods(model.getMethods()));
	}

	private void updateHeader(ClassFigure figure, ClassNode<?> model) {
		figure.setNature(model.getNature());
		figure.setLabel(model.getType().getName());
	}

	private List<Pair<String, DexConstant>> createLabelsForAttributes(List<Attribute> attributes) {
		List<Pair<String, DexConstant>> labels = new ArrayList<Pair<String, DexConstant>>(attributes.size());
		for (Attribute attribute : attributes) {
			labels.add(new Pair<String, DexConstant>(getSymbolFor(attribute) + " " + attribute.getName() + " : " + attribute.getType().getFirstname(), attribute.getNature()));
		}
		return labels;
	}

	private List<Pair<String, DexConstant>> createLabelsForMethods(List<Method> methods) {
		List<Pair<String, DexConstant>> labels = new ArrayList<Pair<String, DexConstant>>(methods.size());
		for(Method method : methods) {
			labels.add(new Pair<String, DexConstant>(getSymbolFor(method) + " " + method.getName() + getParams(method.getParameters()) + " : " + method.getType().getFirstname(), method.getNature()));
		}
		return labels;
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
