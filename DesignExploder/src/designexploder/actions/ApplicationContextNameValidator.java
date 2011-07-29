package designexploder.actions;

import org.eclipse.jface.dialogs.IInputValidator;

public class ApplicationContextNameValidator implements IInputValidator {

	@Override
	public String isValid(String name) {
		String result = null;
		if(name == null || name.isEmpty()) {
			result = "Name cannot be empty";
		} else if(!checkAllCharacters(name)) {
			result = "Name may only contain numbers and digits";
		}
		return result;
	}

	private boolean checkAllCharacters(String name) {
		for (char aChar : name.toLowerCase().toCharArray()) {
			if(	(aChar < 'a' || aChar > 'z') &&
				(aChar < 0 || aChar > 9)) {
				return false;
			}
		}
		return true;
	}

}
