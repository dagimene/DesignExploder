package designexploder.editor.controllers.commands;

import org.eclipse.gef.commands.Command;

import designexploder.model.ExtensibleModelElement;

public class ModelCommand extends Command {
	
	private ExtensibleModelElement model;

	public ModelCommand(String label, ExtensibleModelElement model) {
		super(label);
		this.model = model;
	}

	public ModelCommand(ExtensibleModelElement model) {
		this.model = model;
	}

	public ExtensibleModelElement getModel() {
		return model;
	}

}
