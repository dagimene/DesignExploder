package designexploder.editor.controllers;

import designexploder.editor.controllers.policies.ClassItemRequestsEditPolicy;
import designexploder.editor.controllers.policies.ClassItemSelectionEditPolicy;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

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
		installEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE, new ClassItemSelectionEditPolicy());
        installEditPolicy(EditPolicy.NODE_ROLE, new ClassItemRequestsEditPolicy());
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

    /**
     * Redirect Requests to node's edit policies.
     */
    @Override
    public void performRequest(Request request) {
        Command command = null;
        EditPolicyIterator i = getEditPolicyIterator();
        while (i.hasNext()) {
            if (command != null)
                command = command.chain(i.next().getCommand(request));
            else
                command = i.next().getCommand(request);
        }
        getViewer().getEditDomain().getCommandStack().execute(command);
    }

}
