package designexploder.editor.controllers;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import designexploder.editor.controllers.commands.RemoveNodeCommand;
import designexploder.model.Node;

final class ApplicationContextComponentEditPolicy extends ComponentEditPolicy {

	@Override
	protected Command createDeleteCommand(GroupRequest deleteRequest) {
		return new RemoveNodeCommand((Node) this.getHost().getModel());
	}
	
}