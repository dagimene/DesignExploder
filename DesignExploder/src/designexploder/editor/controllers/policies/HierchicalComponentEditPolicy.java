package designexploder.editor.controllers.policies;

import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

public abstract class HierchicalComponentEditPolicy extends ComponentEditPolicy {

	/**
	 * Delete request sent when a part parent is being removed 
	 */
	public static final String REQ_DELETE_PARENT = "Delete class node";

	@Override
	public Command getCommand(Request request) {
		if(request.getType() == REQ_DELETE_PARENT) {
			return createDeleteParentCommand((GroupRequest) request);
		}
		return super.getCommand(request);
	}

	protected abstract Command createDeleteParentCommand(GroupRequest deleteParentRequest);

	@Override
	public boolean understandsRequest(Request req) {
		return req.getType() == REQ_DELETE_PARENT || super.understandsRequest(req);
	}
	
}
