package designexploder.editor.renderers.extension.classnode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import designexploder.editor.graphics.ClassItemFigure;
import designexploder.model.Node;
import designexploder.model.extension.classnode.ClassItem;
import designexploder.model.extension.common.Nature;
import designexploder.resources.IconResource;

public class ClassItemBaseRenderer implements ClassItemDecorator {

	private ArrayList<ClassItemDecorator> decorators = new ArrayList<ClassItemDecorator>();
	
	public void render(ClassItem model, Node node, ClassItemFigure figure) {
		figure.setLabel(getItemLabel(model, node));
		figure.setNature(getItemNature(model, node));
		figure.setIcons(getItemIcons(model, node, new LinkedList<IconResource>()));
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
