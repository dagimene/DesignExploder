package designexploder.editor.graphics;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class TestDraw2D {

	public static Figure createFigure(Display d) {
		Figure contents = new Figure();
		
		d = (d != null ? d : Display.getCurrent());
		
		XYLayout contentsLayout = new XYLayout();
		contents.setLayoutManager(contentsLayout);

		Font classFont = new Font(null, "Arial", 12, SWT.BOLD);
		Label classLabel1 = new Label("Table", new Image(Display.getCurrent(),
				ClassBox.class.getResourceAsStream("../../resources/images/class_obj.gif")));
		classLabel1.setFont(classFont);

		Label classLabel2 = new Label("Column", new Image(d,
				ClassBox.class.getResourceAsStream("../../resources/images/class_obj.gif")));
		classLabel2.setFont(classFont);

		ClassBox classFigure = new ClassBox(classLabel1);
		ClassBox classFigure2 = new ClassBox(classLabel2);

		Label attribute1 = new Label("columns: Column[]", new Image(d,
				ClassBox.class.getResourceAsStream("../../resources/images/field_private_obj.gif")));
		Label attribute2 = new Label("rows: Row[]", new Image(d,
				ClassBox.class.getResourceAsStream("../../resources/images/field_private_obj.gif")));
		Label attribute3 = new Label("columnID: int", new Image(d,
				ClassBox.class.getResourceAsStream("../../resources/images/field_private_obj.gif")));
		Label attribute4 = new Label("items: List", new Image(d,
				ClassBox.class.getResourceAsStream("../../resources/images/field_private_obj.gif")));

		classFigure.getAttributesCompartment().add(attribute1);
		classFigure.getAttributesCompartment().add(attribute2);
		classFigure2.getAttributesCompartment().add(attribute3);
		classFigure2.getAttributesCompartment().add(attribute4);

		Label method1 = new Label("getColumns(): Column[]", new Image(d,
				ClassBox.class.getResourceAsStream("../../resources/images/methpub_obj.gif")));
		Label method2 = new Label("getRows(): Row[]", new Image(d,
				ClassBox.class.getResourceAsStream("../../resources/images/methpub_obj.gif")));
		Label method3 = new Label("getColumnID(): int", new Image(d,
				ClassBox.class.getResourceAsStream("../../resources/images/methpub_obj.gif")));
		Label method4 = new Label("getItems(): List", new Image(d,
				ClassBox.class.getResourceAsStream("../../resources/images/methpub_obj.gif")));

		classFigure.getMethodsCompartment().add(method1);
		classFigure.getMethodsCompartment().add(method2);
		classFigure2.getMethodsCompartment().add(method3);
		classFigure2.getMethodsCompartment().add(method4);

		final Rectangle r1 = new Rectangle(10, 10, -1, -1);
		final Rectangle r2 = new Rectangle(200, 200, -1, -1);
		
		contentsLayout.setConstraint(classFigure, r1);
		contentsLayout.setConstraint(classFigure2, r2);

		/* Creating the connection */
		ConnectionFigure c = new ConnectionFigure(classFigure, classFigure2);

		contents.add(classFigure);
		contents.add(classFigure2);
		contents.add(c);
		return contents;
	}
	
	public static void main(String args[]) {
		new TestDraw2D().run(args);
	}
	
	public void run(String args[]) {
		Display d = new Display();
		final Shell shell = new Shell(d);
		shell.setSize(400, 400);
		shell.setText("UMLClassFigure Test");
		LightweightSystem lws = new LightweightSystem(shell);
		
		lws.setContents(createFigure(d));
		shell.open();
		while (!shell.isDisposed())
			while (!d.readAndDispatch())
				d.sleep();
	}
}
