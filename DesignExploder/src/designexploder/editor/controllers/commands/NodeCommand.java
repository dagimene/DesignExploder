package designexploder.editor.controllers.commands;

import org.eclipse.gef.commands.Command;

import designexploder.model.Node;

public abstract class NodeCommand extends Command {
	
	private Node model;

	public NodeCommand() {
		super();
	}

	public NodeCommand(String label, Node model) {
		super(label);
		this.model = model;
	}

	public NodeCommand(Node model) {
		this.model = model;
	}

	public NodeCommand(String label) {
		this(label, null);
	}

	public Node getModel() {
		return model;
	}

	public void setModel(Node model) {
		this.model = model;
	}
}
