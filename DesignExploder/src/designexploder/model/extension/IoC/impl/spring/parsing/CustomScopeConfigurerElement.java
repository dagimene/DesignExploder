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

    public void declareScope(String bean, String key) {
        Element entry = new Element(ENTRY, BEANS_NS);
        entry.addAttribute(new Attribute(KEY, key));
        RefElement ref = new RefElement();
        ref.setBean(bean);
        entry.appendChild(ref);
        map.appendChild(entry);
    }

}
