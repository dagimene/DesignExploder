package designexploder.model.extension.IoC.impl.spring.parsing;

import nu.xom.Attribute;
import nu.xom.Element;

import static designexploder.model.extension.IoC.impl.spring.parsing.ParsingConstants.*;

public class ContextMethodsReplacerElement extends BeanElement {

    public ContextMethodsReplacerElement() {
        setClazz(CONTEXT_METHODS_REPLACER_CLASS);
        addNameAttribute(REPLACER_ID);
        appendChild(ConstructorArg.dexContextInstance());
    }

}
