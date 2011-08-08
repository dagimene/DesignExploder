package designexploder.resources;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.swt.graphics.Image;

import designexploder.model.extension.classnode.ClassItem;
import designexploder.model.extension.classnode.Method;

public class ClassItemIconProvider {
	
	private static final String SUFFIX = "_ICON";
	private final IconResource resource;
	private Image icon;

	private ClassItemIconProvider(IconResource resource) {
		this.resource = resource;
	}

	public Image getIcon() {
		return icon != null ? icon : loadIcon();
	}

	private Image loadIcon() {
		return icon = resource.getImage();
	}
	
	public static IconResource getItemIconResource(ClassItem item, boolean injection) {
		return getItemIconResource(ClassItemType.get(item), AccessModifier.get(item), injection);
	}
	
	private static IconResource getItemIconResource(ClassItemType item, AccessModifier access, boolean injection) {
		return IconResource.valueOf((injection ? "INJECTION_" : "") +
				item.name() + "_" + access.name() + SUFFIX);
	}

	public static Collection<IconResource> getAccesorsIconResources(Method setter, Method getter) {
		if(getter != null && setter != null) {
			if(setter.getAccessModifier() == getter.getAccessModifier()) {
				return Collections.singleton(getAccesorIconResource(AccesorType.ACCESORS, AccessModifier.get(getter)));
			} else {
				return Arrays.asList(new IconResource[] {
						getAccesorIconResource(AccesorType.GETTER, AccessModifier.get(getter)),
						getAccesorIconResource(AccesorType.SETTER, AccessModifier.get(setter))});
			}
		} else if(getter != null) {
			return Collections.singleton(getAccesorIconResource(AccesorType.GETTER, AccessModifier.get(getter)));
		} else if(setter != null) {
			return Collections.singleton(getAccesorIconResource(AccesorType.SETTER, AccessModifier.get(setter)));
		}
		return Collections.emptySet();
	}
	
	private static IconResource getAccesorIconResource(AccesorType accesor, AccessModifier access) {
		return IconResource.valueOf(accesor.name() + "_" + access.name() + SUFFIX);
	}

	static enum AccesorType {
		GETTER, SETTER, ACCESORS;
	}
	
	static enum ClassItemType {
		METHOD, FIELD;
		public static ClassItemType get(ClassItem item) {
			return item.isMethod() ? METHOD : FIELD;
		}
	}
	
	static enum AccessModifier {
		PUBLIC, PROTECTED, PACKAGE, PRIVATE;
		public static AccessModifier get(ClassItem item) {
			if(item.isPublic()) {
				return PUBLIC;
			} else if(item.isProtected()) {
				return PROTECTED;
			} else if(item.isPrivate()) {
				return PRIVATE;
			}
			return PACKAGE;
		}
	}

}
