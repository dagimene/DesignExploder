package designexploder.model.build;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import designexploder.model.Node;
import designexploder.util.adt.IdUtil;

public abstract class IdAwareModelBuilder extends BaseModelBuilder {

	/**
	 * Yet another map for faster node lookup
	 */
	private Map<String, Set<Node>> nodesFromName = new HashMap<String, Set<Node>>();
	
	@Override
	protected void registerNode(Node node) {
		super.registerNode(node);
		String name = IdUtil.parseId(node.getId()).name;
		Set<Node> set = nodesFromName.get(name);
		if(set == null) {
			set = new HashSet<Node>();
			nodesFromName.put(name, set);
		}
		set.add(node);
	}
	
	@Override
	protected void unregisterNode(Node node) {
		super.unregisterNode(node);
		String name = IdUtil.parseId(node.getId()).name;
		Set<Node> set = nodesFromName.get(name);
		if(set != null) {
			set.remove(node);
			if(set.isEmpty()) {
				nodesFromName.remove(name);
			}
		}
	}

	protected Set<Node> findNodesFromIdName(String idName) {
		Set<Node> set = nodesFromName.get(idName);
		return set != null ? set : Collections.<Node>emptySet();
	}
}