package designexploder.editor.renderers;

import designexploder.model.Node;
import designexploder.model.extension.IoC.BeanNode;
import designexploder.model.extension.common.Nature;

public class BeanFigureRenderer extends ClassFigureRenderer {

	@Override
	public String getNodeLabel(Node node) {
		BeanNode bean = node.getExtension(BeanNode.class);
		String beanName = bean != null ? bean.getName() : null;
		return beanName != null ? beanName : super.getNodeLabel(node);
	}
	
	@Override
	public Nature getNodeNature(Node node) {
		BeanNode bean = node.getExtension(BeanNode.class);
		return bean != null ? bean.getNature() : super.getNodeNature(node);
	}
	
}
