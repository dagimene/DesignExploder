package designexploder.editor.renderers;

import designexploder.model.Node;
import designexploder.model.extension.common.Nature;
import designexploder.util.adt.Pair;

import java.util.List;

public interface NodeRendererDecorator {

	String getNodeLabel(Node node);
	
    List<Pair<String, String>> getNodeLabelTooltipInfo(Node node, List<Pair<String, String>> info);

    Nature getNodeNature(Node node);

}

