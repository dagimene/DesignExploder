package designexploder.editor.renderers.extension.IoC;

import designexploder.editor.renderers.ConnectionRendererDecorator;
import designexploder.model.Connection;
import designexploder.model.extension.IoC.IoCInstantiation;
import designexploder.model.extension.classnode.*;
import designexploder.model.extension.common.Nature;

public class BeanInstantiationDecorator implements ConnectionRendererDecorator {

	@Override
	public String getConnectionLabel(Connection connection) {
		IoCInstantiation instantiation = connection.getExtension(IoCInstantiation.class);
		return instantiation != null ? instantiation.getName() : null;
	}

	@Override
	public Nature getConnectionMainNature(Connection connection) {
		Nature result = null;
		IoCInstantiation instantiation = connection.getExtension(IoCInstantiation.class);
		if(instantiation != null) {
			result = instantiation.getNature();
		}
		return result;
	}

	@Override
	public Nature getConnectionEndpointsNature(Connection connection) {
		Nature result = null;
		IoCInstantiation instantiation = connection.getExtension(IoCInstantiation.class);
		if(instantiation != null) {
            result = ClassModelNatures.ASSOCIATION;
		}
		return result;
	}

}
