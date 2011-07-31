package designexploder.util.adt;

import java.util.Iterator;

public class FilteredIterator<T> extends EarlyNextIterator<T> {

	private final Condition<T> condition;
	private final Iterator<T> inner;
	
	public FilteredIterator(Iterator<T> inner, Condition<T> condition) {
		this.inner = inner;
		this.condition = condition;
		init();
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	@Override
	protected T findNext() {
		T next;
		do {
			next = null;
		} while(inner.hasNext() && !condition.check(next = inner.next()));
		return next;
	}
	
}
