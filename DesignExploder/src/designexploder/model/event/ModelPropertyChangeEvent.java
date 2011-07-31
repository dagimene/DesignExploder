package designexploder.model.event;


public class ModelPropertyChangeEvent<T> extends ModelEvent {

	private T oldValue;
	private T newValue;

	public ModelPropertyChangeEvent(ModelEventType type, ModelEventTrigger source, T oldValue, T newValue) {
		super(type, source);
		this.oldValue = oldValue;
		this.newValue = newValue;
	}

	public T getOldValue() {
		return oldValue;
	}

	public T getNewValue() {
		return newValue;
	}

	@Override
	public String toString() {
		return "[ModelPropertyChangeEvent. "+super.toString()+", oldValue: "+oldValue+", newValue: "+newValue+"]";
	}
}
