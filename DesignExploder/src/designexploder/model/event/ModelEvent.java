package designexploder.model.event;

public class ModelEvent implements Cloneable {

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

	@Override
	public ModelEvent clone() {
		try {
			return (ModelEvent) super.clone();
		} catch (CloneNotSupportedException ignore) {}
		return null;
	}	

}
