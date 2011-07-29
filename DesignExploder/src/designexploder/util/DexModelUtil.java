package designexploder.util;

import designexploder.model.Node;
import designexploder.model.NodeContainer;

public class DexModelUtil {

	/**
	 * Removes node from current parent, updates node container, and adds it to new container.
	 * @param node
	 * @param container
	 */
	public static void reparentNode(Node node, NodeContainer container) {
		node.getNodeContainer().removeNode(node);
		node.setNodeContainer(container);
		((NodeContainer) container).addNode(node);
	}

}
