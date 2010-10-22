package designexploder.model.classnode;

import designexploder.model.classnode.impl.ClassNodeImpl;
import designexploder.model.classnode.impl.ClassSectionImpl;
import designexploder.model.classnode.impl.MemberImpl;

public class ClassModelFactory {
	private static ClassModelFactory INSTANCE;
	private ClassModelFactory() {}
	public static ClassModelFactory getFactory() {
		if(INSTANCE == null) {
			INSTANCE = new ClassModelFactory();
		}
		return INSTANCE; 
	}
	
	public ClassNode createClassNode() {
		return new ClassNodeImpl();
	}
	
	public Member createMember() {
		return new MemberImpl();
	}
	
	public ClassSection createSection() {
		return new ClassSectionImpl();
	}
}
