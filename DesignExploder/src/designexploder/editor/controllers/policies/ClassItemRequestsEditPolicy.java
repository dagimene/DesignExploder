package designexploder.editor.controllers.policies;

import designexploder.editor.actions.InjectClassItemAction;
import designexploder.model.Node;
import designexploder.model.extension.classnode.ClassItem;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.AbstractEditPolicy;

public class ClassItemRequestsEditPolicy extends AbstractEditPolicy {

	@Override
	public Command getCommand(Request request) {
		Command result = null;
		if(understandsRequest(request)) {
            result = toggleFieldInjection();
		}
		return result;
	}

    private Command toggleFieldInjection() {
        Command command = null;
        Node node = (Node) getHost().getParent().getModel();
        ClassItem item = (ClassItem) getHost().getModel();
        if(InjectClassItemAction.canInjectClassItem(node, item)) {
            command = InjectClassItemAction.createInjectCommand(node, item);
        }
        return command;
    }

    @Override
	public boolean understandsRequest(Request req) {
		return req.getType() == REQ_OPEN;
	}

}
