package designexploder.actions;

import org.eclipse.gef.requests.CreationFactory;

public class DummyCreationFactory implements CreationFactory {

	private Object newObject;
	private Object objectType;
	
	public DummyCreationFactory(Object newObject, Object objectType) {
		super();
		this.newObject = newObject;
		this.objectType = objectType;
	}

	@Override
	public Object getNewObject() {
		return newObject;
	}

	@Override
	public Object getObjectType() {
		return objectType;
	}

}
