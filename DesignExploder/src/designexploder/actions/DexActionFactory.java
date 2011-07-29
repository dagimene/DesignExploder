package designexploder.actions;

public class DexActionFactory {

	private String id;
	
	public static final DexActionFactory CREATE_APPLICATION_CONTEXT = new DexActionFactory("createApplicationContext");

	public DexActionFactory(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
	
}
