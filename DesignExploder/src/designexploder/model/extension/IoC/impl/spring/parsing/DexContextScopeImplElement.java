package designexploder.model.extension.IoC.impl.spring.parsing;

import static designexploder.model.extension.IoC.impl.spring.parsing.ParsingConstants.*;

public class DexContextScopeImplElement extends BeanElement {

    public DexContextScopeImplElement(String name, String value) {
        setClazz(CONTEXT_SCOPE_IMPL_CLASS);
        addNameAttribute(name);
        appendChild(ConstructorArg.typeAndValue(STRING_CLASS, value));
        appendChild(ConstructorArg.dexContextInstance());
    }

}
