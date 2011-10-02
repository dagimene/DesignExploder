package designexploder.model.extension.IoC.impl.spring.parsing;

import nu.xom.Element;

import static designexploder.model.extension.IoC.impl.spring.parsing.ParsingConstants.*;

public class CollectionElement extends Element {

	public CollectionElement(boolean ordered) {
		super(ordered ? LIST : SET, BEANS_NS);
	}

}
