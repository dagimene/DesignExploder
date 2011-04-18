package designexploder.model.autolayout;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.geometry.Dimension;

public class AutolayoutLayoutBridge implements LayoutManager {

	private final Autolayot autolayuot;
	private final LayoutManager delegate;

	public AutolayoutLayoutBridge(Autolayot autolayuot, LayoutManager delegate) {
		this.autolayuot = autolayuot;
		this.delegate = delegate;
	}

	public Object getConstraint(IFigure child) {
		System.out.println("get constraint");
		return delegate.getConstraint(child);
	}

	public Dimension getMinimumSize(IFigure container, int wHint, int hHint) {
		System.out.println("Minimun");
		return delegate.getMinimumSize(container, wHint, hHint);
	}

	public Dimension getPreferredSize(IFigure container, int wHint, int hHint) {
		System.out.println("Prefered");
		return delegate.getPreferredSize(container, wHint, hHint);
	}

	public void invalidate() {
		System.out.println("Invalidate");
		delegate.invalidate();
	}

	public void layout(IFigure container) {
		
		System.out.println("Layout");
		delegate.layout(container);
	}

	public void remove(IFigure child) {
		System.out.println("Remove");
		delegate.remove(child);
	}

	public void setConstraint(IFigure child, Object constraint) {
		System.out.println("set constraint: "+constraint);
		delegate.setConstraint(child, constraint);
	}
}
