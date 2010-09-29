package designexploder.model;

public class ModelFactory {

	private static ModelFactory INSTANCE;
	private ModelFactory() {}
	public static ModelFactory getFactory() {
		if(INSTANCE == null) {
			INSTANCE = new ModelFactory();
		}
		return INSTANCE; 
	}
	
	public Node createNode() {
		return new NodeImpl();
	}
	
	public Connection createConnection() {
		return new ConnectionImpl();
	}
	
	public Diagram createDiagram() {
		return new DiagramImpl();
	}
}
