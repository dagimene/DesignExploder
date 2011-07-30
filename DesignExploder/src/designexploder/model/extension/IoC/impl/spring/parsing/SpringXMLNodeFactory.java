package designexploder.model.extension.IoC.impl.spring.parsing;

import nu.xom.Document;
import nu.xom.Element;
import nu.xom.NodeFactory;

import static designexploder.model.extension.IoC.impl.spring.parsing.ParsingConstants.*;

public class SpringXMLNodeFactory extends NodeFactory {

	@Override
	public Element startMakingElement(String name, String namespace) {
		Element result = null;
		if(namespace.equals(BEANS_NS)) {
			ParsingElement element = ParsingConstants.valueOf(name);
			switch (element) {
			case BEANS:
				result = new BeansElement();
				break;
			case BEAN:
				result = new BeanElement();
				break;
			case PROPERTY:
				result = new DependencyElement();
				break;
			}
		}
		if(result == null) { 
			result = super.startMakingElement(name, namespace);
		}
		return result;
	}

	@Override
	public Document startMakingDocument() {
		return new SpringConfigFile();
	}

}
