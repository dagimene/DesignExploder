package designexploder.model.extension.common.impl;

import designexploder.model.extension.classnode.ClassModelEventTypes;
import designexploder.model.extension.common.NodeDesignProperties;
import designexploder.model.impl.ExtendedModelEventTrigger;

public class NodeDesignPropertiesImpl extends ExtendedModelEventTrigger implements NodeDesignProperties {

    private boolean showInheritedMembers;

    @Override
    @SuppressWarnings("unchecked")
    public Class<NodeDesignProperties> getExtensionClass() {
        return NodeDesignProperties.class;
    }

    public boolean isShowInheritedMembers() {
        return showInheritedMembers;
    }

    public void setShowInheritedMembers(boolean showInheritedMembers) {
        if(this.showInheritedMembers != showInheritedMembers) {
            this.showInheritedMembers = showInheritedMembers;
            fireModelPropertyChangeEvent(ClassModelEventTypes.SHOW_INHERITED_MEMBERS_CHANGED, !showInheritedMembers, showInheritedMembers);
        }
    }

}
