package designexploder.model.extension.classnode.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import designexploder.model.extension.classnode.Parameter;
import designexploder.model.extension.classnode.Parameterized;
import designexploder.model.extension.classnode.Type;

class ParameterizedImpl extends ModifiableImpl implements Parameterized {
	
	private List<Parameter> parameters;
	
	protected ParameterizedImpl(Type type) {
		super(type);
		parameters = new ArrayList<Parameter>(10);
	}

	@Override
	public List<Parameter> getParameters() {
		return Collections.unmodifiableList(parameters);
	}

	@Override
	public void addParameter(Parameter parameter) {
		parameters.add(parameter);
	}

	@Override
	public void removeParameter(Parameter parameter) {
		parameters.remove(parameter);
	}
}
