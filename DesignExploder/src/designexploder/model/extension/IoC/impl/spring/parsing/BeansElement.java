package designexploder.model.extension.IoC.impl.spring.parsing;

import java.util.Iterator;

import nu.xom.Element;

import static designexploder.model.extension.IoC.impl.spring.parsing.ParsingConstants.*;

public class BeansElement extends Element implements Iterable<BeanElement> {

	public BeansElement() {
		super(BEANS, BEANS_NS);
	}

	@Override
	public Iterator<BeanElement> iterator() {
		return new ElementIterator<BeanElement>(this.getChildElements(), BeanElement.class);
	}
	
}
