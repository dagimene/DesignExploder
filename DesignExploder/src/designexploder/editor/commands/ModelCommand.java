package designexploder.editor.commands;

import org.eclipse.gef.commands.Command;

import designexploder.model.event.ModelEventTrigger;

public class ModelCommand extends Command {
	
	private ModelEventTrigger model;

	public ModelCommand(String label, ModelEventTrigger model) {
		super(label);
		this.model = model;
	}

	public ModelCommand(ModelEventTrigger model) {
		this.model = model;
	}

	public ModelEventTrigger getModel() {
		return model;
	}

}
