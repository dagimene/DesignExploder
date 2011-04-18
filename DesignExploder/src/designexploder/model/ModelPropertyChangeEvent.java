package designexploder.model;

public class ModelPropertyChangeEvent extends ModelEvent {

	private Object oldValue;
	private Object newValue;

	public ModelPropertyChangeEvent(ModelEventType type, Object oldValue, Object newValue) {
		super(type);
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
