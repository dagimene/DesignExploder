package designexploder.model.extension.IoC.impl;

import designexploder.model.extension.IoC.ApplicationContext;
import designexploder.model.extension.common.impl.NamedImpl;

class ApplicationContextImpl extends NamedImpl implements ApplicationContext {

	@Override
	@SuppressWarnings("unchecked")
	public Class<ApplicationContext> getExtensionClass() {
		return ApplicationContext.class;
	}
}
