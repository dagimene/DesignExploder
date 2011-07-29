package designexploder.model.extension.classnode;

import designexploder.model.extension.common.Named;
import designexploder.model.extension.common.Naturalized;

public interface ClassConnection extends Named, Naturalized {
	
	int getSourceCardinality();
	
	int getTargetCardinality();

	void setSourceCardinality(int cardinality);
	
	void setTargetCardinality(int cardinality);
	
}
