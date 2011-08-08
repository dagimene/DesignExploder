package designexploder.editor.renderers;

import designexploder.model.Node;
import designexploder.model.extension.common.Nature;

public interface NodeRendererDecorator {

	String getNodeLabel(Node node);
	
	Nature getNodeNature(Node node);

}

