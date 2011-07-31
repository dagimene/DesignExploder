package designexploder.editor.controllers.policies;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.requests.GroupRequest;

import designexploder.editor.controllers.commands.RemoveNodeCommand;
import designexploder.model.Node;
import designexploder.util.adt.ADTUtil;
import designexploder.util.adt.CastIterator;
import designexploder.util.adt.Condition;

public class ApplicationContextComponentEditPolicy extends HierchicalComponentEditPolicy {

	@Override
	protected Command createDeleteCommand(GroupRequest deleteRequest) {
		if(deleteRequest.getEditParts().size() > 1) {
			return UnexecutableCommand.INSTANCE;
		}

		List<EditPart> childrenEditParts = new ArrayList<EditPart>();
		GroupRequest deleteParentRequest = new GroupRequest(REQ_DELETE_PARENT);
		addPartAndChildren(getHost(), childrenEditParts);
		deleteParentRequest.setEditParts(childrenEditParts);
		
		return createDeleteParentCommand(deleteParentRequest);			
	}
	
	@Override
	@SuppressWarnings("unchecked")
	protected Command createDeleteParentCommand(final GroupRequest deleteParentRequest) {
		return ADTUtil.forAll(getHost().getChildren().iterator(), 
			new Condition<Object>() {
				@Override
				public boolean check(Object editPart) {
					Command childCommand = ((EditPart) editPart).getCommand(deleteParentRequest);
					return childCommand != null && childCommand.canExecute();
				}
			}) ? new RemoveNodeCommand((Node) this.getHost().getModel()) : null;
	}
	
	private void addPartAndChildren(EditPart editPart,
			List<EditPart> allEditParts) {
		allEditParts.add(editPart);
		for(EditPart child :
				CastIterator.<EditPart>createIterable(editPart.getChildren().iterator())) {
			addPartAndChildren(child, allEditParts);
		}
	}
}