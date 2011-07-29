package designexploder.editor.controllers.commands;

import designexploder.model.Node;
import designexploder.model.NodeContainer;

public class AddNodeCommand extends NodeCommand {

	public AddNodeCommand(Node model) {
		super("CreateNodeCommand", model);
	}

	@Override
	public void execute() {
		((NodeContainer)getModel().getNodeContainer()).addNode(getModel());
	}

	@Override
	public void undo() {
		((NodeContainer)getModel().getNodeContainer()).removeNode(getModel());
	}
}
