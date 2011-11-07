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
			case REF:
				result = new RefElement();
				break;
			case SET:
				result = new CollectionElement(false);
				break;
			case LIST:
				result = new CollectionElement(true);
				break;
            case MAP:
            case ENTRY:
            case CONSTRUCTOR_ARG:
                result = new Element(name, BEANS_NS);
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

	public SpringConfigFile createSpringConfigFile(BeansElement beans) {
		SpringConfigFile configFile = new SpringConfigFile();
		configFile.setRootElement(beans);
		return configFile;
	}
	
}
