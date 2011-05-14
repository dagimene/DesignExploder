package designexploder.editor.graphics.styles;

import java.util.HashMap;
import java.util.Map;

import designexploder.model.classnode.DexConstant;

import static designexploder.model.classnode.DexConstant.*;

public class StylesFactory {
	
	private static StylesFactory instance = new StylesFactory();
	public static StylesFactory getInstance() {
		return instance;
	}
	
	private StylesFactory(){
		// Should count the applicable DexConstant, not the styles.
		styles = new HashMap<DexConstant, Style>(Style.values().length);
		
		/* Node styles */
		styles.put(CLASS, Style.CLASS);
		styles.put(ABSTRACT_CLASS, Style.ABSTRACT_CLASS);
		styles.put(ENUM, Style.ENUM);
		styles.put(INTERFACE, Style.INTERFACE);
		styles.put(METHOD, Style.MEMBER);
		styles.put(ATTRIBUTE, Style.MEMBER);
		styles.put(ABSTRACT_METHOD, Style.ABSTRACT_METHOD);
		styles.put(ERROR, Style.ERROR);

		/* Connection styles */
		styles.put(HIERARCHY, null);
		styles.put(REALIZATION, null);
		styles.put(COMPOSITION, null);
		styles.put(AGREGATION, null);
		styles.put(ASSOCIATION, null);
	}
	
	private Map<DexConstant, Style> styles;
	
	public Style getStyleFor(DexConstant nature) {
		return styles.get(nature);
	}
}
