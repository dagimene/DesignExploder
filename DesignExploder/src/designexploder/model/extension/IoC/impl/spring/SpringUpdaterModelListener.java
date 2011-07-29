package designexploder.model.extension.IoC.impl.spring;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import nu.xom.Attribute;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.ParentNode;
import nu.xom.ParsingException;
import nu.xom.Serializer;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;

import designexploder.model.Node;
import designexploder.model.NodeContainer;
import designexploder.model.event.BasicModelEventTypes;
import designexploder.model.event.ModelCollectionAlterEvent;
import designexploder.model.event.ModelEvent;
import designexploder.model.event.ModelEventListener;
import designexploder.model.event.ModelEventType;
import designexploder.model.extension.IoC.ApplicationContext;
import designexploder.model.extension.classnode.ClassNode;
import designexploder.util.XMLDocumentIterator;

public class SpringUpdaterModelListener implements ModelEventListener {

	private static final String XML_BEANS_SCHEMA = "http://www.springframework.org/schema/beans";

	@Override
	public void processModelEvent(ModelEvent e) {
		if(e instanceof ModelCollectionAlterEvent<?>) {
			Object element = ((ModelCollectionAlterEvent<?>) e).getElement();
			if(element instanceof Node) {
				Node node = (Node) element;
				ClassNode classNode = node.getExtension(ClassNode.class);
				NodeContainer container = node.getNodeContainer();
				ApplicationContext context = container.getExtension(ApplicationContext.class);
				if(classNode != null && context != null) {
					alterContext(node, container, e.getType());
				}
			}
		}
	}

	private void alterContext(Node clazz, NodeContainer context, ModelEventType eventType) {
		IFile file = null;//(IFile) config.getElementResource();
		
		Document document;
		try {
			document = readDocument(file);
			if(document != null) {
				if(eventType == BasicModelEventTypes.NODE_ADDED) {
					addBean(document, clazz.getId());
				} else if(eventType  == BasicModelEventTypes.NODE_REMOVED) {
					removeBean(document, clazz.getId());
				}
				writeDocument(file, document);
			}
		} catch (CoreException e) {
			e.printStackTrace();
		}
		
	}

	private void addBean(Document document, String id) {
		Element bean = new Element("bean", XML_BEANS_SCHEMA);
		bean.addAttribute(new Attribute("id", id));
		bean.addAttribute(new Attribute("class", id));
		((ParentNode) document.getChild(0)).appendChild(bean);
	}

	private void removeBean(Document document, String id) {
		Iterator<Element> iterator = new XMLDocumentIterator((ParentNode) document.getChild(0));
		while(iterator.hasNext()) {
			Element element = iterator.next();
			if(element.getLocalName().equals("bean")) {
				Attribute idAttribute = element.getAttribute("id");
				if(idAttribute != null && idAttribute.getValue().equals(id)) {
					iterator.remove();
					return;
				}
			}
		}
	}

	private void writeDocument(IFile file, Document document) throws CoreException {
		ByteArrayOutputStream pipeOut = new ByteArrayOutputStream();
		Serializer serializer = new Serializer(pipeOut);
		serializer.setIndent(4);
		try {
			serializer.write(document);
		} catch (IOException e) {}
		ByteArrayInputStream pipeIn = new ByteArrayInputStream(pipeOut.toByteArray());
		file.setContents(pipeIn, false, true, null);
	}

	private Document readDocument(IFile file) throws CoreException {
		InputStream contents = null;
		Document document = null; 
		try {
			contents = file.getContents();
			Builder builder = new Builder();
			document = builder.build(contents);
		} catch (ParsingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(contents != null) {
				try {
					contents.close();
				} catch (IOException e) {}
			}
		}
		return document;
	}
}
