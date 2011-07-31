package designexploder.editor.renderers;

import java.util.List;

import designexploder.model.Node;
import designexploder.model.extension.classnode.ClassItem;
import designexploder.model.extension.common.Nature;

public interface NodeRendererDecorator {

	String getNodeLabel(Node node);
	
	Nature getNodeNature(Node node);

	String getItemLabel(int index, Node node, ClassItem item);

	Nature getItemNature(int index, Node node, ClassItem item);

	List<Nature> getItemIcons(int index, Node node, ClassItem row, List<Nature> natures);

}

