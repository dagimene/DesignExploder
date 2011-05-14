package designexploder.model.classnode.impl.eclipse.jdt;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jdt.core.IType;

import designexploder.model.build.ModelDataProvider.NodeDataProvider;

public class JDTNodeDataProvider implements NodeDataProvider {
	private final IType type;

	JDTNodeDataProvider(IType type) {
		this.type = type;
	}
	
	@Override
	public Rectangle getBounds() {
		return new Rectangle(0, 0, -1, -1);
	}
	
	public IType getIType() {
		return type;
	}
}