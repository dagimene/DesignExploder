package designexploder.editor.graphics;

import org.eclipse.draw2d.ConnectionEndpointLocator;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.MidpointLocator;
import org.eclipse.swt.graphics.Image;


/**
 * Adds support for additional ClassConnection's name and cardinality
 */
public class ClassConnectionFigure extends ConnectionFigure {

	public static final int CONNECTION = 0;
	public static final int SOURCE = 1;
	public static final int TARGET = 2;

	private Label connectionLabel;
	private Label souceLabel;
	private Label targetLabel;
	
	public ClassConnectionFigure() {
		super();
		addLabels();
	}

	public ClassConnectionFigure(ClassFigure source, ClassFigure target) {
		super(source, target);
		addLabels();
	}
	
	private void addLabels() {
		connectionLabel = new Label();
		add(connectionLabel, new MidpointLocator(this, 0));
		souceLabel = new Label();
		add(souceLabel, new ConnectionEndpointLocator(this, false));
		targetLabel = new Label();
		add(targetLabel, new ConnectionEndpointLocator(this, true));
	}
	
	public void setLabelIcon(int position, Image icon) {
		getLabelFor(position).setIcon(icon);
	}

	public void setLabelText(int position, String text) {
		getLabelFor(position).setText(text);
	}
	
	private Label getLabelFor(int position) {
		switch(position) {
		case 0:
			return connectionLabel;
		case 1:
			return souceLabel;
		case 2:
			return targetLabel;
		}
		throw new IllegalArgumentException("No label component for given constant");
	}
}
