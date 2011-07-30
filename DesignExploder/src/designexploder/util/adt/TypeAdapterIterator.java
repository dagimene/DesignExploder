package designexploder.util.adt;

import java.util.Iterator;

public class TypeAdapterIterator<U, V> implements Iterator<V> {

	private Iterator<U> inner;
	private Mapper<U, V> mapper;
	
	public TypeAdapterIterator(Iterator<U> inner, Mapper<U, V> mapper) {
		this.inner = inner;
		this.mapper = mapper;
	}

	@Override
	public boolean hasNext() {
		return inner.hasNext();
	}

	@Override
	public V next() {
		return mapper.map(inner.next());
	}

	@Override
	public void remove() {
		inner.remove();
	}

}
