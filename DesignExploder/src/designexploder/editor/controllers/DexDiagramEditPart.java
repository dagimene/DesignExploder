package designexploder.editor.controllers;

import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;

import designexploder.editor.controllers.commands.MoveNodeCommand;
import designexploder.editor.controllers.policies.ClassSelectionPolicy;
import designexploder.editor.graphics.GraphicsFactory;
import designexploder.model.Diagram;
import designexploder.model.classnode.ClassNode;

public class DexDiagramEditPart extends AbstractGraphicalEditPart {
	
	@Override
	protected IFigure createFigure() {
		return GraphicsFactory.createDiagramFigure();
	}
	
	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new XYLayoutEditPolicy() {
			protected EditPolicy createChildEditPolicy(EditPart child) {
				return new ClassSelectionPolicy();
			}
			
			@Override
			protected Command getCreateCommand(CreateRequest request) {
				return null;
			}
			
			/**
			 * Translates draw2d Rectangle constraint to a draw2d Point 
			 */
			@Override
			protected Object translateToModelConstraint(Object figureConstraint) {
				return ((Rectangle)figureConstraint).getLocation();
			}

			/**
			 * Creates a move node command.
			 * @param child a ClassNodeEditPart
			 * @param constraint a Point
			 */
			@Override
			protected Command createChangeConstraintCommand(EditPart child,
					Object constraint) {
				return new MoveNodeCommand((ClassNode)((ClassNodeEditPart)child).getModel(), (Point)constraint);
			}
		});
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	protected List getModelChildren() {
		return ((Diagram)getModel()).getNodes();
	}
}
