package designexploder.model;

public class ModelEvent {

	private ModelEventType type;

	public ModelEvent(ModelEventType type) {
		this.type = type;
	}

	public ModelEventType getType() {
		return type;
	}

	@Override
	public String toString() {
		return "[ModelEvent. Type: "+type+"]";
	}
}
