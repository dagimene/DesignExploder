package designexploder.model.event;

import java.util.Collection;


public class ModelCollectionAlterEvent<T> extends ModelEvent {

	private T element;
	private Collection<T> collection;

	public ModelCollectionAlterEvent(ModelEventType type, ModelEventTrigger source, Collection<T> collection, T element) {
		super(type, source);
		this.element = element;
		this.collection = collection;
	}

	public T getElement() {
		return element;
	}

	public Collection<T> getCollection() {
		return collection;
	}

	@Override
	public String toString() {
		return "[ModelCollectionAlterEvent. "+super.toString()+", collection: "+collection+", element: "+element+"]";
	}
}
