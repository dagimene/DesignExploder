package designexploder.model.extension.IoC.impl.spring.parsing;

import static designexploder.model.extension.IoC.impl.spring.parsing.ParsingConstants.*;

public class DexScopedBeansListElement extends BeanElement {

    public DexScopedBeansListElement(String scope, String clazz) {
        setClazz(DEX_SCOPED_BEANS_LIST_CLASS);
        appendChild(ConstructorArg.dexContextScope(scope));
        appendChild(ConstructorArg.stringParameter(clazz));
    }

}
