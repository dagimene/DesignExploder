package designexploder.model.classnode;

import designexploder.model.Named;

public enum DexConstant implements Named {
	
	/* ACCESS MODIFIERS */
	
	PUBLIC("public"),
	PRIVATE("private"),
	PROTECTED("protected"),
	
	/* GENERAL MODIFIERS */
	
	STATIC("static"),
	FINAL("final"),
	ABSTRACT("abstract"),
	TRANSIENT("transient"),
	VOLATILE("volatile"),
	
	/* CLASS SECTIONS */
	
	ATTRIBUTE("Attributes"),
	METHOD("Methods"),

	/* Class Natures */
	
	CLASS("Class"),
	ABSTRACT_CLASS("Abstract Class"),
	INTERFACE("Interface"),
	ABSTRACT_METHOD("Abstract Method"),
	
	/* Connection Natures */
	
	ASSOCIATION("Association"),
	AGREGATION("Agregation"), // TODO: Meaningless to codegeneration.
	COMPOSITION("Composition"),
	REALIZATION("Realization"),
	HIERARCHY("Hierarchy");
	
	private String name;
	
	DexConstant(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
