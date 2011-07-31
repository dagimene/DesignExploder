package designexploder.editor.renderers.extension.IoC;

import designexploder.editor.renderers.ConnectionRendererDecorator;
import designexploder.model.Connection;
import designexploder.model.extension.IoC.BeanInjection;
import designexploder.model.extension.common.Nature;

public class BeanInjectionDecorator implements ConnectionRendererDecorator {

	@Override
	public String getConnectionLabel(Connection connection) {
		BeanInjection injection = connection.getExtension(BeanInjection.class);
		return injection != null ? injection.getName() : null;
	}

	@Override
	public Nature getConnectionNature(Connection connection) {
		BeanInjection injection = connection.getExtension(BeanInjection.class);
		return injection != null ? injection.getNature() : null;
	}

}
