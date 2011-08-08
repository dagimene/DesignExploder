package designexploder.editor.renderers.extension.IoC;

import java.util.List;
import java.util.Set;

import designexploder.editor.graphics.styles.StylesFactory;
import designexploder.editor.renderers.extension.classnode.ClassItemDecorator;
import designexploder.model.Node;
import designexploder.model.extension.IoC.BeanNode;
import designexploder.model.extension.IoC.ClassItemTargeted;
import designexploder.model.extension.IoC.Dependency;
import designexploder.model.extension.classnode.ClassItem;
import designexploder.model.extension.common.Nature;
import designexploder.resources.ClassItemIconProvider;
import designexploder.resources.IconResource;

public class ClassItemIoCDecorator implements ClassItemDecorator {

	@Override
	public String getItemLabel(ClassItem item, Node node) {
		return null;
	}

	@Override
	public Nature getItemNature(ClassItem item, Node node) {
		return null;
	}

	@Override
	public List<IconResource> getItemIcons(ClassItem item, Node node, List<IconResource> natures) {
		BeanNode bean = node.getExtension(BeanNode.class);
		if(bean != null) {
			addClassItemTargetedIcons(bean.getDependencies(), item, natures);
			addClassItemTargetedIcons(bean.getIoCAwareMethods(), item, natures);
		}
		return natures;
	}

	private void addClassItemTargetedIcons(Set<? extends ClassItemTargeted> classItemTargeteds, ClassItem target, List<IconResource> natures) {
		for (ClassItemTargeted classItemTargeted : classItemTargeteds) {
			if(classItemTargeted.getTarget() == target) {
				if(classItemTargeted instanceof Dependency && ((Dependency) classItemTargeted).isResolved()) {
					natures.remove(0);
					natures.add(0, ClassItemIconProvider.getItemIconResource(classItemTargeted.getTarget(), true));
				}
				natures.add(0, StylesFactory.getInstance().getIconFor(classItemTargeted.getNature()));
			}
		}
	}

}
