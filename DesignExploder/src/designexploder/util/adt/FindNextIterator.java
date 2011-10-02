package designexploder.util.adt;

import java.util.Iterator;

public abstract class FindNextIterator<T> implements Iterator<T> {

	private T next;
	
	private boolean searchPerformed;

	@Override
	public boolean hasNext() {
		ensureSearchPerformed();
		return next != null;
	}

	private void ensureSearchPerformed() {
		if(!searchPerformed) {
			next = findNext();
			searchPerformed = true;
		}
	}

	@Override
	public T next() {
		ensureSearchPerformed();
		searchPerformed = false;
		return next;
	}

	protected abstract T findNext();

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}
}
