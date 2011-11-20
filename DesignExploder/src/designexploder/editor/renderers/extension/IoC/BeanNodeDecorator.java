package designexploder.editor.renderers.extension.IoC;

import designexploder.editor.renderers.NodeRendererDecorator;
import designexploder.model.Node;
import designexploder.model.extension.IoC.BeanNode;
import designexploder.model.extension.common.Nature;
import designexploder.util.adt.Pair;

import java.util.List;

public class BeanNodeDecorator implements NodeRendererDecorator {

	@Override
	public String getNodeLabel(Node node) {
		return null;
	}

    @Override
    public List<Pair<String, String>> getNodeLabelTooltipInfo(Node node, List<Pair<String, String>> info) {
        BeanNode bean = node.getExtension(BeanNode.class);
        if(bean != null) {
            info.add(new Pair<String, String>("Name", bean.getName()));
        }
        return info;
    }

    @Override
	public Nature getNodeNature(Node node) {
		BeanNode bean = node.getExtension(BeanNode.class);
		return bean != null ? bean.getNature() : null;
	}

}
