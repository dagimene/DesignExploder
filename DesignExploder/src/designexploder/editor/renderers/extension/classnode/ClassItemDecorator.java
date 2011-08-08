package designexploder.editor.renderers.extension.classnode;

import java.util.List;

import designexploder.model.Node;
import designexploder.model.extension.classnode.ClassItem;
import designexploder.model.extension.common.Nature;
import designexploder.resources.IconResource;

public interface ClassItemDecorator {

	String getItemLabel(ClassItem item, Node node);

	Nature getItemNature(ClassItem item, Node node);

	List<IconResource> getItemIcons(ClassItem row, Node node, List<IconResource> natures);

}
