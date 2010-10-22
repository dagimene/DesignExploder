package designexploder.model.utils;

import java.util.Arrays;
import java.util.Collections;

import org.eclipse.draw2d.geometry.Point;

import designexploder.model.Connection;
import designexploder.model.Diagram;
import designexploder.model.ModelFactory;
import designexploder.model.Node;
import designexploder.model.classnode.ClassModelFactory;
import designexploder.model.classnode.ClassNode;
import designexploder.model.classnode.ClassSection;
import designexploder.model.classnode.ClassTypesEnum;
import designexploder.model.classnode.Member;
import designexploder.model.classnode.Modifier;

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
	
	public Diagram createClassDummyModel() {
		ModelFactory mf = ModelFactory.getFactory();
		ClassModelFactory cmf = ClassModelFactory.getFactory();
		
		Diagram diagram = mf.createDiagram();
		
		ClassNode n1 = cmf.createClassNode();
		n1.setLabel("Table");
		Member header = cmf.createMember();
		header.setStereotypes(Collections.singletonList("singleton"));
		header.setName("dex.base.Table");
		header.setType("java.lang.Object");
		header.setModifiers(Arrays.asList(new Modifier[]{ClassTypesEnum._PUBLIC, ClassTypesEnum._ABSTRACT}));
		n1.setHeader(header);
		
		ClassSection attributes = cmf.createSection();
		attributes.setType(ClassTypesEnum.ATTRIBUTES);
		Member m0 = cmf.createMember();
		m0.setName("column");
		m0.setType("dex.base.Column");
		m0.setSymbol("-");
		m0.setModifiers(Arrays.asList(new Modifier[]{ClassTypesEnum._PRIVATE, ClassTypesEnum._FINAL}));
		attributes.setMembers(Arrays.asList(new Member[]{m0}));
		
		ClassSection methods = cmf.createSection();
		methods.setType(ClassTypesEnum.METHODS);
		Member m1 = cmf.createMember();
		m1.setName("getColumn()");
		m1.setType("dex.base.Column");
		m1.setSymbol("+");
		m1.setModifiers(Arrays.asList(new Modifier[]{ClassTypesEnum._PUBLIC}));
		
		Member m2 = cmf.createMember();
		m2.setName("setColumn(Column column)");
		m2.setType("void");
		m2.setSymbol("+");
		m2.setModifiers(Arrays.asList(new Modifier[]{ClassTypesEnum._PUBLIC, ClassTypesEnum._STATIC}));
		methods.setMembers(Arrays.asList(new Member[]{m1, m2}));
		
		n1.setLocation(new Point(120, 130));
		n1.setSections(Arrays.asList(new ClassSection[]{attributes, methods}));
		
		
		ClassNode n2 = cmf.createClassNode();
		header = cmf.createMember();
		header.setName("dex.base.Column");
		header.setType("java.lang.Object");
		header.setModifiers(Arrays.asList(new Modifier[]{ClassTypesEnum._PUBLIC}));
		n2.setLabel("Column");
		n2.setHeader(header);
		
		attributes = cmf.createSection();
		attributes.setType(ClassTypesEnum.ATTRIBUTES);
		m0 = cmf.createMember();
		m0.setName("width");
		m0.setType("int");
		m0.setSymbol("-");
		m0.setModifiers(Arrays.asList(new Modifier[]{ClassTypesEnum._PRIVATE, ClassTypesEnum._FINAL}));
		
		m1 = cmf.createMember();
		m1.setName("height");
		m1.setType("int");
		m1.setSymbol("-");
		m1.setModifiers(Arrays.asList(new Modifier[]{ClassTypesEnum._PRIVATE}));
		attributes.setMembers(Arrays.asList(new Member[]{m0, m1}));
		
		methods = cmf.createSection();
		methods.setType(ClassTypesEnum.METHODS);
		m2 = cmf.createMember();
		m2.setName("isAutoadjustable()");
		m2.setType("boolean");
		m2.setSymbol("+");
		m2.setModifiers(Arrays.asList(new Modifier[]{ClassTypesEnum._PUBLIC}));
		methods.setMembers(Arrays.asList(new Member[]{m2}));
		
		n2.setLocation(new Point(480, 370));
		n2.setSections(Arrays.asList(new ClassSection[]{attributes, methods}));

		Connection c = mf.createConnection();
		c.setSource(n1);
		c.setTarget(n2);
		n1.setOutflows(Collections.singletonList(c));
		n2.setInflows(Collections.singletonList(c));
		diagram.setNodes(Arrays.asList(n1, n2));
		
		return diagram;
	}
}
