package designexploder.editor.controllers.policies;

import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.AbstractEditPolicy;

import designexploder.editor.commands.extension.AddExtensionCommand;
import designexploder.editor.commands.extension.RemoveExtensionCommand;
import designexploder.model.Node;
import designexploder.model.extension.IoC.BeanNode;
import designexploder.model.extension.IoC.IoCModelUtil;
import designexploder.model.extension.IoC.impl.IoCModelFactory;
import designexploder.model.extension.classnode.ClassNode;
import designexploder.util.adt.IdUtil;

public class ClassNodeRequestsEditPolicy extends AbstractEditPolicy {
		
	@Override
	public Command getCommand(Request request) {
		Command result = null;
		if(understandsRequest(request)) {
			Node node = (Node) getHost().getModel();
			BeanNode extension = node.getExtension(BeanNode.class);
			if(extension == null) {
				ClassNode classNode = node.getExtension(ClassNode.class);
				extension = IoCModelFactory.getInstance().createBeanNode();
				extension.setNode(node);
				extension.setNature(IoCModelUtil.getBeanNatureFor(classNode, extension, IdUtil.parseId(node.getId())));
				result = new AddExtensionCommand<BeanNode>(node, BeanNode.class, extension);
			} else {
				result = new RemoveExtensionCommand<BeanNode>(node, BeanNode.class);
			}
		}
		return result;
	}
	@Override
	public boolean understandsRequest(Request req) {
		// return req.getType() == REQ_OPEN;
		return false;
	}

}
