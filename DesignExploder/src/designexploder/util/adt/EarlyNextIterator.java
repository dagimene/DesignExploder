package designexploder.util.adt;

import java.util.Iterator;

public abstract class EarlyNextIterator<T> implements Iterator<T> {

	private T next;
	
	@Override
	public boolean hasNext() {
		return next != null;
	}

	@Override
	public T next() {
		T next = this.next;
		this.next = findNext();
		return next;
	}
	
	protected void init() {
		next = findNext();
	}

	protected abstract T findNext();

}
