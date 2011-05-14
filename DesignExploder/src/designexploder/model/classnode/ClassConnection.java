package designexploder.model.classnode;

import designexploder.model.Connection;

public interface ClassConnection extends Connection, Named, Naturalized {
	
	int getSourceCardinality();
	
	int getTargetCardinality();

	void setSourceCardinality(int cardinality);
	
	void setTargetCardinality(int cardinality);
	
}
