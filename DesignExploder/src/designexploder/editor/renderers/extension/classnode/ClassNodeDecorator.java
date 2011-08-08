package designexploder.editor.renderers.extension.classnode;


import designexploder.editor.renderers.NodeRendererDecorator;
import designexploder.model.Node;
import designexploder.model.extension.classnode.ClassNode;
import designexploder.model.extension.common.Nature;

public class ClassNodeDecorator implements NodeRendererDecorator {

	public String getNodeLabel(Node node) {
		ClassNode classNode = node.getExtension(ClassNode.class);
		return classNode.getType().getName();		
	}

	public Nature getNodeNature(Node node) {
		ClassNode classNode = node.getExtension(ClassNode.class);
		return classNode.getNature();
	}

}
