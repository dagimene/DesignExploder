package designexploder.editor.commands.extension;

import designexploder.editor.commands.ModelCommand;
import designexploder.model.ExtensibleModelElement;
import designexploder.model.ModelExtension;

public class RemoveExtensionCommand<E extends ModelExtension> extends ModelCommand {

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
		getModel().addExtension(extension);
	}
	
	@Override
	public ExtensibleModelElement getModel() {
		return (ExtensibleModelElement) super.getModel();
	}

}
