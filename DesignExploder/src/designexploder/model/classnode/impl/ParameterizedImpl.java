package designexploder.model.classnode.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import designexploder.model.classnode.Parameter;
import designexploder.model.classnode.Parameterized;
import designexploder.model.classnode.Type;

public class ParameterizedImpl extends ModifiableImpl implements Parameterized {
	
	private List<Parameter> parameters;
	
	protected ParameterizedImpl(Type type) {
		super(type);
		parameters = new ArrayList<Parameter>(10);
	}

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
