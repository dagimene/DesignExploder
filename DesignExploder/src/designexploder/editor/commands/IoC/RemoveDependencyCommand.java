package designexploder.editor.commands.IoC;

import designexploder.editor.commands.NodeCommand;
import designexploder.model.Node;
import designexploder.model.extension.IoC.BeanNode;
import designexploder.model.extension.IoC.Dependency;

public class RemoveDependencyCommand extends NodeCommand {

	private Dependency dependency;

	public RemoveDependencyCommand(Node model, Dependency dependency) {
		super("RemoveDependencyCommand", model);
		this.dependency = dependency;
	}
	
	@Override
	public void execute() {
		getModel().getExtension(BeanNode.class).removeDependency(dependency);
	}
	
	@Override
	public void undo() {
		getModel().getExtension(BeanNode.class).addDependency(dependency);
	}

}
