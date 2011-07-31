package designexploder.model.extension.classnode.impl;

import designexploder.model.event.ModelEvent;
import designexploder.model.event.ModelEventListener;
import designexploder.model.event.ModelPropertyChangeEvent;
import designexploder.model.extension.classnode.Attribute;
import designexploder.model.extension.classnode.ClassConnection;
import designexploder.model.extension.classnode.ClassItem;
import designexploder.model.extension.classnode.ClassModelEventTypes;
import designexploder.model.extension.common.CommonModelEventTypes;
import designexploder.model.extension.common.impl.NaturalizedImpl;

class ClassConnectionImpl extends NaturalizedImpl implements ClassConnection {

	private int originCardinality;
	private int targetCardinality;
	private Attribute origin;
	
	public ClassConnectionImpl() {
		addListener(ClassModelEventTypes.ORIGIN_CHANGED, selfListener);
	}

	@Override
	public Attribute getOrigin() {
		return origin;
	}

	@Override
	public void setOrigin(Attribute origin) {
		Attribute oldValue = this.origin;
		this.origin = origin;
		fireModelPropertyChangeEvent(ClassModelEventTypes.ORIGIN_CHANGED, oldValue, origin);
	}
	
	public String getName() {
		return origin != null ? origin.getName() : null;
	}
	
	public int getSourceCardinality() {
		return originCardinality;
	}
	
	public void setSourceCardinality(int originCardinality) {
		this.originCardinality = originCardinality;
	}
	
	public int getTargetCardinality() {
		return targetCardinality;
	}
	
	public void setTargetCardinality(int targetCardinality) {
		this.targetCardinality = targetCardinality;
	}
	private final ModelEventListener selfListener = new ModelEventListener() {
		@Override
		public void processModelEvent(ModelEvent e) {
			if(e.getType() == ClassModelEventTypes.ORIGIN_CHANGED) {
				@SuppressWarnings("unchecked")
				ModelPropertyChangeEvent<ClassItem> event = (ModelPropertyChangeEvent<ClassItem>) e;
				if(event.getOldValue() != null) {
					event.getOldValue().removeListener(CommonModelEventTypes.NAME_CHANGED, targetListener);
				}
				if(event.getNewValue() != null) {
					event.getNewValue().addListener(CommonModelEventTypes.NAME_CHANGED, targetListener);
				}
			}
		}
	};

	private ModelEventListener targetListener = new ModelEventListener() {
		/**
		 * Forwards a new NAME_CHANGED event to listeners
		 */
		@Override
		public void processModelEvent(ModelEvent e) {
			ModelEvent fowardEvent = e.clone();
			fowardEvent.setSource(ClassConnectionImpl.this);
			fireEvent(fowardEvent);
		}
	};

}
