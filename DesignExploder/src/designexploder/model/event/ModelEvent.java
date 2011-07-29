package designexploder.model.event;

public class ModelEvent {

	private ModelEventType type;
	private ModelEventTrigger source;

	public ModelEvent(ModelEventType type, ModelEventTrigger source) {
		this.type = type;
		this.source = source;
	}

	public ModelEventType getType() {
		return type;
	}

	public ModelEventTrigger getSource() {
		return source;
	}

	public void setSource(ModelEventTrigger source) {
		this.source = source;
	}

	@Override
	public String toString() {
		return "[ModelEvent. Type: "+type+"]";
	}
}
