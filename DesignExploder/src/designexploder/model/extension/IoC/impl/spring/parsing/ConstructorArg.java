package designexploder.model.extension.IoC.impl.spring.parsing;

import nu.xom.Attribute;
import nu.xom.Element;
import org.eclipse.swt.custom.ST;

import static designexploder.model.extension.IoC.impl.spring.parsing.ParsingConstants.*;

/**
* Created by IntelliJ IDEA.
* User: dagimene
* Date: 11/8/11
* Time: 12:33 AM
* To change this template use File | Settings | File Templates.
*/
class ConstructorArg extends Element {

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

    public static ConstructorArg typeAndValue(String type, String value) {
        ConstructorArg constructorArg = new ConstructorArg();
        constructorArg.setType(type);
        constructorArg.setValue(value);
        return constructorArg;
    }

    public static ConstructorArg stringParameter(String value) {
        return typeAndValue(STRING_CLASS, value);
    }

    public static ConstructorArg dexContextInstance() {
        ConstructorArg constructorArg = new ConstructorArg();
        constructorArg.setType(DEX_CONTEXT_INSTANCE_CLASS);
        constructorArg.setRef(CONTEXT_ID);
        return constructorArg;
    }

    public static ConstructorArg dexContextScope(String name) {
        ConstructorArg constructorArg = new ConstructorArg();
        constructorArg.setType(CONTEXT_SCOPE_CLASS);
        constructorArg.setRef(name);
        return constructorArg;
    }
}
