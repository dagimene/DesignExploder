package designexploder.editor.controllers.commands;

import designexploder.model.Node;
import designexploder.model.NodeContainer;

public class RemoveNodeCommand extends NodeCommand {

	public RemoveNodeCommand(Node model) {
		super("DeleteNodeCommand", model);
	}

	@Override
	public void execute() {
		((NodeContainer)getModel().getNodeContainer()).removeNode(getModel());
	}

	@Override
	public void undo() {
		((NodeContainer)getModel().getNodeContainer()).addNode(getModel());
	}
	
}
