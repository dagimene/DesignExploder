package designexploder.model.classnode.build;

import designexploder.model.build.ModelFactory;
import designexploder.model.classnode.ClassConnection;
import designexploder.model.classnode.ClassNode;
import designexploder.model.classnode.DexConstant;

public interface ClassNodeModelFactory<N extends ClassNode<C>, C extends ClassConnection> extends ModelFactory<N, C> {

	ClassConnectionBuilder<C> createConnection();
	
	public interface ClassConnectionBuilder<C extends ClassConnection> extends ConnectionBuilder<C> {
		void setNature(DexConstant nature);
		
		void setSourceCardinality(int cardinality);
		
		void setTargetCardinality(int cardinality);
		
		void setName(String name);
	}
}
