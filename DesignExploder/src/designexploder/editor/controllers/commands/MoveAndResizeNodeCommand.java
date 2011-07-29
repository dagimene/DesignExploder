package designexploder.editor.controllers.commands;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;

import designexploder.model.Node;

public class MoveAndResizeNodeCommand extends MoveNodeCommand {

	private Dimension size;

	public MoveAndResizeNodeCommand(Node node, Point location, Dimension size) {
		super(node, location);
		this.size = size;
	}

	@Override
	public void execute() {
		super.execute();
		Dimension temp = getModel().getSize();
		getModel().setSize(size);
		size = temp;
	}

}
