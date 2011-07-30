package designexploder.util.adt;

public interface Mapper<U, V> {
	
	V map(U element);

}
