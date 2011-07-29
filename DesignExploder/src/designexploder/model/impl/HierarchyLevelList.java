package designexploder.model.impl;

import java.util.AbstractList;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

import designexploder.model.ContainerNode;
import designexploder.model.Node;

class HierarchyLevelList extends AbstractList<Node> {

	private final List<Node> leafs = new ArrayList<Node>();
	private final List<ContainerNodeImpl> containers = new ArrayList<ContainerNodeImpl>();

	@Override
	public Node get(int index) {
		return index < leafs.size() ? leafs.get(index) : containers.get(index - leafs.size());
	}
	
	@Override
	public boolean add(Node node) {
		if(node instanceof ContainerNode) {
			return containers.add((ContainerNodeImpl) node);
		}
		return leafs.add(node);
	}

	@Override
	public Node remove(int index) {
		return index < leafs.size() ? leafs.remove(index) : containers.remove(index - leafs.size());
	}

	@Override
	public int size() {
		return leafs.size() + containers.size();
	}

	public Iterator<Node> deepIterator() {
		return new DeepIterator();
	}
	
	class DeepIterator implements Iterator<Node> {

		private int cursor = 0;
		private Iterator<Node> deep;
		private Queue<Iterator<Node>> queue = new ArrayDeque<Iterator<Node>>();

		@Override
		public boolean hasNext() {
			return cursor != size() || deep != null || !queue.isEmpty();
		}

		@Override
		/**
		 * @throws NPE if there is there are no more element.
		 */
		public Node next() {
			Node result = null;
			if(cursor != size()) {
				if(cursor < leafs.size()) {
					result = leafs.get(cursor++);
				} else {
					result = containers.get(cursor++ - leafs.size());
					Iterator<Node> deepIterator = ((ContainerNodeImpl)result).getDeepIterator();
					if(deepIterator.hasNext()) {
						queue.add(deepIterator);
					}
				}
			} else {
				if(deep == null) {
					deep = queue.poll();
				}
				result = deep.next();
				if(!deep.hasNext()) {
					deep = null;
				}
			}
			return result;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
}
