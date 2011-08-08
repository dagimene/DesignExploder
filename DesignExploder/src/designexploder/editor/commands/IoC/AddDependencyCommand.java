package designexploder.editor.commands.IoC;

import designexploder.editor.commands.NodeCommand;
import designexploder.model.Node;
import designexploder.model.extension.IoC.BeanNode;
import designexploder.model.extension.IoC.Dependency;

public class AddDependencyCommand extends NodeCommand {

	private Dependency dependency;

	public AddDependencyCommand(Node model, Dependency dependency) {
		super("AddDependencyCommand", model);
		this.dependency = dependency;
	}
	
	@Override
	public void execute() {
		getModel().getExtension(BeanNode.class).addDependency(dependency);
	}
	
	@Override
	public void undo() {
		getModel().getExtension(BeanNode.class).removeDependency(dependency);
	}

}
