package designexploder.editor.graphics.styles;

import java.util.HashMap;
import java.util.Map;

import designexploder.editor.graphics.styles.Style.Constant;
import designexploder.model.extension.common.Nature;
import designexploder.resources.IconResource;

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

		// Bean Node styles
		styles.put(BEAN, Style.BEAN);
		styles.put(BEAN_AUTO, Style.BEAN_AUTO);
		styles.put(BEAN_FACADE, Style.BEAN_FACADE);
		styles.put(BEAN_FACTORY, Style.BEAN_FACTORY);
		styles.put(BEAN_STATELESS, Style.BEAN_STATELESS);

		// Class Connection styles
		
		styles.put(HIERARCHY, Style.HIERARCHY);
		styles.put(REALIZATION, Style.REALIZATION);
		styles.put(COMPOSITION, Style.COMPOSITION);
		styles.put(ASSOCIATION, Style.ASSOCIATION);
		// styles.put(AGREGATION, Style.AGREGATION);
		
		// Dependency Injection styles
		styles.put(UNRESOLVED_DEPENDENCY, Style.UNRESOLVED_DEPENDENCY);
		styles.put(INJECTION_BEAN, Style.INJECTION_BEAN);
		styles.put(INJECTION_COLLECTION, Style.INJECTION_COLLECTION);
		styles.put(INJECTION_PROXY, Style.INJECTION_PROXY);
		styles.put(INJECTION_TREE, Style.INJECTION_TREE);
		
		// IoCAwareMethod styles
		styles.put(IOC_METHOD_FACTORY, Style.IOC_METHOD_FACTORY);
		styles.put(IOC_METHOD_INIT, Style.IOC_METHOD_INIT);
		styles.put(IOC_METHOD_INSTANTIATE, Style.IOC_METHOD_INSTANTIATE);
		
		// Misc
		styles.put(INDIFFERENT_CONNECTION, Style.INDIFFERENT_CONNECTION);
		styles.put(ERROR, Style.ERROR);

	}
	
	private Map<Nature, Style> styles;
	
	public Style getStyleFor(Nature nature) {
		return styles.get(nature);
	}
	
	public IconResource getIconFor(Nature nature) {
		return getStyleFor(nature).getImage(Constant.ICON);
	}

	public IconResource getLineIconFor(Nature nature) {
		return getStyleFor(nature).getImage(Constant.LINE_ICON);
	}
	
}