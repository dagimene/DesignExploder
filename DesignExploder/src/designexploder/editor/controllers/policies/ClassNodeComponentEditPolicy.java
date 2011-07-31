package designexploder.editor.controllers.policies;

import java.util.Set;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.GroupRequest;

import designexploder.editor.controllers.commands.RemoveNodeCommand;
import designexploder.model.BasicModelUtil;
import designexploder.model.Node;
import designexploder.model.NodeContainer;
import designexploder.util.adt.CastIterator;
import designexploder.util.adt.IdUtil;

public class ClassNodeComponentEditPolicy extends HierchicalComponentEditPolicy {
	
	
	@Override
	protected Command createDeleteCommand(GroupRequest deleteRequest) {
		Command result = null;
		Node node = (Node) getHost().getModel();
		NodeContainer diagram = BasicModelUtil.findModelRoot(node);
		String idName = IdUtil.parseId(node.getId()).name;
		Set<Node> nodes = BasicModelUtil.findNodesByIdName(diagram, idName);
		for(EditPart part :
			CastIterator.<EditPart>createIterable(deleteRequest.getEditParts().iterator())) {
			nodes.remove(part.getModel());
		}
		if(!nodes.isEmpty()) {
			result = new RemoveNodeCommand(node); 
		}
		return result;
	}

	@Override
	protected Command createDeleteParentCommand(GroupRequest deleteParentRequest) {
		return createDeleteCommand(deleteParentRequest);
	}

}
