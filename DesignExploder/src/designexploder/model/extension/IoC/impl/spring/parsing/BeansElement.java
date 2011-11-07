package designexploder.model.extension.IoC.impl.spring.parsing;

import java.util.Iterator;

import nu.xom.Attribute;
import nu.xom.Element;

import static designexploder.model.extension.IoC.impl.spring.parsing.ParsingConstants.*;

public class BeansElement extends Element implements Iterable<BeanElement> {

	public BeansElement() {
		super(BEANS, BEANS_NS);
        addNamespaceDeclaration(AOP_PREFIX, AOP_NS);
        Attribute attribute = new Attribute(SCHEMA_LOCATION, BEANS_SCHEMA);
        attribute.setNamespace(XSI_PREFIX, XSI_NS);
        addAttribute(attribute);
	}

	@Override
	public Iterator<BeanElement> iterator() {
		return new ElementIterator<BeanElement>(this.getChildElements(), BeanElement.class);
	}
	
}
