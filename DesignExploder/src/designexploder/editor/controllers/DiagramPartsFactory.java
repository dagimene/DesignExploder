package designexploder.editor.controllers;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import designexploder.model.Connection;
import designexploder.model.Diagram;
import designexploder.model.Node;
import designexploder.model.classnode.ClassConnection;
import designexploder.model.classnode.ClassNode;

public class DiagramPartsFactory implements EditPartFactory {
	
	public EditPart createEditPart(EditPart context, Object model) {
		EditPart editPart = null;
		if(model instanceof ClassConnection) {
			editPart = new ClassConnectionEditPart();
		} else if(model instanceof ClassNode) {
			editPart = new ClassNodeEditPart();
		} else if(model instanceof Connection) {
			editPart = new ConnectionEditPart();
		} else if(model instanceof Node) {
			editPart = new NodeEditPart();
		} else if(model instanceof Diagram) {
			editPart = new DexDiagramEditPart(); 
		}
		if(editPart != null) {
			editPart.setModel(model);
		}
		return editPart;
	}
}
