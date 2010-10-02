package designexploder.model.utils;

import java.util.Arrays;
import java.util.Collections;

import org.eclipse.draw2d.geometry.Point;

import designexploder.model.Connection;
import designexploder.model.Diagram;
import designexploder.model.ModelFactory;
import designexploder.model.Node;

public class ModelUtils {

	public static Diagram createDummyModel() {

		ModelFactory mf = ModelFactory.getFactory();
		
		Diagram diagram = mf.createDiagram();
		Node n1 = mf.createNode();
		n1.setLabel("Table");
		n1.setLocation(new Point(120, 130));
		Node n2 = mf.createNode();
		n2.setLabel("Column");
		n2.setLocation(new Point(480, 370));
		Connection c = mf.createConnection();
		c.setSource(n1);
		c.setTarget(n2);
		n1.setOutflows(Collections.singletonList(c));
		n2.setInflows(Collections.singletonList(c));
		diagram.setNodes(Arrays.asList(n1, n2));
		
		return diagram;
	}
}
