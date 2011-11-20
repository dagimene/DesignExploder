package designexploder.model.extension.IoC.impl.spring.parsing;

import designexploder.model.extension.IoC.IoCModelNatures;
import nu.xom.Attribute;
import nu.xom.Element;

import static designexploder.model.extension.IoC.impl.spring.parsing.ParsingConstants.*;

public class ReplaceMethodElement extends Element {

	public ReplaceMethodElement() {
		super(REPLACED_METHOD, BEANS_NS);
        // addAttribute(new Attribute(REPLACER, REPLACER_ID));
	}

    public String getReplacer() {
        return getAttributeValue(REPLACER);
    }

    public void setReplacer(String replacer) {
        addAttribute(new Attribute(REPLACER, replacer));
    }

    public String getName() {
        return getAttributeValue(NAME);
    }

    public void setName(String name) {
        addAttribute(new Attribute(NAME, name));
    }

    public static ReplaceMethodElement create(String name, IoCModelNatures nature) {
        ReplaceMethodElement replaceMethodElement = new ReplaceMethodElement();
        replaceMethodElement.setName(name);
        replaceMethodElement.setReplacer("::" + nature.name());
        return replaceMethodElement;
    }

    public static ReplaceMethodElement create(String name, String scope) {
        ReplaceMethodElement replaceMethodElement = new ReplaceMethodElement();
        replaceMethodElement.setName(name);
        replaceMethodElement.setReplacer(scope);
        return replaceMethodElement;
    }
}
