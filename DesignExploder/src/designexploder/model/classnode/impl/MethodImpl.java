package designexploder.model.classnode.impl;

import java.util.ArrayList;
import java.util.List;

import designexploder.model.classnode.DexConstant;
import designexploder.model.classnode.Method;
import designexploder.model.classnode.Modifiable;
import designexploder.model.classnode.Type;

public class MethodImpl extends AttributeImpl implements Method {
	
	private List<Modifiable> parameters;
	
	public MethodImpl(String name, Type type, DexConstant nature) {
		super(name, type, nature);
		parameters = new ArrayList<Modifiable>(10);
	}

	public List<Modifiable> getParameters() {
		return parameters;
	}

	public void setParameters(List<Modifiable> parameters) {
		this.parameters = parameters;
	}
}
