package designexploder.model.build;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import designexploder.model.BasicModelUtil;
import designexploder.model.Connection;
import designexploder.model.Node;
import designexploder.model.NodeContainer;
import designexploder.model.build.ModelBasicDataProvider.ConnectionBasicDataProvider;
import designexploder.model.build.ModelBasicDataProvider.NodeBasicDataProvider;
import designexploder.model.extension.IoC.IoCModelUtil;
import designexploder.util.adt.IdUtil;
import designexploder.util.adt.IdUtil.ID;

public class ModelBasicDataSetter extends IdAwareModelBuilder {

	private ModelBasicDataProvider provider;
	private Set<Node> declaredNodes = new HashSet<Node>();

	public ModelBasicDataSetter(ModelBasicDataProvider provider) {
		this.provider = provider;
	}

	@Override
	public NodeContainer build(NodeContainer diagram) {
		super.build(diagram);
		setPresets(diagram);
		purge(diagram);
		return diagram;
	}

	private void setPresets(NodeContainer diagram) {
		Iterator<? extends NodeBasicDataProvider> nodesIterator = provider.getNodes();
		while(nodesIterator.hasNext()) {
			NodeBasicDataProvider data = nodesIterator.next();
			ID id = IdUtil.parseId(data.getId());
			if(id != null) {
				Node node = super.findNode(id.toString());
				if(node == null && id.type == IdUtil.CLASS_TYPE && id.number != -1) {
					Node baseNode = super.findNode(IdUtil.createClassId(id.name).toString());
					if(baseNode != null) {
						node = IoCModelUtil.createClassNodeCopy(baseNode, id.toString());
						super.addNode(node);
					}
				}
				if(node != null) {
					// Prevent reparent of beans
					if(id.type == IdUtil.BEAN_TYPE) {
						NodeContainer parent = node.getNodeContainer();
						data.setBasicData(node, diagram);
						if(node.getNodeContainer() != parent) {
							BasicModelUtil.reparentNode(node, parent);
						}
					} else {
						data.setBasicData(node, diagram);
					}
					declaredNodes.add(node);
				}
			}
		}
		Iterator<? extends ConnectionBasicDataProvider> connectionsIterator = provider.getConnections();
		while(connectionsIterator.hasNext()) {
			ConnectionBasicDataProvider data = connectionsIterator.next();
			Node source = findNode(data.getSourceID());
			Node target = findNode(data.getTargetID());
			if(source != null && target != null) {
				for (Connection connection : source.getOutflows()) {
					if(connection.getId().equals(data.getId())) {
						data.setBasicData(connection, source, target, diagram);
						break;
					}
				}
			}
		}
	}

	/**
	 * Find all class nodes that were not declared in the .dex.
	 * If the node has duplicates, remove it.
	 * If the node doesn't have duplicates, leave it.
	 * @param diagram
	 */
	private void purge(NodeContainer diagram) {
		Set<Node> blackList = new HashSet<Node>();
		for(Node node : getAllNodes()) {
			if(!declaredNodes.contains(node)) {
				ID id = IdUtil.parseId(node.getId());
				if(id.type == IdUtil.CLASS_TYPE) {
					Set<Node> duplicates = findNodesFromIdName(id.name);
					if(duplicates.size() > 1) {
						blackList.add(node);
					}
				}
			}
		}
		for (Node node : blackList) {
			super.removeNode(node);
		}
	}

}