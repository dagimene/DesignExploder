package designexploder.editor.controllers.policies;

import java.util.List;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editpolicies.ResizableEditPolicy;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;

import designexploder.editor.controllers.ClassNodeEditPart;
import designexploder.editor.controllers.ContainerNodeEditPart;
import designexploder.editor.controllers.commands.AddNodeCommand;
import designexploder.editor.controllers.commands.MoveAndResizeNodeCommand;
import designexploder.editor.controllers.commands.MoveNodeCommand;
import designexploder.editor.controllers.commands.ReparentNodeCommand;
import designexploder.model.BasicModelUtil;
import designexploder.model.Node;
import designexploder.model.NodeContainer;
import designexploder.model.extension.IoC.BeanNode;
import designexploder.model.extension.classnode.ClassNode;
import designexploder.model.impl.BasicModelFactory;

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

	private Command createCloneCommand(EditPart child, Rectangle contraint) {
		AddNodeCommand result = null;
		NodeContainer container = (NodeContainer) getHost().getModel();
		Node node = (Node) child.getModel();
		if(node.getExtension(ClassNode.class) != null) {
			String newId = BasicModelUtil.nextClassIdLike(node);
			Node newNode = BasicModelFactory.getInstance().createModelCopy(node, newId, true);
			newNode.setNodeContainer(container);
			newNode.setLocation(contraint.getLocation());
			if(newNode.getExtension(BeanNode.class) != null) {
				newNode.removeExtension(BeanNode.class);
			}
			result = new AddNodeCommand(newNode);
		}
		return result; 
	}

	private Command createAddCommand(EditPart child, Rectangle constraint) {
		NodeContainer newContainer = (NodeContainer) getHost().getModel();
		CompoundCommand result = new CompoundCommand();
		result.add(new ReparentNodeCommand((Node) child.getModel(), newContainer));
		result.add(createMoveCommand(child, constraint));
		return result;
	}

	@Override
	protected Command getCreateCommand(CreateRequest request) {
		Node newModel = (Node) request.getNewObject();
		newModel.setNodeContainer((NodeContainer) getHost().getModel());
		setNodeBounds(newModel, request.getLocation(), request.getSize());
		return new AddNodeCommand(newModel);
	}

	private void setNodeBounds(Node node, Point location, Dimension size) {
		if(size == null) {
			size = new Dimension(15, 15);
			location.translate(-15, -15);
		}
		node.setLocation(location);
		node.setSize(size);
	}

	@Override
	protected Command getCloneCommand(ChangeBoundsRequest request) {
		List<?> editParts = request.getEditParts();
		if(editParts.size() == 1) {
			GraphicalEditPart child = (GraphicalEditPart) editParts.get(0);
			return createCloneCommand(request, child, translateToModelConstraint(getConstraintFor(request, child)));
		}
		return null;
	}

	@Override
	protected Command createAddCommand(ChangeBoundsRequest request, EditPart child, Object constraint) {
		return createAddCommand(child, (Rectangle)constraint);
	}
	
	protected Command createCloneCommand(ChangeBoundsRequest request, EditPart child, Object contraint) {
		return createCloneCommand(child, (Rectangle) contraint);
	}

	@Override
	protected Command createChangeConstraintCommand(EditPart child,
			Object constraint) {
		return createChangeConstraintCommand(child, (Rectangle) constraint);
	}
}