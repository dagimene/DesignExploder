
package org.gems.test.deployment.deployment;

import org.eclipse.gmt.gems.model.AbstractConstraintsChecker;

import org.eclipse.gmt.gems.model.Container;
import org.eclipse.gmt.gems.model.ModelObject;
import org.eclipse.gmt.gems.model.ModelUtilities;
import org.eclipse.gmt.gems.model.Model;
import org.eclipse.gmt.gems.model.event.ModelChangeEvent;
import org.eclipse.gmt.gems.model.ExecutableConstraint;
import org.eclipse.gmt.gems.model.ExecutableEventConstraint;
import org.eclipse.gmt.gems.model.Root;
import org.eclipse.gmt.gems.metamodel.ConstraintMemento;
import org.eclipse.gmt.gems.model.actions.EventInterestFactory;
import org.eclipse.gmt.gems.model.actions.EventInterestFactoryRepository;
import org.eclipse.gmt.gems.model.actions.PersistentModelEventInterest;

public class DeploymentConstraintsChecker extends AbstractConstraintsChecker {

    /**
     * 
     */
    public DeploymentConstraintsChecker() {
        super();
    }
    
    public void createConstraints() {
        addConnectionConstraint(Component.class,
                                Node.class,
                                0,
                                2147483647,
                                0,
                                2147483647,
                                DeploymentConnectionType.INSTANCE);
       
        addConnectionConstraint(Component.class,
                                Node.class,
                                0,
                                2147483647,
                                0,
                                2147483647,
                                DeploymentConnectionType.INSTANCE);
       
       
        addContainmentConstraint(DeploymentPlan.class,
                                 Component.class,
                                 0,
                                 2147483647);
        addContainmentConstraint(DeploymentPlan.class,
                                 Node.class,
                                 0,
                                 2147483647);
        addContainmentConstraint(Component.class,
                                 ComponentProperty.class,
                                 0,
                                 2147483647);
        addContainmentConstraint(DeploymentPlan.class,
                                 Component.class,
                                 0,
                                 2147483647);
        addContainmentConstraint(Node.class,
                                 NodeResource.class,
                                 0,
                                 2147483647);
        addContainmentConstraint(DeploymentPlan.class,
                                 Node.class,
                                 0,
                                 2147483647);
        addContainmentConstraint(Node.class,
                                 NodeResource.class,
                                 0,
                                 2147483647);
        addContainmentConstraint(Component.class,
                                 ComponentProperty.class,
                                 0,
                                 2147483647);
        addContainmentConstraint(Root.class,
                                 Component.class,
                                 0,
                                 Integer.MAX_VALUE);
        addContainmentConstraint(Root.class,
                                 Node.class,
                                 0,
                                 Integer.MAX_VALUE);
        
    }
    
    public java.util.List<org.eclipse.gmt.gems.Memento> getConstraintMementos(){
        java.util.LinkedList<org.eclipse.gmt.gems.Memento> mems = new java.util.LinkedList<org.eclipse.gmt.gems.Memento>();
    	 
        return mems;
    }


}
