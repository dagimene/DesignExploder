package designexploder.editor.graphics.styles;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;

public enum Style {
	
	BASE_STYLE(null,
			Constant.FOREGROUND, new Color(null,0,0,0),
			Constant.SELECTED_FOREGROUND, new Color(null,0,0,0),
			Constant.BACKGROUNG, new Color(null,255,255,255),
			Constant.SELECTED_BACKGROUND, new Color(null,255,255,255),
			Constant.FONT, new Font(null, "Arial", 10, SWT.NORMAL),
			Constant.SELECTED_FONT, new Font(null, "Arial", 10, SWT.NORMAL)),
	
	CLASS(BASE_STYLE,
			Constant.BACKGROUNG, new Color(null,255,255,206),
			Constant.SELECTED_BACKGROUND, new Color(null,255,206,255),
			Constant.FONT, new Font(null, "Arial", 12, SWT.NORMAL),
			Constant.SELECTED_FONT, new Font(null, "Arial", 12, SWT.BOLD)),
	
	ABSTRACT_CLASS(CLASS,
			Constant.FONT, new Font(null, "Arial", 12, SWT.ITALIC),
			Constant.SELECTED_FONT, new Font(null, "Arial", 12, SWT.ITALIC | SWT.BOLD)),

	INTERFACE(ABSTRACT_CLASS,
			Constant.BACKGROUNG, new Color(null,255,206,206),
			Constant.SELECTED_BACKGROUND, new Color(null,206,206,255)),
			
	MEMBER(BASE_STYLE,
			Constant.FONT, new Font(null, "Arial", 10, SWT.NORMAL),
			Constant.SELECTED_FONT, new Font(null, "Arial", 10, SWT.BOLD)),
	
	ABSTRACT_METHOD(MEMBER,
			Constant.FONT, new Font(null, "Arial", 10, SWT.ITALIC),
			Constant.SELECTED_FONT, new Font(null, "Arial", 10, SWT.ITALIC | SWT.BOLD));
			
	private Map<Style.Constant, Object> properties;
	private Style parent;
	
	Style(Style parent, Object... keyvalues) {
		this.parent = parent;
		assert keyvalues.length % 2 == 0;
		this.properties = new HashMap<Style.Constant, Object>(keyvalues.length / 2);
		for (int i = 0; i < keyvalues.length; i+=2) {
			assert keyvalues[i] instanceof Style.Constant;
			properties.put((Style.Constant) keyvalues[i], keyvalues[i+1]);
		}
	}
	
	public Object getValue(Style.Constant property) {
		Object value = properties.get(property);
		return value != null ? value : (parent != null ? parent.getValue(property) : null);
	}
	
	public Font getFont(Style.Constant property) {
		Object value = getValue(property);
		return value instanceof Font ? (Font) value : null;
	}

	public Color getColor(Style.Constant property) {
		Object value = getValue(property);
		return value instanceof Color ? (Color) value : null;
	}

	public static enum Constant {
		FONT, SELECTED_FONT,
		FOREGROUND, SELECTED_FOREGROUND,
		BACKGROUNG, SELECTED_BACKGROUND;
		
		public static Constant getBackground(boolean selected) {
			return selected ? Constant.SELECTED_BACKGROUND : Constant.BACKGROUNG;
		}

		public static Constant getForeground(boolean selected) {
			return selected ? Constant.SELECTED_FOREGROUND : Constant.FOREGROUND;
		}

		public static Constant getFont(boolean selected) {
			return selected ? Constant.SELECTED_FONT : Constant.FONT;
		}
	}
}
