package designexploder.model.classnode;

import designexploder.model.impl.NamedImpl;

public class ClassTypesEnum {
	
	static class ModifierImpl extends NamedImpl implements Modifier {
		public ModifierImpl(String name) {
			super(name);
		}
	}

	public static final Modifier _PUBLIC = new ModifierImpl("public");
	public static final Modifier _PRIVATE = new ModifierImpl("private");
	public static final Modifier _PROTECTED = new ModifierImpl("protected");

	public static final Modifier _STATIC = new ModifierImpl("public");
	public static final Modifier _FINAL = new ModifierImpl("final");
	public static final Modifier _ABSTRACT = new ModifierImpl("abstract");
	
	public static final Modifier _TRANSIENT = new ModifierImpl("transient");
	public static final Modifier _VOLATILE = new ModifierImpl("volatile");

	static class SectionTypeImpl extends NamedImpl implements SectionType {
		public SectionTypeImpl(String name) {
			super(name);
		}
	}
	
	public static final SectionType ATTRIBUTES = new SectionTypeImpl("Attributes");
	public static final SectionType METHODS	 = new SectionTypeImpl("Methods");

}
