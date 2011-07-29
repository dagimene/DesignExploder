package designexploder.editor.controllers.commands;

import org.eclipse.draw2d.geometry.Point;

import designexploder.model.Node;

public class MoveNodeCommand extends NodeCommand {

	private Point location;

	public MoveNodeCommand(Node node, Point location) {
		super("MoveNodeCommand", node);
		this.location = location;
	}

	@Override
	public void execute() {
		Point temp = getModel().getLocation();
		getModel().setLocation(location);
		location = temp;
	}

	@Override
	public void undo() {
		execute();
	}
}
