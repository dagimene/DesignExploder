package designexploder.model.extension.IoC.impl.spring.parsing;

import java.util.Iterator;

import nu.xom.Attribute;
import nu.xom.Element;

import static designexploder.model.extension.IoC.impl.spring.parsing.ParsingConstants.*;

public class BeanElement extends Element {

    private String id;
    private String name;

	public BeanElement() {
		super(BEAN, BEANS_NS);
	}

	public String getClazz() {
		return getAttributeValue(CLASS);
	}
	
	public String getName() {
        parseName();
        return name;
	}

	public String getId() {
        parseName();
		return id;
	}

    public void setName(String name) {
		this.name = name;
        buildName();
	}
	
	public void setId(String id) {
        this.id = id;
        buildName();
	}

    public void setClazz(String clazz) {
		addAttribute(new Attribute(CLASS, clazz));
	}

    public String getAutowire() {
        return getAttributeValue(AUTOWIRE);
    }

    public void setAutowire(String autowire) {
        addAttribute(new Attribute(AUTOWIRE, autowire));
    }

    public void setAutowireByType() {
        setAutowire(BY_TYPE);
    }

    private void parseName() {
        String name = getAttributeValue(NAME);
        if(name != null) {
            int whitespaceIx = name.indexOf(' ');
            if(whitespaceIx != -1) {
                this.id = name.substring(0, whitespaceIx);
                this.name = name.substring(whitespaceIx + 1);
            } else {
                this.id = name;
                this.name = null;
            }
        }
    }

    private void buildName() {
        if(id != null) {
            if(name == null || id.equals(name)) {
                addAttribute(new Attribute(NAME, id));
            } else {
                addAttribute(new Attribute(NAME, id + " " + name));
            }
        }
    }

    public Iterator<DependencyElement> getDependencies() {
        return new ElementIterator<DependencyElement>(this.getChildElements(), DependencyElement.class);
    }
}