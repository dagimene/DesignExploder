package designexploder.editor.commands;

import designexploder.model.extension.common.Naturalized;
import designexploder.model.extension.common.Nature;

public class ChangeNatureCommand extends ModelCommand {

	private Nature nature;

	public ChangeNatureCommand(Naturalized model, Nature nature) {
		super(model);
		this.nature = nature;
	}

	@Override
	public void execute() {
		Nature nature = getModel().getNature();
		getModel().setNature(this.nature);
		this.nature = nature;
	}
	
	@Override
	public void undo() {
		execute();
	}
	
	@Override
	public Naturalized getModel() {
		return (Naturalized) super.getModel();
	}
	
}
