package designexploder.editor.controllers.commands;

import designexploder.model.BasicModelUtil;
import designexploder.model.Node;

public class AddNodeCommand extends NodeCommand {

	public AddNodeCommand(Node model) {
		super("CreateNodeCommand", model);
	}

	@Override
	public void execute() {
		BasicModelUtil.addNode(getModel());
	}

	@Override
	public void undo() {
		BasicModelUtil.removeNode(getModel());
	}
}
