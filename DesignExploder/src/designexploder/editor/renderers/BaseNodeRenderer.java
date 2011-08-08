package designexploder.editor.renderers;

import java.util.ArrayList;
import java.util.Iterator;

import designexploder.editor.graphics.ClassFigure;
import designexploder.model.Node;
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
		figure.setNature(getNodeNature(node));
		figure.setLabel(getNodeLabel(node));
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
}