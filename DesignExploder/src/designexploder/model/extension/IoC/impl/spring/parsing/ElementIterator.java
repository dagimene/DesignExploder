package designexploder.model.extension.IoC.impl.spring.parsing;

import java.util.Iterator;

import nu.xom.Element;
import nu.xom.Elements;

public class ElementIterator<T> implements Iterator<T> {

	private int index = 0;
	private final Elements elements;
	private final Class<T> clazz;
	private T next;
	
	public ElementIterator(Elements elements, Class<T> clazz) {
		this.elements = elements;
		this.clazz = clazz;
		findNext();
	}

	private void findNext() {
		next = null;
		while(next == null && index < elements.size()) {
			Element element = elements.get(index++);
			if(clazz.isAssignableFrom(element.getClass())) {
				next = clazz.cast(element);
			}
		}
	}

	@Override
	public boolean hasNext() {
		return next != null;
	}

	@Override
	public T next() {
		T next = this.next;
		findNext();
		return next; 
	}

	@Override
	public void remove() {
		new UnsupportedOperationException();
	}
}
