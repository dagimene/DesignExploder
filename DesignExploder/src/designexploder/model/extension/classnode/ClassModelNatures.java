package designexploder.model.extension.classnode;

import designexploder.model.extension.common.Nature;

public enum ClassModelNatures implements Nature {
	
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
	NATIVE("native"),
	
	/* Class Natures */
	
	CLASS("Class"),
	ABSTRACT_CLASS("Abstract Class"),
	INTERFACE("Interface"),
	ENUM("Enum"),
	
	/* Class Elements Natures */
	METHOD("Method"),
	ATTRIBUTE("Attribute"),
	ABSTRACT_METHOD("Abstract Method"),
	
	/* Connection Natures */
	
	ASSOCIATION("Association"),
	AGREGATION("Agregation"), // TODO: Meaningless to code generation.
	COMPOSITION("Composition"),
	REALIZATION("Realization"),
	HIERARCHY("Hierarchy"),
	
	/* Nature for fail loading */
	ERROR("Error");
	
	private String name;
	
	ClassModelNatures(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
