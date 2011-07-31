package designexploder.util.adt;

import java.util.Iterator;

public class CastIterator<T> implements Iterator<T> {

	private Iterator<?> inner;

	public CastIterator(Iterator<?> inner) {
		this.inner = inner;
	}

	public boolean hasNext() {
		return inner.hasNext();
	}

	@SuppressWarnings("unchecked")
	public T next() {
		return (T) inner.next();
	}

	public void remove() {
		inner.remove();
	}
	
	public static <T> Iterable<T> createIterable(Iterator<?> inner) {
		return new IterableIterator<T>(new CastIterator<T>(inner));
	}
}
