package designexploder.editor.renderers.extension.IoC;

import designexploder.editor.renderers.ConnectionRendererDecorator;
import designexploder.model.Connection;
import designexploder.model.extension.IoC.BeanInjection;
import designexploder.model.extension.IoC.IoCModelNatures;
import designexploder.model.extension.IoC.IoCModelUtil;
import designexploder.model.extension.classnode.ClassItem;
import designexploder.model.extension.classnode.ClassModelNatures;
import designexploder.model.extension.classnode.ClassModelUtil;
import designexploder.model.extension.classnode.ClassRelation;
import designexploder.model.extension.classnode.Method;
import designexploder.model.extension.classnode.Type;
import designexploder.model.extension.common.Nature;

public class BeanInjectionDecorator implements ConnectionRendererDecorator {

	@Override
	public String getConnectionLabel(Connection connection) {
		BeanInjection injection = connection.getExtension(BeanInjection.class);
		return injection != null ? injection.getName() : null;
	}

	@Override
	public Nature getConnectionMainNature(Connection connection) {
		Nature result = null;
		BeanInjection injection = connection.getExtension(BeanInjection.class);
		if(injection != null) {
			result = injection.getNature();
		} else {
			ClassRelation classRelation = connection.getExtension(ClassRelation.class);
			if(classRelation != null && classRelation.getOrigin() != null) {
				if(IoCModelUtil.findDependencyFromClassItem(classRelation.getOrigin(), connection.getSource()) != null) {
					result = IoCModelNatures.INDIFFERENT_CONNECTION;
				}
			}
		}
		return result;
	}

	@Override
	public Nature getConnectionEndpointsNature(Connection connection) {
		Nature result = null;
		BeanInjection injection = connection.getExtension(BeanInjection.class);
		if(injection != null) {
			if(injection.getNature() == IoCModelNatures.INJECTION_TREE) {
				result = deduceEndpointsNature(injection);
			} else if(injection.getNature() == IoCModelNatures.INJECTION_COLLECTION) {
				result = ClassModelNatures.COMPOSITION;
			} else {
				result = ClassModelNatures.ASSOCIATION;
			}
		}
		return result;
	}

	public Nature deduceEndpointsNature(BeanInjection injection) {
		ClassItem target = injection.getDependency().getTarget();
		Type type;
		if(target.isAttribute()) {
			type = target.getType();
		} else {
			type = ((Method) target).getParameters().get(0).getType();
		}
		if(type.isArray() || (type.isClassType() && ClassModelUtil.isCollection(type.asClassType()))) {
			return ClassModelNatures.COMPOSITION;
		}
		return ClassModelNatures.ASSOCIATION;
	}

}
