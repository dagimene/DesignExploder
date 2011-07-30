package designexploder.editor.controllers.commands;

import designexploder.model.BasicModelUtil;
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
		NodeContainer parent = this.parent;
		this.parent = (NodeContainer) getModel().getNodeContainer();
		BasicModelUtil.reparentNode(getModel(), parent);
	}

	@Override
	public void undo() {
		execute();
	}
}
