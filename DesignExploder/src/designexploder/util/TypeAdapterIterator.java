package designexploder.util;

import java.util.Iterator;

public abstract class TypeAdapterIterator<U, V> implements Iterator<V> {

	private Iterator<U> inner;
	
	public TypeAdapterIterator(Iterator<U> inner) {
		this.inner = inner;
	}

	@Override
	public boolean hasNext() {
		return inner.hasNext();
	}

	@Override
	public V next() {
		return adapt(inner.next());
	}

	@Override
	public void remove() {
		inner.remove();
	}
	
	protected abstract V adapt(U element);

}
