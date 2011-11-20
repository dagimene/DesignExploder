package designexploder.model.extension.IoC.impl.spring.parsing;

import nu.xom.Attribute;
import nu.xom.Element;

import static designexploder.model.extension.IoC.impl.spring.parsing.ParsingConstants.*;

public class ContextMethodsReplacerElement extends BeanElement {

    public ContextMethodsReplacerElement(String name) {
        setClazz(CONTEXT_METHODS_REPLACER_CLASS);
        addNameAttribute(name);
        appendChild(ConstructorArg.dexContextInstance());
        appendChild(ConstructorArg.stringParameter(name));
    }

}
