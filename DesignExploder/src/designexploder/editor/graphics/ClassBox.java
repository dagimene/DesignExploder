package designexploder.editor.graphics;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

class ClassBox extends Figure {
	  public static Color classColor = new Color(null,255,255,206);
	  public static Font classFont = new Font(null, "Arial", 12, SWT.BOLD);
	  public static Image classImage = new Image(Display.getCurrent(),
	    		ClassBox.class.getResourceAsStream("../../resources/images/class_obj.gif"));
	  
	  private Label label = new Label(classImage);
	  private MembersSection attributeFigure = new MembersSection();
	  private MembersSection methodFigure = new MembersSection();
	  
	  public ClassBox() {
		setLayoutManager(new ToolbarLayout());
	    setBorder(new LineBorder(ColorConstants.black,1));
	    setBackgroundColor(classColor);
	    setOpaque(true);
		
	    label.setFont(classFont);
	    
	    add(label);	
	    add(attributeFigure);
	    add(methodFigure);
	  }
	  
	  public void setLabel(String label) {
		  this.label.setText(label);
	  }
	  
	  public MembersSection getAttributesCompartment() {
	    return attributeFigure;
	  }
	  
	  public MembersSection getMethodsCompartment() {
	    return methodFigure;
	  }	
}
