package designexploder.editor.commands.classnode;

import designexploder.editor.commands.NodeCommand;
import designexploder.model.Node;
import designexploder.model.extension.common.NodeDesignProperties;
import designexploder.model.extension.common.impl.NodeDesignPropertiesImpl;

public class ToggleInheritedMembersCommand extends NodeCommand {

	public ToggleInheritedMembersCommand(Node node) {
		super("ToggleInheritedMembersCommand", node);
	}

	@Override
	public void execute() {
        NodeDesignProperties nodeDesignProperties = getNodeDesignProperties();
        nodeDesignProperties.setShowInheritedMembers(!nodeDesignProperties.isShowInheritedMembers());
	}

	@Override
	public void undo() {
		execute();
	}

	private NodeDesignProperties getNodeDesignProperties() {
        NodeDesignProperties extension = getModel().getExtension(NodeDesignProperties.class);
        if(extension == null) {
            extension = new NodeDesignPropertiesImpl();
            getModel().addExtension(extension);
        }
        return extension;
	}

}
