package designexploder.model.build;

import java.util.Iterator;

import designexploder.model.Connection;
import designexploder.model.Node;
import designexploder.model.NodeContainer;
import designexploder.model.build.ModelBasicDataProvider.ConnectionBasicDataProvider;
import designexploder.model.build.ModelBasicDataProvider.NodeBasicDataProvider;

public class ModelBasicDataSetter extends BaseModelBuilder {

	private ModelBasicDataProvider provider;

	public ModelBasicDataSetter(ModelBasicDataProvider provider) {
		this.provider = provider;
	}

	@Override
	public NodeContainer build(NodeContainer diagram) {
		return setPresets(super.build(diagram));
	}

	private NodeContainer setPresets(NodeContainer diagram) {
		Iterator<? extends NodeBasicDataProvider> nodesIterator = provider.getNodes();
		while(nodesIterator.hasNext()) {
			NodeBasicDataProvider data = nodesIterator.next();
			Node node = super.findNode(data.getId());
			if(node != null) {
				data.setBasicData(node, diagram);
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
		return diagram;
	}

}