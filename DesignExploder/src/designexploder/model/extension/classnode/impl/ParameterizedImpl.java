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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ParameterizedImpl that = (ParameterizedImpl) o;

        return !(parameters != null ? !parameters.equals(that.parameters) : that.parameters != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (parameters != null ? parameters.hashCode() : 0);
        return result;
    }
}
