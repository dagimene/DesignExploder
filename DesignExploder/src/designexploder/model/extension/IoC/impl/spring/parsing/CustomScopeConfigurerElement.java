package designexploder.model.extension.IoC.impl.spring.parsing;

import nu.xom.Attribute;
import nu.xom.Element;

import static designexploder.model.extension.IoC.impl.spring.parsing.ParsingConstants.*;

public class CustomScopeConfigurerElement extends BeanElement {

    private Element map;

    public CustomScopeConfigurerElement() {
        setClazz(CUSTOM_SCOPE_CONFIGURER_CLASS);
        DependencyElement propertyElement = new DependencyElement();
        propertyElement.setName(SCOPES);
        propertyElement.appendChild(map = new Element(MAP, BEANS_NS));
        appendChild(propertyElement);
    }

    public void declareScope(String name) {
        Element entry = new Element(ENTRY, BEANS_NS);
        entry.addAttribute(new Attribute(KEY, name));
        BeanElement bean = new BeanElement();
        bean.setClazz(CONTEXT_SCOPE_IMPL_CLASS);
        ConstructorArg constructorArg = new ConstructorArg();
        constructorArg.setType(STRING_CLASS);
        constructorArg.setValue(name);
        bean.appendChild(constructorArg);
        constructorArg = new ConstructorArg();
        constructorArg.setType(DEX_CONTEXT_INSTANCE_CLASS);
        constructorArg.setRef(CONTEXT);
        bean.appendChild(constructorArg);
        entry.appendChild(bean);
        map.appendChild(entry);
    }

    static class ConstructorArg extends Element {

        public ConstructorArg() {
            super(CONSTRUCTOR_ARG, BEANS_NS);
        }

        public void setType(String type) {
            addAttribute(new Attribute(TYPE, type));
        }

        public void setValue(String value) {
            addAttribute(new Attribute(VALUE, value));
        }

        public void setRef(String ref) {
            addAttribute(new Attribute(REF, ref));
        }
    }

}
