package designexploder.editor.controllers;

import designexploder.editor.graphics.ClassFigure;
import designexploder.editor.renderers.ClassNodeRenderer;
import designexploder.model.classnode.ClassNode;

public class ClassNodeEditPart extends NodeEditPart {

	private ClassNodeRenderer renderer = new ClassNodeRenderer();
	
	@Override
	protected void refreshVisuals() {
		renderer.render(getModel(), getFigure());
		super.refreshVisuals();
	}

	protected void createEditPolicies() {
		//installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, new ClassSelectionPolicy());
	}

	@Override
	public ClassFigure getFigure() {
		return (ClassFigure) super.getFigure();
	}

	@Override
	public ClassNode<?> getModel() {
		return (ClassNode<?>) super.getModel();
	}
}
