package designexploder.model.extension.common;

import designexploder.model.event.ModelEventTrigger;


public interface Naturalized extends InmutableNaturalized, ModelEventTrigger {

	void setNature(Nature nature);
	
}
