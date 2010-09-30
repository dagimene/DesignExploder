package designexploder.editor.graphics;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.swt.graphics.Color;

public class ClassBox extends Figure {
	  public static Color classColor = new Color(null,255,255,206);
	  
	  private MemebersSeccion attributeFigure = new MemebersSeccion();
	  private MemebersSeccion methodFigure = new MemebersSeccion();
	  
	  public ClassBox(Label name) {
		setLayoutManager(new ToolbarLayout());
	    setBorder(new LineBorder(ColorConstants.black,1));
	    setBackgroundColor(classColor);
	    setOpaque(true);
		
	    add(name);	
	    add(attributeFigure);
	    add(methodFigure);
	  }
	  
	  public MemebersSeccion getAttributesCompartment() {
	    return attributeFigure;
	  }
	  
	  public MemebersSeccion getMethodsCompartment() {
	    return methodFigure;
	  }	
}
