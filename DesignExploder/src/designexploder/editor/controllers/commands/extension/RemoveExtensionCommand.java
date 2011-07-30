package designexploder.editor.controllers.commands.extension;

import designexploder.editor.controllers.commands.ModelCommand;
import designexploder.model.ExtensibleModelElement;
import designexploder.model.event.ModelEventTrigger;

public class RemoveExtensionCommand<E extends ModelEventTrigger> extends ModelCommand {

	private final E extension;
	private final Class<E> clazz;

	public RemoveExtensionCommand(ExtensibleModelElement node, Class<E> clazz) {
		super(node);
		this.clazz = clazz;
		this.extension = node.getExtension(clazz);
	}

	@Override
	public void execute() {
		getModel().removeExtension(clazz);
	}

	@Override
	public void undo() {
		getModel().addExtension(clazz, extension);
	}

}
