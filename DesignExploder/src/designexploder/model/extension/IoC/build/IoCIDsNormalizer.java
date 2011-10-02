package designexploder.model.extension.IoC.build;

import java.util.HashMap;
import java.util.Map;

import designexploder.model.Node;
import designexploder.model.NodeContainer;
import designexploder.model.build.ModelBuilder;
import designexploder.model.extension.IoC.BeanNode;
import designexploder.model.extension.IoC.IoCModelNatures;
import designexploder.model.extension.classnode.ClassNode;
import designexploder.util.adt.IdUtil;
import designexploder.util.adt.IdUtil.ID;
import designexploder.util.adt.IterableIterator;

public class IoCIDsNormalizer implements ModelBuilder {

	private Map<String, Integer> ids = new HashMap<String, Integer>();
	
	@Override
	public NodeContainer build(NodeContainer diagram) {
		normalizeIds(diagram);
		return diagram;
	}

	private void normalizeIds(NodeContainer diagram) {
		for (Node node : new IterableIterator<Node>(diagram.getDeepIterator())) {
			ClassNode classNode = node.getExtension(ClassNode.class);
			if(classNode != null) {
				String name = classNode.getType().getName();
				Integer number = ids.get(name);
				if(number == null) number = -1;
				BeanNode beanNode = node.getExtension(BeanNode.class);
				ID id;
				if(beanNode != null) {
					if(beanNode.getNature() == IoCModelNatures.BEAN_FACADE) {
						id = IdUtil.createFacadeId(name, number);
					} else {
						id = IdUtil.createBeanId(name, number);
					}
				} else {
					id = IdUtil.createClassId(name, number); 
				}
				node.setId(id.toString());
				ids.put(name, ++number);
			} // No need to reset application context ids.
		}
	}
	
}
