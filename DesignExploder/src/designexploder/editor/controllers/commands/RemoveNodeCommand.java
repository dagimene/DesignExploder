package designexploder.editor.controllers.commands;

import designexploder.model.BasicModelUtil;
import designexploder.model.Node;

public class RemoveNodeCommand extends NodeCommand {

	public RemoveNodeCommand(Node model) {
		super("DeleteNodeCommand", model);
	}

	@Override
	public void execute() {
		BasicModelUtil.removeNode(getModel());
	}

	@Override
	public void undo() {
		BasicModelUtil.addNode(getModel());
	}

}
