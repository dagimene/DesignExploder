package designexploder.actions;

import org.eclipse.jface.dialogs.IInputValidator;

import designexploder.model.NodeContainer;
import designexploder.util.adt.IdUtil;

public class ApplicationContextNameValidator implements IInputValidator {

	private final NodeContainer diagram;

	public ApplicationContextNameValidator(NodeContainer diagram) {
		this.diagram = diagram;
	}

	@Override
	public String isValid(String name) {
		String result = null;
		if(name == null || name.isEmpty()) {
			result = "Name cannot be empty";
		} else if(!checkAllCharacters(name)) {
			result = "Name may only contain numbers and digits, and start with a letter";
		} else if(diagram != null && diagram.findNode(IdUtil.createContextId(name + ".xml").toString()) != null) {
			result = "Another context already exists with this name";
		} else if(name.toLowerCase().equals("main")) {
			result = "'main' is a reserved name for the root context";
		}
		return result;
	}

	private boolean checkAllCharacters(String name) {
		name = name.toLowerCase();
		char first = name.charAt(0);
		if((first < 'a' || first > 'z')) {
			return false;
		}
		for (char aChar : name.toCharArray()) {
			if(	(aChar < 'a' || aChar > 'z') &&
				(aChar < '0' || aChar > '9')) {
				return false;
			}
		}
		return true;
	}

}
