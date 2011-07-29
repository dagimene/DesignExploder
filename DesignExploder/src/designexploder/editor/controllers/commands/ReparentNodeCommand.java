package designexploder.editor.controllers.commands;

import designexploder.model.Node;
import designexploder.model.NodeContainer;

public class ReparentNodeCommand extends NodeCommand {

	private NodeContainer parent;

	public ReparentNodeCommand(Node model, NodeContainer parent) {
		super("ReparentNodeCommand", model);
		this.parent = parent;
	}

	@Override
	public void execute() {
		NodeContainer newContainer = parent;
		parent = (NodeContainer) getModel().getNodeContainer(); 
		parent.removeNode(getModel());
		getModel().setNodeContainer(newContainer);
		newContainer.addNode(getModel());
	}

	@Override
	public void undo() {
		execute();
	}
}
