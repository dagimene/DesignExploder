package designexploder.resources;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

public class ResourcesManager {

	public static final String CLASS_ICON = "class.gif";
	public static final String ENUM_ICON = "enum.gif";
	public static final String ABSTRACT_CLASS_ICON = "abstract_class.gif";
	public static final String INTERFACE_ICON = "interface.gif";
	public static final String BEAN_ICON = "bean.gif";  
	
	public static Image getImage(String name) {
		return new Image(Display.getCurrent(), ResourcesManager.class.getResourceAsStream("images/" + name));
	}
	
}
