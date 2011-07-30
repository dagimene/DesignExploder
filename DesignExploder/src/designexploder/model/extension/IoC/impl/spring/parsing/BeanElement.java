package designexploder.model.extension.IoC.impl.spring.parsing;

import java.util.Iterator;

import nu.xom.Element;

import static designexploder.model.extension.IoC.impl.spring.parsing.ParsingConstants.*;

public class BeanElement extends Element {

	public BeanElement() {
		super(BEAN, BEANS_NS);
	}

	public String getClazz() {
		return getAttributeValue(CLASS);
	}
	
	public String getName() {
		return getAttributeValue(NAME);
	}
	
	public String getId() {
		return getAttributeValue(ID);
	}
	
	public Iterator<DependencyElement> getDependencies() {
		return new ElementIterator<DependencyElement>(this.getChildElements(), DependencyElement.class);
	}
}
