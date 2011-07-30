package designexploder.editor.controllers;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.AbstractEditPolicy;

import designexploder.editor.controllers.commands.extension.AddExtensionCommand;
import designexploder.editor.controllers.commands.extension.RemoveExtensionCommand;
import designexploder.editor.graphics.ClassFigure;
import designexploder.editor.graphics.GraphicsFactory;
import designexploder.editor.renderers.BeanFigureRenderer;
import designexploder.editor.renderers.ClassFigureRenderer;
import designexploder.model.ExtensibleModelElement;
import designexploder.model.extension.IoC.BeanNode;
import designexploder.model.extension.IoC.IoCModelNatures;
import designexploder.model.extension.IoC.impl.IoCModelFactory;
import designexploder.model.extension.classnode.ClassNode;

public class ClassNodeEditPart extends NodeEditPart {

	private ClassFigureRenderer renderer = new BeanFigureRenderer();
	
	@Override
	protected IFigure createFigure() {
		return GraphicsFactory.createClassFigure();
	}
	
	@Override
	protected void refreshVisuals() {
		renderer.render(getModel(), getFigure());
		super.refreshVisuals();
	}

	@Override
	public ClassFigure getFigure() {
		return (ClassFigure) super.getFigure();
	}
	
	@Override
	public void performRequest(Request request) {
		Command command = null;
		EditPolicyIterator i = getEditPolicyIterator();
		while (i.hasNext()) {
			if (command != null)
				command = command.chain(i.next().getCommand(request));
			else
				command = i.next().getCommand(request);
		}
		getViewer().getEditDomain().getCommandStack().execute(command);
	}
	
	@Override
	protected void createEditPolicies() {
		super.createEditPolicies();
		installEditPolicy(EditPolicy.NODE_ROLE, new AbstractEditPolicy() {
			
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
		});
	}

	public ClassNode getClassModel() {
		return getModel().getExtension(ClassNode.class);
	}
}
