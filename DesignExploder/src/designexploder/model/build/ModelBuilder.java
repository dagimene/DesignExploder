package designexploder.model.build;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import designexploder.model.Connection;
import designexploder.model.Diagram;
import designexploder.model.Node;
import designexploder.model.build.ModelDataProvider.ConnectionDataProvider;
import designexploder.model.build.ModelDataProvider.NodeDataProvider;
import designexploder.model.build.ModelFactory.ConnectionBuilder;
import designexploder.model.build.ModelFactory.NodeBuilder;
import designexploder.util.IterableIterator;

public class ModelBuilder<N extends Node<C>, C extends Connection> {

	protected Map<String, N> nodes = new HashMap<String, N>();
	protected Set<C> connections = new HashSet<C>();
	protected final ModelFactory<N, C> modelFactory;
	protected final ModelDataProvider modelDataProvider;
	
	public ModelBuilder(ModelDataProvider modelDataProvider, ModelFactory<N, C> modelFactory) {
		this.modelDataProvider = modelDataProvider;
		this.modelFactory = modelFactory;
	}
	
	public Diagram<N, C> buildDiagram() {
		buildAllNodes(modelDataProvider.getNodes());
		buildAllConnections(modelDataProvider.getConnections());
		return createDiagram();
	}
	
	protected Diagram<N, C> createDiagram() {
		return modelFactory.createDiagram(new HashSet<N>(nodes.values()));
	}

	protected <T extends NodeDataProvider> void buildAllNodes(Iterator<T> providers) {
		for (NodeDataProvider nodeData : new IterableIterator<T>(providers)) {
			buildNode(nodeData);
		}
	}

	protected <T extends ConnectionDataProvider> void buildAllConnections(Iterator<T> providers) {
		for (ConnectionDataProvider connectionData : new IterableIterator<T>(providers)) {
			buildConnection(connectionData);
		}
	}
	
	protected void buildNode(NodeDataProvider nodeData) {
		NodeBuilder<N, C> nodeBuilder = modelFactory.createNode();
		loadNodeData(nodeData, nodeBuilder);
		addNode(nodeBuilder.getResult());
	}
	
	protected void buildConnection(ConnectionDataProvider connectionData) {
		ConnectionBuilder<C> connectionBuilder = modelFactory.createConnection();
		loadConnectionData(connectionData, connectionBuilder);
		addConnection(connectionBuilder.getResult());
	}

	protected void loadNodeData(NodeDataProvider nodeData, NodeBuilder<N, C> nodeBuilder) {
		nodeBuilder.setBounds(nodeData.getBounds());
	}
	
	protected void loadConnectionData(ConnectionDataProvider connectionData,
			ConnectionBuilder<C> connectionBuilder) {
		connectionBuilder.setSource(nodes.get(connectionData.getSourceID()));
		connectionBuilder.setTarget(nodes.get(connectionData.getTargetID()));
	}

	protected void addNode(N node) {
		nodes.put(node.getId(), node);
	}

	@SuppressWarnings("unchecked")
	protected void addConnection(C connection) {
		connections.add(connection);
		connection.getSource().addOutflow(connection);
		connection.getTarget().addInflow(connection);
	}
}