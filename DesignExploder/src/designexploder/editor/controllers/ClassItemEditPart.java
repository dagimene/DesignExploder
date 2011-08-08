package designexploder.editor.controllers;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editpolicies.SelectionEditPolicy;

import designexploder.editor.controllers.listeners.RefreshableEditPart;
import designexploder.editor.graphics.ClassItemFigure;
import designexploder.editor.renderers.extension.IoC.ClassItemIoCDecorator;
import designexploder.editor.renderers.extension.classnode.ClassItemBaseRenderer;
import designexploder.editor.renderers.extension.classnode.ClassItemDecoratorImpl;
import designexploder.model.Node;
import designexploder.model.extension.classnode.ClassItem;

public class ClassItemEditPart extends AbstractGraphicalEditPart implements RefreshableEditPart {
	
	private ClassItemBaseRenderer renderer = new ClassItemBaseRenderer();
	
	public ClassItemEditPart() {
		renderer.addDecorator(new ClassItemIoCDecorator());
		renderer.addDecorator(new ClassItemDecoratorImpl());
	}

	@Override
	protected IFigure createFigure() {
		return new ClassItemFigure();
	}

	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE, new SelectionEditPolicy() {
			@Override
			protected void showSelection() {
				getFigure().setSelected(true);
			}
			
			@Override
			protected void hideSelection() {
				getFigure().setSelected(false);
			}
		});
	}

	@Override
	public void refreshVisuals() {
		renderer.render(getModel(), getParentModel(), getFigure());
	}
	
	public Node getParentModel() {
		return (Node) getParent().getModel();
	}
	
	@Override
	public ClassItem getModel() {
		return (ClassItem) super.getModel();
	}
	
	@Override
	public ClassItemFigure getFigure() {
		return (ClassItemFigure) super.getFigure();
	}

	@Override
	public void refreshChildren() {
		super.refreshChildren();
	}

	@Override
	public void refreshSourceConnections() {
		super.refreshSourceConnections();
	}

	@Override
	public void refreshTargetConnections() {
		super.refreshTargetConnections();
	}
}
