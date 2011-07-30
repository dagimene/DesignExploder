package designexploder.util.adt;

import java.util.Iterator;

import nu.xom.Element;
import nu.xom.ParentNode;

public class XMLDocumentIterator implements Iterator<Element> {

	private int total;
	private int actual = -1;
	private int next;
	private ParentNode node;
	
	public XMLDocumentIterator(ParentNode node) {
		this.node = node;
		total = node.getChildCount();
		findNext();
	}

	private void findNext() {
		while(next < total && !(node.getChild(next) instanceof Element)) {
			next++;
		}
	}

	@Override
	public boolean hasNext() {
		return next < total;
	}

	@Override
	public Element next() {
		Element result = (Element) node.getChild(next);
		actual = next;
		next++;
		findNext();
		return result;
	}

	@Override
	public void remove() {
		node.removeChild(actual);
		next--;
	}	
}
