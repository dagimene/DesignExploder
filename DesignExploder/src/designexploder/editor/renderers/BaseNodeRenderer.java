package designexploder.editor.renderers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import designexploder.editor.graphics.ClassFigure;
import designexploder.editor.graphics.SectionFigure;
import designexploder.model.Node;
import designexploder.model.extension.classnode.ClassItem;
import designexploder.model.extension.classnode.ClassNode;
import designexploder.model.extension.common.Nature;

public class BaseNodeRenderer implements NodeRendererDecorator, Renderer<Node, ClassFigure> {

	private ArrayList<NodeRendererDecorator> decorators = new ArrayList<NodeRendererDecorator>();
	
	/**
	 * Adds a decorator. Decorators must be added in order of importance. The former will override the latter.
	 * @param decorator
	 * @return
	 */
	public boolean addDecorator(NodeRendererDecorator decorator) {
		return decorators.add(decorator);
	}

	public boolean removeDecorator(NodeRendererDecorator decorator) {
		return decorators.remove(decorator);
	}

	public void render(Node node, ClassFigure figure) {
		updateHeader(figure, node);
		ClassNode classNode = node.getExtension(ClassNode.class);
		updateCompartment(figure.getAttributesCompartment(), node, classNode.getAttributes());
		updateCompartment(figure.getMethodsCompartment(), node, classNode.getMethods());
	}

	private void updateHeader(ClassFigure figure, Node node) {
		figure.setNature(getNodeNature(node));
		figure.setLabel(getNodeLabel(node));
	}
	
	private void updateCompartment(SectionFigure compartment, Node node, List<? extends ClassItem> items) {
		int count = items.size();
		compartment.setLabelsQuantity(count);
		for(int index = 0; index < count; index++) {
			ClassItem item = items.get(index);
			compartment.setLabelContent(index, getItemLabel(index, node, item), getItemNature(index, node, item), getItemIcons(index, node, item, new LinkedList<Nature>()));
		}
	}
	
	
	public String getNodeLabel(Node node) {
		Iterator<NodeRendererDecorator> iterator = decorators.iterator();
		String result = null;
		while(result == null && iterator.hasNext()) {
			result = iterator.next().getNodeLabel(node);
		}
		return result != null ? result : "";
	}

	public Nature getNodeNature(Node node) {
		Iterator<NodeRendererDecorator> iterator = decorators.iterator();
		Nature result = null;
		while(result == null && iterator.hasNext()) {
			result = iterator.next().getNodeNature(node);
		}
		return result != null ? result : Nature.NONE;
	}

	public String getItemLabel(int index, Node node, ClassItem item) {
		Iterator<NodeRendererDecorator> iterator = decorators.iterator();
		String result = null;
		while(result == null && iterator.hasNext()) {
			result = iterator.next().getItemLabel(index, node, item);
		}
		return result != null ? result : "";
	}

	public Nature getItemNature(int index, Node node, ClassItem item) {
		Iterator<NodeRendererDecorator> iterator = decorators.iterator();
		Nature result = null;
		while(result == null && iterator.hasNext()) {
			result = iterator.next().getItemNature(index, node, item);
		}
		return result != null ? result : Nature.NONE;
	}

	public List<Nature> getItemIcons(int index, Node node, ClassItem item, List<Nature> natures) {
		// Reverted iteration allows overriding decorators to notice what was done by previous decorators.
		ListIterator<NodeRendererDecorator> iterator = decorators.listIterator(decorators.size());
		while(iterator.hasPrevious()) {
			natures = iterator.previous().getItemIcons(index, node, item, natures);
		}
		return natures;
	}
}