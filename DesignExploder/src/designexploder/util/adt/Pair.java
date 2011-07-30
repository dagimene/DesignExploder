package designexploder.util.adt;

public class Pair<P, Q> {

	private P first;
	private Q second;
	
	public Pair() {}
	
	public Pair(P first, Q second) {
		super();
		this.first = first;
		this.second = second;
	}

	public P getFirst() {
		return first;
	}
	public void setFirst(P first) {
		this.first = first;
	}
	public Q getSecond() {
		return second;
	}
	public void setSecond(Q second) {
		this.second = second;
	}

	@Override
	public String toString() {
		return "Pair [" + first.toString() + ", " + second.toString() + "]";
	}
}
