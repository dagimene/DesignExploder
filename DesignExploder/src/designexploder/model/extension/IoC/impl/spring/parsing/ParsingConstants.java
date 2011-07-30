package designexploder.model.extension.IoC.impl.spring.parsing;

import java.util.HashMap;
import java.util.Map;

public class ParsingConstants {

	public static final String BEANS_NS = "http://www.springframework.org/schema/beans";

	public static final String BEANS = "beans";
	
	public static final String BEAN = "bean";

	public static final String ID = "id";

	public static final String NAME = "name";
	
	public static final String CLASS = "class";
	
	public static final String PROPERTY = "property";

	public static final String REF = "ref";

	private static Map<String, ParsingElement> elements = new HashMap<String, ParsingElement>();
	
	static {
		elements.put(BEANS, ParsingElement.BEANS);
		elements.put(BEAN, ParsingElement.BEAN);
		elements.put(PROPERTY, ParsingElement.PROPERTY);
	}
	
	public static ParsingElement valueOf(String name) {
		return elements.get(name);
	}
	
	public static enum ParsingElement {
		BEANS, BEAN, PROPERTY;
	}

}
