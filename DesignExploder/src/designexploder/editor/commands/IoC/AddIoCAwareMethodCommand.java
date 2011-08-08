package designexploder.editor.commands.IoC;

import designexploder.editor.commands.NodeCommand;
import designexploder.model.Node;
import designexploder.model.extension.IoC.BeanNode;
import designexploder.model.extension.IoC.IoCAwareMethod;

public class AddIoCAwareMethodCommand extends NodeCommand {

	private IoCAwareMethod method;

	public AddIoCAwareMethodCommand(Node model, IoCAwareMethod method) {
		super("AddIoCAwareMethodCommand", model);
		this.method = method;
	}
	
	@Override
	public void execute() {
		getModel().getExtension(BeanNode.class).addIoCAwareMethod(method);
	}
	
	@Override
	public void undo() {
		getModel().getExtension(BeanNode.class).removeIoCAwareMethod(method);
	}

}
