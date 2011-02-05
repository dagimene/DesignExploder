package designexploder.model.classconnection;

import designexploder.model.Connection;
import designexploder.model.Named;
import designexploder.model.Naturalized;

public interface ClassConnection extends Connection, Named, Naturalized {
	
	int getSourceCardinality();
	
	int getTargetCardinality();

	void setOriginCardinality(int cardinality);
	
	void setTargetCardinality(int cardinality);
	
}
