package designexploder.editor.commands.extension;

import designexploder.editor.commands.ModelCommand;
import designexploder.model.ExtensibleModelElement;
import designexploder.model.ModelExtension;

public class AddExtensionCommand<E extends ModelExtension> extends ModelCommand {

	private final E extension;
	private final Class<E> clazz;

	public AddExtensionCommand(ExtensibleModelElement node, Class<E> clazz, E extension) {
		super(node);
		this.clazz = clazz;
		this.extension = extension;
	}

	@Override
	public void execute() {
		getModel().addExtension(extension);
	}

	@Override
	public void undo() {
		getModel().removeExtension(clazz);
	}
	
	@Override
	public ExtensibleModelElement getModel() {
		return (ExtensibleModelElement) super.getModel();
	}

}
