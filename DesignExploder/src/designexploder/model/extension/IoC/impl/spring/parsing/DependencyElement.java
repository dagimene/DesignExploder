package designexploder.model.extension.IoC.impl.spring.parsing;

import nu.xom.Attribute;
import nu.xom.Element;

import static designexploder.model.extension.IoC.impl.spring.parsing.ParsingConstants.*;

public class DependencyElement extends Element {

	public DependencyElement() {
		super(PROPERTY, BEANS_NS);
	}

	public String getName() {
		return getAttributeValue(NAME);
	}
	
	public String getValueProperty() {
		return getAttributeValue(VALUE);
	}

	public void setValueProperty(String name) {
		addAttribute(new Attribute(VALUE, name));
	}
	
	public void setName(String name) {
		addAttribute(new Attribute(NAME, name));
	}

}
