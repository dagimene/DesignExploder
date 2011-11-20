package designexploder.editor.renderers.extension.classnode;


import designexploder.editor.renderers.NodeRendererDecorator;
import designexploder.model.Node;
import designexploder.model.extension.classnode.ClassNode;
import designexploder.model.extension.common.Nature;
import designexploder.util.adt.Pair;

import java.util.List;

public class ClassNodeDecorator implements NodeRendererDecorator {

	public String getNodeLabel(Node node) {
		ClassNode classNode = node.getExtension(ClassNode.class);
		return classNode.getType().getFirstname();
	}

    @Override
    public List<Pair<String, String>> getNodeLabelTooltipInfo(Node node, List<Pair<String, String>> info) {
        ClassNode classNode = node.getExtension(ClassNode.class);
        info.add(0, new Pair<String, String>("Class", classNode.getType().getName()));
        return info;
    }

    public Nature getNodeNature(Node node) {
		ClassNode classNode = node.getExtension(ClassNode.class);
		return classNode.getNature();
	}

}
