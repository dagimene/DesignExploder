package designexploder.model.build;

import java.util.ArrayList;
import java.util.List;

import designexploder.model.NodeContainer;

public class ChainedModelBuilder implements ModelBuilder {

	private List<ModelBuilder> chain = new ArrayList<ModelBuilder>();
	
	public ChainedModelBuilder addBuilder(ModelBuilder builder) {
		chain.add(builder);
		return this;
	}
	
	public NodeContainer build(NodeContainer diagram) {
		for (ModelBuilder builder : chain) {
			diagram = builder.build(diagram);
		}
		return diagram;
	}
	
}
