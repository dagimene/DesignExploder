package designexploder.model.extension.IoC;

import designexploder.model.event.ModelEventTrigger;
import designexploder.model.extension.classnode.InmutableNamed;
import designexploder.model.extension.common.InmutableNaturalized;

public interface BeanInjection extends InmutableNamed, InmutableNaturalized, ModelEventTrigger {
	
	Dependency getDependency();
	
	void setDependency(Dependency dependency);
	
}
