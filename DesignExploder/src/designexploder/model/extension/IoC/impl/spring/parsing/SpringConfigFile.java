package designexploder.model.extension.IoC.impl.spring.parsing;

import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Node;

public class SpringConfigFile extends Document {

	public SpringConfigFile() {
		super(new Element("root"));
	}
	
	public BeansElement getBeans() {
		Node root = getChild(0);
		return root instanceof BeansElement ? (BeansElement) root : null;
	}
	
}
