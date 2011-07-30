package designexploder.editor.controllers.commands.extension;

import designexploder.editor.controllers.commands.ModelCommand;
import designexploder.model.ExtensibleModelElement;
import designexploder.model.event.ModelEventTrigger;

public class AddExtensionCommand<E extends ModelEventTrigger> extends ModelCommand {

	private final E extension;
	private final Class<E> clazz;

	public AddExtensionCommand(ExtensibleModelElement node, Class<E> clazz, E extension) {
		super(node);
		this.clazz = clazz;
		this.extension = extension;
	}

	@Override
	public void execute() {
		getModel().addExtension(clazz, extension);
	}

	@Override
	public void undo() {
		getModel().removeExtension(clazz);
	}

}
