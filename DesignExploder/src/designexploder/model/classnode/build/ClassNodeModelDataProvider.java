package designexploder.model.classnode.build;

import java.util.Iterator;

import designexploder.model.build.ModelDataProvider;
import designexploder.model.classnode.DexConstant;

public interface ClassNodeModelDataProvider extends ModelDataProvider {
	
	Iterator<? extends ClassConnectionDataProvider> getConnections();

	public interface ClassConnectionDataProvider extends ConnectionDataProvider { 
		
		DexConstant getNature();

		String getName();
		
		int getSourceCardinality();
		
		int getTargetCardinality();
		
	}
}
