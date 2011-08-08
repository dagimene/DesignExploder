package designexploder.editor.renderers.extension.classnode;

import java.util.List;

import designexploder.model.Node;
import designexploder.model.extension.classnode.Attribute;
import designexploder.model.extension.classnode.ClassItem;
import designexploder.model.extension.classnode.Method;
import designexploder.model.extension.classnode.Parameter;
import designexploder.model.extension.common.Nature;
import designexploder.resources.ClassItemIconProvider;
import designexploder.resources.IconResource;

public class ClassItemDecoratorImpl implements ClassItemDecorator {

	@Override
	public String getItemLabel(ClassItem item, Node node) {
		if(item.isAttribute()) {
			return createLabelForAttribute((Attribute) item);
		} else {
			return createLabelForMethod((Method) item);
		}
	}

	@Override
	public Nature getItemNature(ClassItem item, Node node) {
		return item.getNature();
	}

	@Override
	public List<IconResource> getItemIcons(ClassItem item, Node node, List<IconResource> natures) {
		natures.add(ClassItemIconProvider.getItemIconResource(item, false));
		if(item.isAttribute()) {
			Method setter = ((Attribute) item).getSetter();
			Method getter = ((Attribute) item).getGetter();
			natures.addAll(ClassItemIconProvider.getAccesorsIconResources(setter, getter));
		}
		return natures;
	}

	private String createLabelForAttribute(Attribute attribute) {
		return attribute.getName() + " : " + attribute.getType().getFirstname();
	}

	private String createLabelForMethod(Method method) {
		return method.getName() + getParams(method.getParameters()) + " : " + method.getType().getFirstname();
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
