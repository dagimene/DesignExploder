package designexploder.editor.graphics.styles;

import java.util.HashMap;
import java.util.Map;

import designexploder.model.classnode.DexConstant;

public class StylesFactory {
	
	private static StylesFactory instance = new StylesFactory();
	public static StylesFactory getInstance() {
		return instance;
	}
	
	private StylesFactory(){
		// Should count the applicable DexConstant, not the styles.
		styles = new HashMap<DexConstant, Style>(Style.values().length);
		styles.put(DexConstant.CLASS, Style.CLASS);
		styles.put(DexConstant.ABSTRACT_CLASS, Style.ABSTRACT_CLASS);
		styles.put(DexConstant.ENUM, Style.ENUM);
		styles.put(DexConstant.INTERFACE, Style.INTERFACE);
		styles.put(DexConstant.METHOD, Style.MEMBER);
		styles.put(DexConstant.ATTRIBUTE, Style.MEMBER);
		styles.put(DexConstant.ABSTRACT_METHOD, Style.ABSTRACT_METHOD);
		styles.put(DexConstant.ERROR, Style.ERROR);
	}
	
	private Map<DexConstant, Style> styles;
	
	public Style getStyleFor(DexConstant nature) {
		return styles.get(nature);
	}
}
