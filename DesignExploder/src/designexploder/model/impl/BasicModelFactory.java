package designexploder.model.impl;

import designexploder.model.*;

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
}
