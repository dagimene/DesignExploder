package designexploder.model.utils;

import java.util.Arrays;
import java.util.Collections;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

import designexploder.model.Connection;
import designexploder.model.Diagram;
import designexploder.model.ModelFactory;
import designexploder.model.Node;
import designexploder.model.classconnection.ClassConnection;
import designexploder.model.classnode.Attribute;
import designexploder.model.classnode.ClassModelFactory;
import designexploder.model.classnode.ClassNode;
import designexploder.model.classnode.ClassSection;
import designexploder.model.classnode.DexConstant;
import designexploder.model.classnode.Method;
import designexploder.model.classnode.Modifiable;
import designexploder.model.classnode.impl.TypeImpl;

public class ModelUtils {
	
	public static final Dimension NO_DIM = new Dimension(-1, -1);

	public static Diagram createDummyModel() {

		ModelFactory mf = ModelFactory.getFactory();
		
		Diagram diagram = mf.createDiagram();
		Node n1 = mf.createNode("Table");
		n1.setBounds(new Rectangle(new Point(120, 130), NO_DIM));
		Node n2 = mf.createNode("Column");
		n2.setBounds(new Rectangle(new Point(480, 370), NO_DIM));
		Connection c = mf.createConnection();
		c.setSource(n1);
		c.setTarget(n2);
		n1.setOutflows(Collections.singletonList(c));
		n2.setInflows(Collections.singletonList(c));
		diagram.setNodes(Arrays.asList(n1, n2));
		
		return diagram;
	}
	
	public static void fillDummyNode(ClassNode node) {
		
	}

	public static Diagram createClassDummyModel() {
		ModelFactory mf = ModelFactory.getFactory();
		ClassModelFactory cmf = ClassModelFactory.getFactory();
		
		Diagram diagram = mf.createDiagram();
		
		ClassNode n1 = cmf.createClassNode(new TypeImpl("dex.base.Table"), new TypeImpl("java.lang.Object"), DexConstant.CLASS);
		n1.setModifiers(Arrays.asList(new DexConstant[]{DexConstant.PUBLIC, DexConstant.ABSTRACT}));
		
		ClassSection attributes = cmf.createSection();
		attributes.setNature(DexConstant.ATTRIBUTE);
		Attribute m0 = cmf.createAttribute("column", new TypeImpl("dex.base.Column"));
		m0.setModifiers(Arrays.asList(new DexConstant[]{DexConstant.PRIVATE, DexConstant.FINAL}));
		attributes.setMembers(Arrays.asList(new Attribute[]{m0}));
		
		ClassSection methods = cmf.createSection();
		methods.setNature(DexConstant.METHOD);
		Method p0 = cmf.createMethod("getColumn", new TypeImpl("dex.base.Column"), DexConstant.METHOD);
		p0.setModifiers(Arrays.asList(new DexConstant[]{DexConstant.PUBLIC}));
		
		Method p1 = cmf.createMethod("setColumn", new TypeImpl("void"), DexConstant.METHOD);
		Modifiable param1 = cmf.createModifiable("column", new TypeImpl("dex.base.Column"));
		p1.setParameters(Arrays.asList(new Modifiable[] {param1}));
		p1.setModifiers(Arrays.asList(new DexConstant[]{DexConstant.PUBLIC, DexConstant.STATIC}));
		methods.setMembers(Arrays.asList(new Method[]{p0, p1}));
		
		n1.setBounds(new Rectangle(new Point(120, 130), NO_DIM));
		n1.setSections(Arrays.asList(new ClassSection[]{attributes, methods}));
		
		
		ClassNode n2 = cmf.createClassNode(new TypeImpl("dex.base.Column"), new TypeImpl("java.lang.Object"), DexConstant.ABSTRACT_CLASS);
		n2.setModifiers(Arrays.asList(new DexConstant[]{DexConstant.PUBLIC}));
		
		attributes = cmf.createSection();
		attributes.setNature(DexConstant.ATTRIBUTE);
		m0 = cmf.createAttribute("width", new TypeImpl("int"));
		m0.setModifiers(Arrays.asList(new DexConstant[]{DexConstant.PRIVATE, DexConstant.FINAL}));
		Attribute m1 = cmf.createAttribute("height", new TypeImpl("int"));
		m1.setModifiers(Arrays.asList(new DexConstant[]{DexConstant.PRIVATE}));
		attributes.setMembers(Arrays.asList(new Attribute[]{m0, m1}));
		
		methods = cmf.createSection();
		methods.setNature(DexConstant.METHOD);
		p0 = cmf.createMethod("isAutoadjustable", new TypeImpl("boolean"), DexConstant.ABSTRACT_METHOD);
		p0.setModifiers(Arrays.asList(new DexConstant[]{DexConstant.PUBLIC}));
		methods.setMembers(Arrays.asList(new Method[]{p0}));
		
		n2.setBounds(new Rectangle(new Point(480, 370), NO_DIM));
		n2.setSections(Arrays.asList(new ClassSection[]{attributes, methods}));

		ClassConnection c = cmf.createConnection(n1, n2, DexConstant.ASSOCIATION);
		c.setName("likes");
		c.setOriginCardinality(1);
		c.setTargetCardinality(Integer.MAX_VALUE);
		n1.setOutflows(Collections.singletonList((Connection)c));
		n2.setInflows(Collections.singletonList((Connection)c));
		diagram.setNodes(Arrays.asList(n1, n2));
		
		return diagram;
	}
}
