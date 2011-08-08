package designexploder.editor.commands.IoC;

import designexploder.editor.commands.NodeCommand;
import designexploder.model.Node;
import designexploder.model.extension.IoC.BeanNode;
import designexploder.model.extension.IoC.IoCAwareMethod;

public class RemoveIoCAwareMethodCommand extends NodeCommand {

	private IoCAwareMethod method;

	public RemoveIoCAwareMethodCommand(Node model, IoCAwareMethod method) {
		super("RemoveIoCAwareMethodCommand", model);
		this.method = method;
	}
	
	@Override
	public void execute() {
		getModel().getExtension(BeanNode.class).removeIoCAwareMethod(method);
	}
	
	@Override
	public void undo() {
		getModel().getExtension(BeanNode.class).addIoCAwareMethod(method);
	}

}
