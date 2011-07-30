package designexploder.model.extension.IoC.impl.spring.parsing;

import nu.xom.Element;

import static designexploder.model.extension.IoC.impl.spring.parsing.ParsingConstants.*;

public class DependencyElement extends Element {

	public DependencyElement() {
		super(PROPERTY, BEANS_NS);
	}

	public String getName() {
		return getAttributeValue(NAME);
	}
	
	public String getRef() {
		return getAttributeValue(REF);
	}

}
