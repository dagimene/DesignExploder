package designexploder.editor.controllers.policies;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editpolicies.ResizableEditPolicy;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;

import designexploder.editor.controllers.ClassNodeEditPart;
import designexploder.editor.controllers.ContainerNodeEditPart;
import designexploder.editor.controllers.commands.AddNodeCommand;
import designexploder.editor.controllers.commands.MoveAndResizeNodeCommand;
import designexploder.editor.controllers.commands.MoveNodeCommand;
import designexploder.editor.controllers.commands.ReparentNodeCommand;
import designexploder.model.Node;
import designexploder.model.NodeContainer;

public class NodeContainerLayoutEditPolicy extends XYLayoutEditPolicy {

	protected EditPolicy createChildEditPolicy(EditPart child) {
		EditPolicy result;
		if(((Node)child.getModel()).isResizeable()) {
			result = new ResizableEditPolicy();
		} else {
			result = new ClassSelectionPolicy();
		}
		return result;
	}

	/**
	 * Creates a move or move and resize node command.
	 * @param child a ClassNodeEditPart
	 * @param constraint a Rectangle
	 */
	@Override
	protected Command createChangeConstraintCommand(EditPart child,
			Object constraint) {
		return createChangeConstraintCommand(child, (Rectangle) constraint);
	}
	
	private Command createChangeConstraintCommand(EditPart child,
			Rectangle constraint) {
		Command result = null;
		if(child instanceof ContainerNodeEditPart) {
			result = new MoveAndResizeNodeCommand(((ContainerNodeEditPart)child).getModel(), constraint.getLocation(), constraint.getSize());
		} else if(child instanceof ClassNodeEditPart) {
			result = createMoveCommand(child, constraint);
		}
		return result;
	}
	
	private Command createMoveCommand(EditPart child, Rectangle constraint) {
		return new MoveNodeCommand((Node) child.getModel(), constraint.getLocation());
	}

	@Override
	protected Command getCreateCommand(CreateRequest request) {
		Node newModel = (Node) request.getNewObject();
		newModel.setNodeContainer((NodeContainer) getHost().getModel());
		Point location = request.getLocation();
		Dimension size = request.getSize();
		if(size == null) {
			size = new Dimension(15, 15);
			location.translate(-15, -15);
		}
		newModel.setLocation(location);
		newModel.setSize(size);
		return new AddNodeCommand(newModel);
	}

	@Override
	protected Command createAddCommand(EditPart child, Object constraint) {
		return createAddCommand(child, (Rectangle)constraint);
	}
	
	private Command createAddCommand(EditPart child, Rectangle constraint) {
		NodeContainer newContainer = (NodeContainer) getHost().getModel();
		CompoundCommand result = new CompoundCommand();
		result.add(new ReparentNodeCommand((Node) child.getModel(), newContainer));
		result.add(createMoveCommand(child, constraint));
		return result;
	}
}