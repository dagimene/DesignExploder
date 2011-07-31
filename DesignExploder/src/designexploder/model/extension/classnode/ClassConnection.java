package designexploder.model.extension.classnode;

import designexploder.model.extension.common.Naturalized;

public interface ClassConnection extends InmutableNamed, Naturalized {
	
	int getSourceCardinality();
	
	int getTargetCardinality();

	void setSourceCardinality(int cardinality);
	
	void setTargetCardinality(int cardinality);
	
	Attribute getOrigin();
	
	void setOrigin(Attribute origin);
	
}
