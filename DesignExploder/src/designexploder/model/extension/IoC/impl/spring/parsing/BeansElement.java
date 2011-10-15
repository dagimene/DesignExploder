package designexploder.model.extension.IoC.impl.spring.parsing;

import java.util.Iterator;

import nu.xom.Attribute;
import nu.xom.Element;

import static designexploder.model.extension.IoC.impl.spring.parsing.ParsingConstants.*;

public class BeansElement extends Element implements Iterable<BeanElement> {

	public BeansElement() {
		super(BEANS, BEANS_NS);
        Attribute attribute = new Attribute("schemaLocation", "http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-2.0.xsd");
        attribute.setNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");
        addAttribute(attribute);
	}

	@Override
	public Iterator<BeanElement> iterator() {
		return new ElementIterator<BeanElement>(this.getChildElements(), BeanElement.class);
	}
	
}
