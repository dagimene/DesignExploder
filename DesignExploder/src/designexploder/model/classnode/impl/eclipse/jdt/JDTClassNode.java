package designexploder.model.classnode.impl.eclipse.jdt;

import designexploder.model.classnode.ClassConnection;
import designexploder.model.classnode.ClassNode;
import designexploder.model.classnode.impl.ClassNodeImpl;

public class JDTClassNode extends ClassNodeImpl<ClassConnection> implements ClassNode<ClassConnection> {

	public JDTClassNode(JDTClassType type) {
		super(type);
		setNature(getType().getNature());
	}

	@Override
	public JDTClassType getType() {
		return (JDTClassType) super.getType();
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + " [ "+getType().toString()+" ]";
	}
}
