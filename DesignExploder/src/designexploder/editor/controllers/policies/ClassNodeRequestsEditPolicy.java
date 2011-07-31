package designexploder.editor.controllers.policies;

import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.AbstractEditPolicy;

import designexploder.editor.controllers.commands.extension.AddExtensionCommand;
import designexploder.editor.controllers.commands.extension.RemoveExtensionCommand;
import designexploder.model.ExtensibleModelElement;
import designexploder.model.extension.IoC.BeanNode;
import designexploder.model.extension.IoC.IoCModelNatures;
import designexploder.model.extension.IoC.impl.IoCModelFactory;

public class ClassNodeRequestsEditPolicy extends AbstractEditPolicy {
		
	@Override
	public Command getCommand(Request request) {
		Command result = null;
		if(understandsRequest(request)) {
			ExtensibleModelElement extensibleModelElement = (ExtensibleModelElement) getHost().getModel();
			BeanNode extension = extensibleModelElement.getExtension(BeanNode.class);
			if(extension == null) {
				extension = IoCModelFactory.getInstance().createBeanNode();
				extension.setNature(IoCModelNatures.COMMON_BEAN);
				result = new AddExtensionCommand<BeanNode>(extensibleModelElement, BeanNode.class, extension);
			} else {
				result = new RemoveExtensionCommand<BeanNode>(extensibleModelElement, BeanNode.class);
			}
		}
		return result;
	}
	@Override
	public boolean understandsRequest(Request req) {
		return req.getType() == REQ_OPEN;
	}

}
