package designexploder.model.extension.classnode.impl.eclipse.jdt;

import static designexploder.model.extension.classnode.ClassModelNatures.CLASS;
import static designexploder.model.extension.classnode.ClassModelNatures.ENUM;
import static designexploder.model.extension.classnode.ClassModelNatures.INTERFACE;
import static designexploder.model.extension.classnode.ClassModelNatures.PRIVATE;
import static designexploder.model.extension.classnode.ClassModelNatures.PROTECTED;
import static designexploder.model.extension.classnode.ClassModelNatures.PUBLIC;

import org.eclipse.jdt.core.Flags;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;

import designexploder.model.extension.classnode.ClassModelNatures;

public class DexConstantUtil {
	
	public static ClassModelNatures resolveNature(IType type) {
		try {
			int flags = type.getFlags();
			if(Flags.isInterface(flags)) {
				return INTERFACE;
			}
			if(Flags.isEnum(flags)) {
				return ENUM;
			}
			return CLASS;
		} catch (JavaModelException e) {
			return ClassModelNatures.ERROR;
		}
	}
	
	public static ClassModelNatures getAccessDexContant(int flags) {
		if(Flags.isPublic(flags)) {
			return PUBLIC;
		}
		if(Flags.isPrivate(flags)) {
			return PRIVATE;
		}
		if(Flags.isProtected(flags)) {
			return PROTECTED;
		}
		// Flags.isPackageDefault(flags)
		return null; 
	}
}
