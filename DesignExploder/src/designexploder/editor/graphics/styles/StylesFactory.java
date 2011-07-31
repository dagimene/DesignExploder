package designexploder.editor.graphics.styles;

import java.util.HashMap;
import java.util.Map;

import designexploder.model.extension.common.Nature;

import static designexploder.model.extension.classnode.ClassModelNatures.*;
import static designexploder.model.extension.IoC.IoCModelNatures.*;

public class StylesFactory {
	
	private static StylesFactory instance = new StylesFactory();
	public static StylesFactory getInstance() {
		return instance;
	}
	
	private StylesFactory(){
		// Should count the applicable DexConstant, not the styles.
		styles = new HashMap<Nature, Style>(Style.values().length);
		
		styles.put(NONE, Style.ERROR);

		// Class Node styles
		styles.put(CLASS, Style.CLASS);
		styles.put(ABSTRACT_CLASS, Style.ABSTRACT_CLASS);
		styles.put(ENUM, Style.ENUM);
		styles.put(INTERFACE, Style.INTERFACE);
		styles.put(METHOD, Style.MEMBER);
		styles.put(ATTRIBUTE, Style.MEMBER);
		styles.put(ABSTRACT_METHOD, Style.ABSTRACT_METHOD);
		styles.put(ERROR, Style.ERROR);

		// Bean Node styles
		styles.put(COMMON_BEAN, Style.COMMON_BEAN);

		// Class Connection styles
		
		styles.put(HIERARCHY, Style.HIERARCHY);
		styles.put(REALIZATION, Style.REALIZATION);
		styles.put(COMPOSITION, Style.COMPOSITION);
		styles.put(ASSOCIATION, Style.ASSOCIATION);
		// styles.put(AGREGATION, Style.AGREGATION);
		
		// Bean Injection styles
		styles.put(SINGLE, Style.SINGLE);
		
	}
	
	private Map<Nature, Style> styles;
	
	public Style getStyleFor(Nature nature) {
		return styles.get(nature);
	}
}