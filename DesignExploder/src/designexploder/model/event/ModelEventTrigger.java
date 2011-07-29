package designexploder.model.event;


public interface ModelEventTrigger {

	void addListener(ModelEventType event, ModelEventListener listener);

	boolean removeListener(ModelEventType event, ModelEventListener listener);

}