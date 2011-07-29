package designexploder.editor.controllers.factory;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;


public class DexDiagramPartsFactory implements EditPartFactory {
	
	public EditPart createEditPart(EditPart context, Object model) {
		EditPart editPart = DexModelEntityTypeMapper.typeFor(model).instantiateEditPart();
		if(editPart != null) {
			editPart.setModel(model);
		}
		return editPart;
	}
}
