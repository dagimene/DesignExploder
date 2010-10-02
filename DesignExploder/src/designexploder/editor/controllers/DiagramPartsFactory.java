package designexploder.editor.controllers;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;

import designexploder.model.Connection;
import designexploder.model.Diagram;
import designexploder.model.Node;

public class DiagramPartsFactory implements EditPartFactory {
	
	public EditPart createEditPart(EditPart context, Object model) {
		EditPart editPart = null;
		if(model instanceof Diagram) {
			editPart = new DexDiagramEditPart();
		} else if(model instanceof Node) {
			editPart = new NodeEditPart();
		} else if(model instanceof Connection) {
			editPart = new ConnectionEditPart();
		}
		if(editPart != null) {
			editPart.setModel(model);
		}
		return editPart;
	}
}
