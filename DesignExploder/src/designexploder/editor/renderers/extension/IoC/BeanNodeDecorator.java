package designexploder.editor.renderers.extension.IoC;

import java.util.List;

import designexploder.editor.renderers.NodeRendererDecorator;
import designexploder.model.Node;
import designexploder.model.extension.IoC.BeanNode;
import designexploder.model.extension.classnode.ClassItem;
import designexploder.model.extension.common.Nature;

public class BeanNodeDecorator implements NodeRendererDecorator {

	@Override
	public String getNodeLabel(Node node) {
		BeanNode bean = node.getExtension(BeanNode.class);
		return bean != null ? bean.getName() : null;
	}
	
	@Override
	public Nature getNodeNature(Node node) {
		BeanNode bean = node.getExtension(BeanNode.class);
		return bean != null ? bean.getNature() : null;
	}

	@Override
	public String getItemLabel(int index, Node node, ClassItem item) {
		return null;
	}

	@Override
	public Nature getItemNature(int index, Node node, ClassItem item) {
		return null;
	}

	@Override
	public List<Nature> getItemIcons(int index, Node node, ClassItem row,
			List<Nature> natures) {
		return natures;
	}

}
