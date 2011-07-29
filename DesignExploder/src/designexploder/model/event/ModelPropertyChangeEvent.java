package designexploder.model.event;


public class ModelPropertyChangeEvent extends ModelEvent {

	private Object oldValue;
	private Object newValue;

	public ModelPropertyChangeEvent(ModelEventType type, ModelEventTrigger source, Object oldValue, Object newValue) {
		super(type, source);
		this.oldValue = oldValue;
		this.newValue = newValue;
	}

	public Object getOldValue() {
		return oldValue;
	}

	public Object getNewValue() {
		return newValue;
	}

	@Override
	public String toString() {
		return "[ModelPropertyChangeEvent. "+super.toString()+", oldValue: "+oldValue+", newValue: "+newValue+"]";
	}
}
