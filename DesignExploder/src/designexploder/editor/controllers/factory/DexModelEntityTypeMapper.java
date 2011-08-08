package designexploder.editor.controllers.factory;

import org.eclipse.gef.EditPart;

import designexploder.editor.controllers.ApplicationContextEditPart;
import designexploder.editor.controllers.ClassConnectionEditPart;
import designexploder.editor.controllers.ClassItemEditPart;
import designexploder.editor.controllers.ClassNodeEditPart;
import designexploder.editor.controllers.RootNodeContainerEditPart;
import designexploder.model.Connection;
import designexploder.model.ContainerNode;
import designexploder.model.Node;
import designexploder.model.NodeContainer;
import designexploder.model.extension.classnode.ClassItem;

public enum DexModelEntityTypeMapper {
	
	CONTAINER_NODE(ContainerNode.class, ApplicationContextEditPart.class),
	NODE(Node.class, ClassNodeEditPart.class),
	CONNECTION(Connection.class, ClassConnectionEditPart.class),
	NODE_CONTAINER(NodeContainer.class, RootNodeContainerEditPart.class),
	CLASS_ITEM(ClassItem.class, ClassItemEditPart.class);

	private final Class<? extends EditPart> editPartClass;
	private final Class<?> modelClass;
	
	DexModelEntityTypeMapper(Class<?> modelClass, Class<? extends EditPart> editPartClass) {
		this.modelClass = modelClass;
		this.editPartClass = editPartClass;
	}

	public EditPart instantiateEditPart() {
		return instantiate(editPartClass);
	}
	
	private <E> E instantiate(Class<E> clazz) {
		E result = null;
		try {
			result = clazz.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static DexModelEntityTypeMapper typeFor(Object model) {
		DexModelEntityTypeMapper result = null;
		for (DexModelEntityTypeMapper type : values()) {
			if(type.modelClass.isAssignableFrom(model.getClass())) {
				result = type;
				break;
			}
		}
		return result; 
	}

}
