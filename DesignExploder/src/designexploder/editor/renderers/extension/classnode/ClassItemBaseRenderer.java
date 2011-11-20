package designexploder.editor.renderers.extension.classnode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import designexploder.editor.graphics.ClassItemFigure;
import designexploder.model.Node;
import designexploder.model.extension.IoC.BeanNode;
import designexploder.model.extension.IoC.Dependency;
import designexploder.model.extension.IoC.IoCModelUtil;
import designexploder.model.extension.classnode.ClassItem;
import designexploder.model.extension.classnode.Method;
import designexploder.model.extension.common.Nature;
import designexploder.model.extension.common.NodeDesignProperties;
import designexploder.resources.IconResource;

public class ClassItemBaseRenderer implements ClassItemDecorator {

	private ArrayList<ClassItemDecorator> decorators = new ArrayList<ClassItemDecorator>();
	
	public void render(ClassItem model, Node node, ClassItemFigure figure) {
		figure.setLabel(getItemLabel(model, node));
		figure.setNature(getItemNature(model, node));
		figure.setIcons(getItemIcons(model, node, new LinkedList<IconResource>()));
        figure.setRendered(shouldRender(model, node));
	}

    private boolean shouldRender(ClassItem model, Node node) {
        if(!model.isInherited() || isShowInheritedMembers(node) || IoCModelUtil.findDependencyFromClassItem(model, node) != null) {
            return true;
        } else if(model.isMethod()) {
            BeanNode beanNode = node.getExtension(BeanNode.class);
            return beanNode != null && IoCModelUtil.findIoCAwareMethodFromMethod((Method) model, beanNode) != null;
        }
        return false;
    }

    private boolean isShowInheritedMembers(Node node) {
        NodeDesignProperties extension = node.getExtension(NodeDesignProperties.class);
        return (extension != null && extension.isShowInheritedMembers());
    }

    @Override
	public String getItemLabel(ClassItem item, Node node) {
		Iterator<ClassItemDecorator> iterator = decorators.iterator();
		String result = null;
		while(result == null && iterator.hasNext()) {
			result = iterator.next().getItemLabel(item, node);
		}
		return result != null ? result : "";
	}

	@Override
	public Nature getItemNature(ClassItem item, Node node) {
		Iterator<ClassItemDecorator> iterator = decorators.iterator();
		Nature result = null;
		while(result == null && iterator.hasNext()) {
			result = iterator.next().getItemNature(item, node);
		}
		return result != null ? result : Nature.NONE;
	}

	@Override
	public List<IconResource> getItemIcons(ClassItem item, Node node, List<IconResource> natures) {
		// Reverted iteration allows overriding decorators to notice what was done by previous decorators.
		ListIterator<ClassItemDecorator> iterator = decorators.listIterator(decorators.size());
		while(iterator.hasPrevious()) {
			natures = iterator.previous().getItemIcons(item, node, natures);
		}
		return natures;
	}

	public void addDecorator(ClassItemDecorator decorator) {
		decorators.add(decorator);
	}

}
