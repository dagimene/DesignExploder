package designexploder.editor.controllers.policies;

import designexploder.editor.actions.ShowInheritedMembersAction;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.AbstractEditPolicy;

import designexploder.model.Node;

public class ClassNodeRequestsEditPolicy extends AbstractEditPolicy {

	@Override
	public Command getCommand(Request request) {
		Command result = null;
		if(understandsRequest(request)) {
            result = ShowInheritedMembersAction.createToggleInheritedMembersCommand((Node) getHost().getModel());
		}
		return result;
	}

    @Override
	public boolean understandsRequest(Request req) {
		return req.getType() == REQ_OPEN;
	}

}
