package designexploder.model.extension.IoC.impl.spring.parsing;

import java.util.HashMap;
import java.util.Map;

public class ParsingConstants {

	public static final String BEANS_NS = "http://www.springframework.org/schema/beans";

    public static final String BEANS_SCHEMA = "http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-2.0.xsd http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-2.0.xsd";

    public static final String AOP_NS = "http://www.springframework.org/schema/aop";

    public static final String AOP_PREFIX = "aop";

    public static final String XSI_NS = "http://www.w3.org/2001/XMLSchema-instance";

    public static final String XSI_PREFIX = "xsi";

    public static final String SCHEMA_LOCATION = "schemaLocation";

	public static final String BEANS = "beans";

	public static final String BEAN = "bean";

	public static final String ID = "id";

	public static final String NAME = "name";
	
	public static final String CLASS = "class";

    public static final String AUTOWIRE = "autowire";

    public static final String INIT_METHOD = "init-method";

    public static final String BY_TYPE = "byType";

	public static final String PROPERTY = "property";

    public static final String CONSTRUCTOR_ARG = "constructor-arg";

    public static final String SCOPES = "scopes";

    public static final String SCOPE = "scope";

	public static final String REF = "ref";

	public static final String VALUE = "value";

    public static final String TYPE = "type";

	public static final String LIST = "list";

	public static final String SET = "set";

    public static final String MAP = "map";

    public static final String KEY = "key";

    public static final String ENTRY = "entry";

    public static final String CUSTOM_SCOPE_CONFIGURER_CLASS = "org.springframework.beans.factory.config.CustomScopeConfigurer";

    public static final String CONTEXT_SCOPE_IMPL_CLASS = "dex.DexContextScopeImpl";

    public static final String CONTEXT_SCOPE_CLASS = "dex.DexContextScope";

    public static final String CONTEXT_METHODS_REPLACER_CLASS = "dex.ContextMethodsReplacer";

    public static final String DEX_CONTEXT_INSTANCE_CLASS = "dex.DexContextInstance";

    public static final String DEX_SCOPED_BEANS_LIST_CLASS = "dex.DexScopedBeansList";

    public static final String STRING_CLASS = "java.lang.String";

    public static final String CONTEXT_ID = "::context";

    public static final String REPLACER_ID = "::replacer";

    public static final String REPLACER = "replacer";

    public static final String SCOPED_PROXY = "scoped-proxy";

    public static final String REPLACED_METHOD = "replaced-method";


	private static Map<String, ParsingElement> elements = new HashMap<String, ParsingElement>();
	
	static {
		elements.put(BEANS, ParsingElement.BEANS);
		elements.put(BEAN, ParsingElement.BEAN);
		elements.put(PROPERTY, ParsingElement.PROPERTY);

        elements.put(LIST, ParsingElement.LIST);
		elements.put(SET, ParsingElement.SET);
        elements.put(REF, ParsingElement.REF);
        elements.put(MAP, ParsingElement.MAP);
        elements.put(ENTRY, ParsingElement.ENTRY);

        elements.put(CONSTRUCTOR_ARG, ParsingElement.CONSTRUCTOR_ARG);
        elements.put(REPLACED_METHOD, ParsingElement.REPLACED_METHOD);
	}
	
	public static ParsingElement valueOf(String name) {
		return elements.get(name);
	}
	
	public static enum ParsingElement {
		BEANS, BEAN, PROPERTY,
        LIST, SET, REF, MAP, ENTRY,
        CONSTRUCTOR_ARG, REPLACED_METHOD;
	}

}
