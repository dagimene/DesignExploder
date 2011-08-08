package designexploder.model;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import designexploder.util.adt.ADTUtil;
import designexploder.util.adt.CastIterator;
import designexploder.util.adt.Condition;
import designexploder.util.adt.FilteredIterator;
import designexploder.util.adt.IdUtil;
import designexploder.util.adt.IdUtil.ID;
import designexploder.util.adt.IterableIterator;

public class BasicModelUtil {

	/**
	 * Removes node from current parent, updates node container, and adds it to new container.
	 * @param node
	 * @param newContainer
	 */
	public static void reparentNode(Node node, NodeContainer newContainer) {
		node.getNodeContainer().removeNode(node);
		node.setNodeContainer(newContainer);
		((NodeContainer) newContainer).addNode(node);
	}

	/**
	 * Removes node from current parent, removing the node connections from the opposite endpoint also.
	 * Prune container's child connection going outside the node.
	 * @param node
	 */
	public static void removeNode(Node node) {
		if(node instanceof ContainerNode) {
			pruneConnections((ContainerNode) node);
		} else {
			pruneConnections(node, Collections.singleton(node));
		}
		node.getNodeContainer().removeNode(node);
	}
	
	public static void removeConnection(Connection connection) { 
		connection.getSource().removeOutflow(connection);
		connection.getTarget().removeInflow(connection);
	}

	private static void pruneConnections(ContainerNode container) {
		Set<Node> sourceSet = new HashSet<Node>();
		sourceSet.add(container);
		for (Node child : new IterableIterator<Node>(container.getDeepIterator())) {
			sourceSet.add(child);
		}
		for (Node child : new IterableIterator<Node>(container.getDeepIterator())) {
			pruneConnections(child, sourceSet);
		}		
	}

	private static void pruneConnections(Node node, Set<Node> sourceSet) {
		for (Connection connection : node.getInflows()) {
			Node endpoint = connection.getSource();
			if(!sourceSet.contains(endpoint)) {
				endpoint.removeOutflow(connection);
			}
		}
		for (Connection connection : node.getOutflows()) {
			Node endpoint = connection.getTarget();
			if(!sourceSet.contains(endpoint)) {
				endpoint.removeInflow(connection);
			}
		}
	}

	/**
	 * Adds node to node's parent, adding node connections to the opposite endpoint also.
	 * @param node
	 */
	public static void addNode(Node node) {
		bloomConnections(node);
		node.getNodeContainer().addNode(node);
	}
	
	private static void bloomConnections(Node node) {
		for (Connection connection : node.getInflows()) {
			Node endpoint = connection.getSource();
			if(!endpoint.getOutflows().contains(connection)) {
				endpoint.addOutflow(connection);
			}
		}
		for (Connection connection : node.getOutflows()) {
			Node endpoint = connection.getTarget();
			if(!endpoint.getInflows().contains(connection)) {
				endpoint.addInflow(connection);
			}
		}
		if(node instanceof NodeContainer) {
			for (Node child : ((NodeContainer) node).getNodes()) {
				bloomConnections(child);
			}
		}
	}

	/**
	 * Adds connection to source and target nodes.
	 * @param connection
	 */
	public static void addConnection(Connection connection) {
		connection.getSource().addOutflow(connection);
		connection.getTarget().addInflow(connection);
	}
	
	public static NodeContainer findModelRoot(Node node) {
		NodeContainer root = node.getNodeContainer();
		while(root instanceof Node && ((Node) root).getNodeContainer() != null) {
			root = ((Node) root).getNodeContainer();
		}
		return root;
	}

	/**
	 * Creates a unique id with same type and name as the id of given node.
	 * @param node
	 * @return
	 */
	public static String nextIdLike(Node node) {
		ID baseId = IdUtil.parseId(node.getId());
		return nextId(node, baseId.type, baseId.name);
	}
	
	public static String nextClassIdLike(Node node) {
		return nextId(node, IdUtil.CLASS_TYPE, IdUtil.parseId(node.getId()).name);
	}

	public static String nextIdForConnection(Node node, Node target) {
		return nextId(node, IdUtil.CONNECTION_TYPE, IdUtil.createConnectionId(node.getId(), target.getId()).name);
	}

	private static String nextId(Node someNode, String type, String name) {
		NodeContainer root = findModelRoot(someNode);
		String id = null;
		for(Iterator<String> iterator = IdUtil.getIdsIterator(type, name);
			root.findNode(id = iterator.next()) != null;) ;
		return id;
	}

	public static Set<Node> findNodesByIdName(NodeContainer container, String idName) {
		Set<Node> result = new HashSet<Node>();
		idName = idName.intern();
		for(Node node : new IterableIterator<Node>(container.getDeepIterator())) {
			if(IdUtil.parseId(node.getId()).name == idName) {
				result.add(node);
			}
		}
		return result;
	}

	public static Iterable<ContainerNode> getContainerNodes(NodeContainer container) {
		return classFilterIterator(container.getNodes().iterator(), ContainerNode.class);
	}

	public static Iterable<Node> getExtendedNodes(NodeContainer container, Class<? extends ModelExtension> extension) {
		return getExtendedModels(container.getNodes(), extension);
	}

	public static <M extends ExtensibleModelElement> Iterable<M> getExtendedModels(Collection<M> models, Class<? extends ModelExtension> extension) {
		return extensionFilterIterator(models.iterator(), extension);
	}
	
	private static <M extends ExtensibleModelElement> Iterable<M> extensionFilterIterator(Iterator<M> base, final Class<? extends ModelExtension> extension) {
		return new IterableIterator<M>(
					new FilteredIterator<M>(base,
							new Condition<M>() {
								@Override
								public boolean check(M model) {
									return model.getExtension(extension) != null;
								}
							}));
	}

	private static <T, S> Iterable<T> classFilterIterator(Iterator<S> base, final Class<T> clazz) {
		return new IterableIterator<T>(
				new CastIterator<T>( 
						new FilteredIterator<S>(base,
								new Condition<S>() {
									@Override
									public boolean check(S node) {
										return clazz.isAssignableFrom(node.getClass());
									}
								})));
	}

	public static Set<Connection> findConnections(Node source, final Node target) {
		return ADTUtil.createSet(new FilteredIterator<Connection>(source.getOutflows().iterator(),
				new Condition<Connection>() {
					@Override
					public boolean check(Connection connection) {
						return connection.getTarget() == target;
				}}));
	}
}
