package designexploder.model.extension.IoC.impl.spring.parsing;

import nu.xom.Attribute;
import nu.xom.Element;

import java.util.Iterator;

import static designexploder.model.extension.IoC.impl.spring.parsing.ParsingConstants.*;

public class ScopedProxyElement extends Element {
	public ScopedProxyElement() {
		super(SCOPED_PROXY, AOP_NS);
        setNamespacePrefix(AOP_PREFIX);
	}
}