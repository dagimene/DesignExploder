package designexploder.editor.controllers;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;

import designexploder.editor.graphics.ClassFigure;
import designexploder.model.Naturalized;
import designexploder.model.classnode.Attribute;
import designexploder.model.classnode.ClassNode;
import designexploder.model.classnode.ClassSection;
import designexploder.model.classnode.DexConstant;
import designexploder.model.classnode.Method;
import designexploder.model.classnode.Modifiable;
import designexploder.util.Pair;

public class ClassNodeEditPart extends NodeEditPart {

	@Override
	protected void refreshVisuals() {
		ClassNode model = (ClassNode)getModel();
		ClassFigure figure = (ClassFigure)getFigure();
		
		updateHeader(figure, model);
		
		List<ClassSection> sections = model.getSections();
		for (ClassSection section : sections) {
			if(section.getNature().equals(DexConstant.ATTRIBUTE)) {
				figure.getAttributesCompartment().setLabels(createLabelsFor(section));
			} else if(section.getNature().equals(DexConstant.METHOD)) {
				figure.getMethodsCompartment().setLabels(createLabelsFor(section));
			}
		}
		super.refreshVisuals();
	}

	private List<Pair<String, DexConstant>> createLabelsFor(ClassSection section) {
		List<? extends Naturalized> members = section.getMembers();
		List<Pair<String, DexConstant>> labels = new ArrayList<Pair<String, DexConstant>>(members.size());
		for (Naturalized member : members) {
			if(section.getNature().equals(DexConstant.ATTRIBUTE)) {
				Attribute attribute = (Attribute)member;
				labels.add(new Pair<String, DexConstant>(getSymbolFor(attribute.getModifiers()) + " " + attribute.getName() + " : " + attribute.getType().getFirstname(), attribute.getNature()));
			} else {
				Method method = (Method)member;
				labels.add(new Pair<String, DexConstant>(getSymbolFor(method.getModifiers()) + " " + method.getName() + getParams(method.getParameters()) + " : " + method.getType().getFirstname(), method.getNature()));
			}
		}
		return labels;
	}

	private String getParams(List<Modifiable> parameters) {
		StringBuilder builder = new StringBuilder("(");
		if(!parameters.isEmpty()) {
			Modifiable modifiable = parameters.get(0);
			builder.append(modifiable.getName());
			builder.append(" : ");
			builder.append(modifiable.getType().getFirstname());
			for (int i = 1; i < parameters.size(); i++) {
				modifiable = parameters.get(i);
				builder.append(", ");
				builder.append(modifiable.getName());
				builder.append(" : ");
				builder.append(modifiable.getType().getFirstname());
			}
		}
		builder.append(")");
		return builder.toString();
	}

	private String getSymbolFor(List<DexConstant> modifiers) {
		return modifiers.contains(DexConstant.PUBLIC) ? "+" :
			(modifiers.contains(DexConstant.PRIVATE) ? "-" : "#");
	}

	private void updateHeader(ClassFigure figure, ClassNode header) {
		figure.setNature(header.getNature());
		figure.setLabel(header.getName());
	}

	protected void createEditPolicies() {
		/*installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, new SelectionEditPolicy() {
			@Override
			protected void showSelection() {
				ClassBox figure = (ClassBox)getFigure();
				figure.setSelected(true);
			}
			
			@Override
			protected void hideSelection() {
				ClassBox figure = (ClassBox)getFigure();
				figure.setSelected(false);
			}
		});*/
		installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, new NonResizableEditPolicy() {
			
			/*@Override
			protected Command getMoveCommand(ChangeBoundsRequest request) {
				new MCommand
				figure.setLocation(request.get)
				return super.getMoveCommand(request);
			}*/
			
			@Override
			protected void showSelection() {
				super.showSelection();
				ClassFigure figure = (ClassFigure)getFigure();
				figure.setSelected(true);
			}
			
			@Override
			protected void hideSelection() {
				super.showSelection();
				ClassFigure figure = (ClassFigure)getFigure();
				figure.setSelected(false);
			}
		});
	}
}
