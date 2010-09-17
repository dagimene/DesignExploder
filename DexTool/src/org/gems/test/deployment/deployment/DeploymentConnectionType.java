
package org.gems.test.deployment.deployment;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;
import org.eclipse.gmt.gems.model.BasicConnectionType;
import org.eclipse.gmt.gems.model.Wire;
import org.eclipse.gmt.gems.model.AttributeValue;
import java.util.Vector;
import org.gems.test.deployment.emf.deployment.*;


public class DeploymentConnectionType extends BasicConnectionType {
    public static final String NAME = "Deployment";
    public static final DeploymentConnectionType INSTANCE = 
        new DeploymentConnectionType();
    
    public static final String MECHANISM = "Mechanism";
    
         	
          public static final String[] MECHANISM_VALUES =
          {
              "manual",
              "automated"
          };

    public static final IPropertyDescriptor[] DEPLOYMENT_ATTRIBUTE_DESCRIPTORS =
    {new org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor("Mechanism","Mechanism",MECHANISM_VALUES)


    };
    public static final Object[] DEPLOYMENT_DEFAULT_ATTRIBUTE_VALUES = {
       
            "manual"
    };
    
      	 

    
    protected void getPropertyDescriptors(Vector desc){
    	desc.addAll(java.util.Arrays.asList(DEPLOYMENT_ATTRIBUTE_DESCRIPTORS));    	
    	super.getPropertyDescriptors(desc);
    }
    
   
   
    
    private DeploymentConnectionType() {
        super(NAME,Component.class,Node.class,"HostedBy","HostedComponents");
        registerType(DeploymentProvider.MODEL_ID,this);
    }
    
    protected DeploymentConnectionType(String type) {
        super(type);
        registerType(DeploymentProvider.MODEL_ID,this);
    }
    
    public String getModelID() {
        return DeploymentProvider.MODEL_ID;
    }
    
    public void installAttributes(Wire wire) {
    	EMFDeploymentProxy proxy = new EMFDeploymentProxy();
		installAttributes(wire,proxy);
    }
    
     public void installAttributes(Wire wire, EMFDeploymentProxy proxy) {
        AttributeValue[] attributes = proxy.getAttributeValues();
        wire.installAttribute(NAME,attributes[0]);
        for(int i = 0; i < DEPLOYMENT_ATTRIBUTE_DESCRIPTORS.length; i++) {
            wire.installAttribute((String)DEPLOYMENT_ATTRIBUTE_DESCRIPTORS[i].getId(),attributes[i+1]);
        }
        super.installAttributes(wire);
    }
   

    public static String getMechanism(Wire w){
    	return (String)w.getAttribute(MECHANISM);
    }
}

 
       
       
   