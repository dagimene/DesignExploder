package designexploder.model.extension.IoC.impl.spring.parsing;

import static designexploder.model.extension.IoC.impl.spring.parsing.ParsingConstants.*;
import nu.xom.Attribute;
import nu.xom.Element;

public class RefElement extends Element {

	public RefElement() {
		super(REF, BEANS_NS);
	}

	public String getRef() {
		return getAttributeValue(BEAN);
	}
	
	public void setBean(String bean) {
		addAttribute(new Attribute(BEAN, bean));
	}
	
}
