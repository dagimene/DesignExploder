package designexploder.editor.commands;

import designexploder.model.Node;

public abstract class NodeCommand extends ModelCommand {
	
	public NodeCommand(String label, Node model) {
		super(label, model);
	}

	public NodeCommand(Node model) {
		super(model);
	}

	public Node getModel() {
		return (Node) super.getModel();
	}
}
