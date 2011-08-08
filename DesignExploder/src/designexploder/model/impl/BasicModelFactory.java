package designexploder.model.impl;

import designexploder.model.*;
import designexploder.util.adt.IdUtil;
import designexploder.util.adt.Mapper;

public class BasicModelFactory {

	private final static BasicModelFactory instance = new BasicModelFactory();

	public static BasicModelFactory getInstance() {
		return instance ;
	}
	
	private BasicModelFactory() {}
	
	public Node createNode() {
		return new NodeImpl();
	}
	
	public Connection createConnection() {
		return new ConnectionImpl();
	}
	
	public NodeContainer createNodeContainer() {
		return new NodeContainerImpl();
	}
	
	public ContainerNode createContainerNode() {
		return new ContainerNodeImpl();
	}

	public <T extends ExtensibleModelElement> T createModelCopy(T model, String newId) {
		return createModelCopy(model, newId, false);
	}

	public <T extends ExtensibleModelElement> T createModelCopy(T model, String newId, boolean deepCopyConnections) {
		return createModelCopy(model, newId, deepCopyConnections, false, null);
	}

	@SuppressWarnings("unchecked")
	public <T extends ExtensibleModelElement> T createModelCopy(T model, String newId, boolean deepCopyConnections, boolean deepCopyNodes, Mapper<String, String> idsGenerator) {
		ExtensibleModelElement copy;
		Node newNode = null;
		NodeContainer newContainer = null;
		Connection newConnection = null;
		Class<?> clazz = model.getClass();
		if(clazz == NodeImpl.class) {
			copy = newNode = new NodeImpl();
		} else if(clazz == NodeContainerImpl.class) {
			copy = newContainer = new NodeContainerImpl();
		} else if(clazz == ContainerNodeImpl.class) {
			copy = newContainer = new ContainerNodeImpl();
			copy = newNode = (Node) copy;
		} else if(clazz == ConnectionImpl.class) {
			copy = newConnection = new ConnectionImpl();
		} else {
			throw new IllegalArgumentException("Type "+clazz+" is not a recognized model implementation.");
		}
		
		// Copy extensible model element properties
		copy.setId(newId);
		for (@SuppressWarnings("rawtypes") Class extensionType : ((ExtensibleModelElement) model).getExtensions()) {
			copy.addExtension(model.getExtension(extensionType));
		}
		
		// Copy node properties
		if(newNode != null) {
			Node node = (Node) model;
			newNode.setBounds(node.getBounds().getCopy());
			newNode.setResizeable(node.isResizeable());
			newNode.setNodeContainer(node.getNodeContainer());

			// Deep copy connections
			if(deepCopyConnections) {
				for (Connection outflow : node.getOutflows()) {
					Connection newOutflow = createModelCopy(outflow, IdUtil.createConnectionId(newId, outflow.getTarget().getId()).toString());
					newOutflow.setSource(newNode);
					newNode.addOutflow(newOutflow);
				}
				for (Connection inflow : node.getInflows()) {
					Connection newInflow = createModelCopy(inflow, IdUtil.createConnectionId(inflow.getSource().getId(), newId).toString());
					newInflow.setTarget(newNode);
					newNode.addInflow(newInflow);
				}
			}
		}
		
		if(newContainer != null && deepCopyNodes) {
			if(idsGenerator == null) {
				throw new IllegalArgumentException("Ids generator must be provided if flag deepCopyNodes is set.");
			}
			NodeContainer container = (NodeContainer) model;
			for (Node child : container.getNodes()) {
				Node newChild = createModelCopy(child, idsGenerator.map(child.getId()), deepCopyConnections, deepCopyNodes, idsGenerator);
				newChild.setNodeContainer(newContainer);
				newContainer.addNode(newChild);
			}
		}
		
		// Copy connection properties
		if(newConnection != null) {
			Connection connection = (Connection) model;
			newConnection.setSource(connection.getSource());
			newConnection.setTarget(connection.getTarget());
		}
		
		return (T) copy;
	}
}
