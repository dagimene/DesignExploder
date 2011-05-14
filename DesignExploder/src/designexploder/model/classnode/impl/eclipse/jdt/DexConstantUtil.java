package designexploder.model.classnode.impl.eclipse.jdt;

import static designexploder.model.classnode.DexConstant.CLASS;
import static designexploder.model.classnode.DexConstant.ENUM;
import static designexploder.model.classnode.DexConstant.INTERFACE;
import static designexploder.model.classnode.DexConstant.PRIVATE;
import static designexploder.model.classnode.DexConstant.PROTECTED;
import static designexploder.model.classnode.DexConstant.PUBLIC;

import org.eclipse.jdt.core.Flags;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;

import designexploder.model.classnode.DexConstant;

public class DexConstantUtil {
	
	public static DexConstant resolveNature(IType type) {
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
			return DexConstant.ERROR;
		}
	}
	
	public static DexConstant getAccessDexContant(int flags) {
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
