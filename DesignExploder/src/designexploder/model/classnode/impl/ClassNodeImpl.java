package designexploder.model.classnode.impl;

import java.util.ArrayList;
import java.util.List;

import designexploder.model.classnode.ClassNode;
import designexploder.model.classnode.ClassSection;
import designexploder.model.classnode.DexConstant;
import designexploder.model.classnode.Type;
import designexploder.model.impl.NodeImpl;

public class ClassNodeImpl extends NodeImpl implements ClassNode {
	
	private Type type;
	private Type clazz;
	private List<ClassSection> sections;
	private List<DexConstant> modifiers;
	private DexConstant nature;
	
	public ClassNodeImpl(Type clazz, Type type, DexConstant nature) {
		super(clazz.getName());
		this.clazz = clazz;
		this.type = type;
		this.nature = nature;
		this.sections = new ArrayList<ClassSection>();
		this.modifiers = new ArrayList<DexConstant>(5);
	}
	
	public List<ClassSection> getSections() {
		return sections;
	}

	public void setSections(List<ClassSection> sections) {
		this.sections = sections;
	}

	public List<DexConstant> getModifiers() {
		return modifiers;
	}

	public void setModifiers(List<DexConstant> modifiers) {
		this.modifiers = modifiers;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getFirstname() {
		return clazz.getFirstname();
	}

	public void setFirstname(String firstname) {
		clazz.setFirstname(firstname);
	}

	public String getLastname() {
		return clazz.getLastname();
	}

	public void setLastname(String lastname) {
		clazz.setLastname(lastname);
	}

	@Override
	public void setName(String name) {
		clazz.setName(name);
	}

	public String getName() {
		return clazz.getName();
	}

	public boolean isBasic() {
		return clazz.isBasic();
	}

	public DexConstant getNature() {
		return nature;
	}

	public void setNature(DexConstant nature) {
		this.nature = nature;
	}
}
