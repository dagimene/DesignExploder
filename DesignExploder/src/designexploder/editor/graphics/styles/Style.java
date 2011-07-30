package designexploder.editor.graphics.styles;

import static designexploder.editor.graphics.styles.Style.Constant.*; 

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;

import designexploder.resources.ResourcesManager;

public enum Style {
	
	BASE_STYLE(null,
			FOREGROUND, new Color(null,0,0,0),
			SELECTED_FOREGROUND, new Color(null,0,0,0),
			BACKGROUNG, new Color(null,255,255,255),
			SELECTED_BACKGROUND, new Color(null,255,255,255),
			FONT, new Font(null, "Arial", 10, SWT.NORMAL),
			SELECTED_FONT, new Font(null, "Arial", 10, SWT.NORMAL)),

	ERROR(null,
			FOREGROUND, new Color(null,125,0,0),
			SELECTED_FOREGROUND, new Color(null,125,0,0),
			BACKGROUNG, new Color(null,255,0,0),
			SELECTED_BACKGROUND, new Color(null,255,0,0),
			FONT, new Font(null, "Arial", 10, SWT.BOLD),
			SELECTED_FONT, new Font(null, "Arial", 10, SWT.BOLD)),
			
	CLASS(BASE_STYLE,
			BACKGROUNG, new Color(null,245,228,156),
			SELECTED_BACKGROUND, new Color(null,245,228,156),
			FONT, new Font(null, "Arial", 12, SWT.NORMAL),
			SELECTED_FONT, new Font(null, "Arial", 12, SWT.NORMAL),
			ICON, ResourcesManager.CLASS_ICON),
	
	ENUM(CLASS,
			BACKGROUNG, new Color(null,255,255,128),
			SELECTED_BACKGROUND, new Color(null,255,255,128),
			ICON, ResourcesManager.ENUM_ICON),

	ABSTRACT_CLASS(CLASS,
			FONT, new Font(null, "Arial", 12, SWT.ITALIC),
			SELECTED_FONT, new Font(null, "Arial", 12, SWT.ITALIC),
			ICON, ResourcesManager.ABSTRACT_CLASS_ICON),
			
	INTERFACE(ABSTRACT_CLASS,
			BACKGROUNG, new Color(null,204,125,146),
			SELECTED_BACKGROUND, new Color(null,204,125,146),
			ICON, ResourcesManager.INTERFACE_ICON),
			
	COMMON_BEAN(CLASS,
			BACKGROUNG, new Color(null,206,169,144),
			SELECTED_BACKGROUND, new Color(null,206,169,144),
			ICON, ResourcesManager.BEAN_ICON),

	MEMBER(BASE_STYLE/*,
			FONT, new Font(null, "Arial", 10, SWT.NORMAL),
			SELECTED_FONT, new Font(null, "Arial", 10, SWT.BOLD)*/),
	
	ABSTRACT_METHOD(MEMBER,
			FONT, new Font(null, "Arial", 10, SWT.ITALIC),
			SELECTED_FONT, new Font(null, "Arial", 10, SWT.ITALIC));
			
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

	public Image getImage(Style.Constant property) {
		Object value = getValue(property);
		// Lazy initialization
		if(value instanceof String) {
			properties.put(property, ResourcesManager.getImage((String) value));
		}
		return value instanceof Image ? (Image) value : null;
	}

	public static enum Constant {
		FONT, SELECTED_FONT,
		FOREGROUND, SELECTED_FOREGROUND,
		BACKGROUNG, SELECTED_BACKGROUND,
		ICON;
		
		public static Constant getBackground(boolean selected) {
			return selected ? SELECTED_BACKGROUND : BACKGROUNG;
		}

		public static Constant getForeground(boolean selected) {
			return selected ? SELECTED_FOREGROUND : FOREGROUND;
		}

		public static Constant getFont(boolean selected) {
			return selected ? SELECTED_FONT : FONT;
		}

		public static Constant getIcon() {
			return ICON;
		}
	}
}
