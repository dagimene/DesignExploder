package designexploder.model.classnode.impl;

import designexploder.model.classnode.Attribute;
import designexploder.model.classnode.ClassConnection;
import designexploder.model.classnode.ClassNode;
import designexploder.model.classnode.DexConstant;
import designexploder.model.classnode.Method;
import designexploder.model.classnode.Type;
import designexploder.model.classnode.build.ClassNodeModelFactory;
import designexploder.model.impl.ModelFactoryImpl;

public class ClassNodeModelFactoryImpl<N extends ClassNode<C>, C extends ClassConnection> extends ModelFactoryImpl<N, C> implements ClassNodeModelFactory<N, C> {
	@Override
	public ClassConnectionBuilder<C> createConnection() {
		return new ClassConnectionModelBuilderImpl();
	}

	public class ClassConnectionModelBuilderImpl extends ConnectionBuilderImpl implements ClassConnectionBuilder<C> {
		public void setNature(DexConstant nature) {
			getResult().setNature(nature);
		}

		@Override
		public void setSourceCardinality(int cardinality) {
			getResult().setSourceCardinality(cardinality);
		}

		@Override
		public void setTargetCardinality(int cardinality) {
			getResult().setTargetCardinality(cardinality);
		}

		@Override
		public void setName(String name) {
			getResult().setName(name);
		}

		@Override
		@SuppressWarnings("unchecked")
		protected C instantiate() {
			return (C) new ClassConnectionImpl();
		}
	}
	
	protected Method createMethod(String name, Type type) {
		return new MethodImpl(name, type);
	}

	protected Attribute createAttribute(String name, Type type) {
		return new AttributeImpl(name, type);
	}
}
