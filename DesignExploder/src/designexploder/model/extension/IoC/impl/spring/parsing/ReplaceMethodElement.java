package designexploder.model.extension.IoC.impl.spring.parsing;

import nu.xom.Attribute;
import nu.xom.Element;

import static designexploder.model.extension.IoC.impl.spring.parsing.ParsingConstants.*;

public class ReplaceMethodElement extends Element {

	public ReplaceMethodElement() {
		super(REPLACED_METHOD, BEANS_NS);
        addAttribute(new Attribute(REPLACER, REPLACER_ID));
	}

    public String getName() {
        return getAttributeValue(NAME);
    }

    public void setName(String name) {
        addAttribute(new Attribute(NAME, name));
    }

    public static ReplaceMethodElement create(String name) {
        ReplaceMethodElement replaceMethodElement = new ReplaceMethodElement();
        replaceMethodElement.setName(name);
        return replaceMethodElement;
    }
}
