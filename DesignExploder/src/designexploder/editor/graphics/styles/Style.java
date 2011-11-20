package designexploder.editor.graphics.styles;

import static designexploder.editor.graphics.styles.Style.Constant.*; 

import java.util.HashMap;
import java.util.Map;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;

import designexploder.editor.graphics.EndpointDecorationsFactory;
import designexploder.resources.IconResource;

public enum Style {
	
	// Node styles
	
	// General
	
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
			SELECTED_FONT, new Font(null, "Arial", 10, SWT.BOLD),
			ICON, IconResource.ERROR_ICON),
			
	// classmodel	
			
	CLASS(BASE_STYLE,
			BACKGROUNG, new Color(null,245,228,156),
			SELECTED_BACKGROUND, new Color(null,245,228,156),
			FONT, new Font(null, "Arial", 12, SWT.NORMAL),
			SELECTED_FONT, new Font(null, "Arial", 12, SWT.NORMAL),
			ICON, IconResource.CLASS_ICON),
	
	ENUM(CLASS,
			BACKGROUNG, new Color(null,255,255,128),
			SELECTED_BACKGROUND, new Color(null,255,255,128),
			ICON, IconResource.ENUM_ICON),

	ABSTRACT_CLASS(CLASS,
			FONT, new Font(null, "Arial", 12, SWT.ITALIC),
			SELECTED_FONT, new Font(null, "Arial", 12, SWT.ITALIC),
			ICON, IconResource.ABSTRACT_CLASS_ICON),
			
	INTERFACE(ABSTRACT_CLASS,
			BACKGROUNG, new Color(null,204,125,146),
			SELECTED_BACKGROUND, new Color(null,204,125,146),
			ICON, IconResource.INTERFACE_ICON),

	MEMBER(BASE_STYLE,
			FONT, new Font(null, "Arial", 10, SWT.NORMAL),
			SELECTED_FONT, new Font(null, "Arial", 10, SWT.BOLD)),
	
	ABSTRACT_METHOD(MEMBER,
			FONT, new Font(null, "Arial", 10, SWT.ITALIC),
			SELECTED_FONT, new Font(null, "Arial", 10, SWT.ITALIC | SWT.BOLD)),
			
	// IoC model
			
	CONTEXT(CLASS,
			BACKGROUNG, new Color(null,206,169,144),
			SELECTED_BACKGROUND, new Color(null,206,169,144),
			ICON, IconResource.BEAN_ICON),

	BEAN(CLASS,
			BACKGROUNG, new Color(null,206,169,144),
			SELECTED_BACKGROUND, new Color(null,206,169,144),
			ICON, IconResource.BEAN_ICON),

	BEAN_FACADE(BEAN,
			ICON, IconResource.BEAN_FACADE_ICON),

	BEAN_AUTO(BEAN,
			ICON, IconResource.BEAN_AUTO_ICON),
			
	BEAN_STATELESS(BEAN,
			ICON, IconResource.BEAN_STATELESS_ICON),

	BEAN_FACTORY(BEAN,
			ICON, IconResource.BEAN_FACTORY_ICON),

	// IoCAwareMethods
			
	IOC_METHOD_FACTORY(MEMBER,
			ICON, IconResource.BEAN_METHOD_FACTORY_ICON),
			
	IOC_METHOD_INIT(MEMBER,
			ICON, IconResource.BEAN_METHOD_INIT_ICON),

    IOC_METHOD_FINALIZE(MEMBER,
			ICON, IconResource.BEAN_METHOD_FINALIZE_ICON),

	IOC_METHOD_INSTANTIATE(MEMBER,
			ICON, IconResource.BEAN_METHOD_INSTANTIATE_ICON),

    IOC_METHOD_ACTIVATE(MEMBER,
            ICON, IconResource.BEAN_METHOD_ACTIVATE_ICON),

    IOC_METHOD_DESTROY(MEMBER,
            ICON, IconResource.BEAN_METHOD_DESTROY_ICON),

	// Connection styles
			
	BASE_CONNECTION_STYLE(null,
			LINE_COLOR, ColorConstants.black,
			LINE_DASH, SWT.LINE_SOLID,
			LINE_WIDTH, 2),

	INDIFFERENT_CONNECTION(BASE_CONNECTION_STYLE,
			LINE_COLOR, ColorConstants.lightGray,
			LINE_WIDTH, 1),
			
	HIERARCHY(BASE_CONNECTION_STYLE,
			TARGET_DECORATION, EndpointDecorationsFactory.CLOSED_ARROW),			

	REALIZATION(HIERARCHY,
			LINE_DASH, SWT.LINE_DASH),			

	COMPOSITION(BASE_CONNECTION_STYLE,
			SOURCE_DECORATION, EndpointDecorationsFactory.FILLED_DIAMOND,
			TARGET_DECORATION, EndpointDecorationsFactory.OPEN_ARROW),			
			
	ASSOCIATION(BASE_CONNECTION_STYLE,
			TARGET_DECORATION, EndpointDecorationsFactory.OPEN_ARROW),			
			
	//AGREGATION(BASE_CONNECTION_STYLE),

	// Nature used for both members and connections
	BASE_INJECTION(MEMBER,
			LINE_COLOR, ColorConstants.blue,
			LINE_DASH, SWT.LINE_SOLID,
			LINE_WIDTH, 1,
			TARGET_DECORATION, EndpointDecorationsFactory.OPEN_ARROW),			

	UNRESOLVED_DEPENDENCY(BASE_INJECTION,
			LINE_COLOR, ColorConstants.red,
			LINE_DASH, SWT.LINE_DASH,
			ICON, IconResource.INJECTION_WARNING_ICON,			
			LINE_ICON, IconResource.WARNING_ICON),
	
	INJECTION_BEAN(BASE_INJECTION,
			ICON, IconResource.BEAN_ICON,
			LINE_ICON, IconResource.BEAN_ICON),			

	INJECTION_COLLECTION(BASE_INJECTION,
			ICON, IconResource.BEAN_FIELD_COLLECTION_ICON,
			LINE_ICON, IconResource.BEAN_FIELD_COLLECTION_ICON),

    INJECTION_PROXIES_COLLECTION(BASE_INJECTION,
            ICON, IconResource.BEAN_FIELD_PROXIES_COLLECTION_ICON,
            LINE_ICON, IconResource.BEAN_FIELD_PROXIES_COLLECTION_ICON),

	INJECTION_TREE(BASE_INJECTION,
			ICON, IconResource.BEAN_FIELD_TREE_ICON,
			LINE_ICON, IconResource.BEAN_FIELD_TREE_ICON),
	
	INJECTION_PROXY(BASE_INJECTION,
			ICON, IconResource.BEAN_FIELD_PROXY_ICON,
			LINE_ICON, IconResource.BEAN_FIELD_PROXY_ICON);

	// -----------------------------------------------------------
			
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

	public IconResource getImage(Style.Constant property) {
		Object value = getValue(property);
		return value instanceof IconResource ? (IconResource) value : null;
	}

	public Integer getInt(Constant property) {
		Object value = getValue(property);
		return (value instanceof Integer ? (Integer) value : null);
	}

	public RotatableDecoration getDecoration(Constant property) {
		Object value = getValue(property);
		return (value instanceof EndpointDecorationsFactory ? ((EndpointDecorationsFactory) value).create() : null);
	}

	public static enum Constant {
		// Node
		FONT, SELECTED_FONT,
		FOREGROUND, SELECTED_FOREGROUND,
		BACKGROUNG, SELECTED_BACKGROUND,
		
		//Connection
		LINE_COLOR, LINE_ALPHA, LINE_WIDTH, LINE_DASH, SOURCE_DECORATION, TARGET_DECORATION,
		
		// Mixed
		ICON, LINE_ICON, ;
		
		public static Constant getBackground(boolean selected) {
			return selected ? SELECTED_BACKGROUND : BACKGROUNG;
		}

		public static Constant getForeground(boolean selected) {
			return selected ? SELECTED_FOREGROUND : FOREGROUND;
		}

		public static Constant getFont(boolean selected) {
			return selected ? SELECTED_FONT : FONT;
		}

		public static Constant getLineColor() {
			return LINE_COLOR;
		}

		public static Constant getIcon() {
			return ICON;
		}

		public static Constant getLineDash() {
			return LINE_DASH;
		}

		public static Constant getSourceDecoration() {
			return SOURCE_DECORATION;
		}

		public static Constant getTargetDecoration() {
			return TARGET_DECORATION;
		}

		public static Constant getLineWidth() {
			return LINE_WIDTH;
		}

		public static Constant getLineIcon() {
			return LINE_ICON;
		}

		public static Constant getLineAlpha() {
			return LINE_ALPHA;
		}
	}
}
