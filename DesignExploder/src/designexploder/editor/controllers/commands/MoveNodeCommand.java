package designexploder.editor.controllers.commands;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

import designexploder.model.classnode.ClassNode;

public class MoveNodeCommand extends Command {

	private ClassNode<?> node;
	private Point location;

	public MoveNodeCommand(ClassNode<?> node, Point location) {
		super("MoveNodeCommand");
		this.node = node;
		this.location = location;
	}

	@Override
	public void execute() {
		Point temp = node.getLocation();
		node.setLocation(location);
		location = temp;
	}

	@Override
	public void undo() {
		execute();
	}
}
