package designexploder.model;

import java.util.Iterator;

import designexploder.util.adt.IdUtil;
import designexploder.util.adt.IdUtil.ID;


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
	 * @param node
	 */
	public static void removeNode(Node node) {
		for (Connection connection : node.getInflows()) {
			connection.getSource().removeOutflow(connection);
		}
		for (Connection connection : node.getOutflows()) {
			connection.getTarget().removeInflow(connection);
		}
		node.getNodeContainer().removeNode(node);
	}

	/**
	 * Adds node to node's parent, adding node connections to the opposite endpoint also.
	 * @param node
	 */
	public static void addNode(Node node) {
		for (Connection connection : node.getInflows()) {
			connection.getSource().addOutflow(connection);
		}
		for (Connection connection : node.getOutflows()) {
			connection.getTarget().addInflow(connection);
		}
		node.getNodeContainer().addNode(node);
	}

	/**
	 * Adds connection to source and target nodes.
	 * @param connection
	 */
	public static void addConnection(Connection connection) {
		connection.getSource().addOutflow(connection);
		connection.getTarget().addInflow(connection);
	}
	
	/**
	 * Creates a unique id with same type and name as the id of given node.
	 * @param node
	 * @return
	 */
	public static String nextIdLike(Node node) {
		ID baseId = IdUtil.parseId(node.getId());
		return nextIdLike(node, baseId.type, baseId.name);
	}
	
	public static String nextClassIdLike(Node node) {
		return nextIdLike(node, IdUtil.CLASS_TYPE, IdUtil.parseId(node.getId()).name);
	}

	private static String nextIdLike(Node node, String type, String name) {
		NodeContainer root = node.getNodeContainer();
		while(root instanceof Node && ((Node) root).getNodeContainer() != null) {
			root = ((Node) root).getNodeContainer();
		}
		String id = null;
		for(Iterator<String> iterator = IdUtil.getIdsIterator(type, name);
			root.findNode(id = iterator.next()) != null;) ;
		return id;
	}
}
