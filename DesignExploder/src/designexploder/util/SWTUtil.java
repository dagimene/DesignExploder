package designexploder.util;

import org.eclipse.gef.tools.AbstractTool.Input;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Widget;

public class SWTUtil {

	public static MouseEvent createMouseEvent(Input input, Widget widget) {
		MouseEvent result = null;
		if(input != null && widget != null) {
			Event event = new Event();
			event.x = input.getMouseLocation().x;
			event.y = input.getMouseLocation().y;
			event.stateMask = SWT.BUTTON1;
			event.button = 1;
			event.widget = widget;
			result = new MouseEvent(event); 
		}
		return result;
	}
	
}
