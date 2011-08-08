package designexploder.util.adt;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ADTUtil {

	public static <T> Set<T> createSet(Iterator<T> iterator) {
		Set<T> result = new HashSet<T>();
		while(iterator.hasNext()) {
			result.add(iterator.next());
		}
		return result;
	}

	public static <T> boolean forAll(Iterator<T> iterator, Condition<T> condition) {
		boolean result = true;
		while(result && iterator.hasNext()) {
			result = condition.check(iterator.next());
		}
		return result;
	}

	public static <T> Set<T> getIntersection(final Set<T> aSet,
			final Set<? extends T> otherSet) {
		Set<T> result = ADTUtil.createSet(aSet.iterator());
		result.retainAll(otherSet);
		return result;
	}

	public static <E> Set<E> filterCollection(Iterable<E> elements, Condition<E> condition) {
		return createSet(new FilteredIterator<E>(elements.iterator(), condition));
	}
	
}
