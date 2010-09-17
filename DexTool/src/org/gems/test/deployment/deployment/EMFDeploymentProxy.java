
package org.gems.test.deployment.deployment;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;
import org.eclipse.gmt.gems.model.BasicConnectionType;
import org.eclipse.gmt.gems.model.Wire;
import org.eclipse.gmt.gems.model.AttributeValue;
import java.util.Vector;
import org.gems.test.deployment.emf.deployment.*;


public class EMFDeploymentProxy {
    	private DeploymentConnection conn_;
    	private AttributeValue[] attributeValues_;
    	
    	
       	private class MechanismAttributeValue implements AttributeValue{
        	private AttributeValue value_;
        	
			public MechanismAttributeValue(AttributeValue val){
				value_ = val;
				if(conn_ != null)
				   
				   value_.setValue(conn_.getMechanism().getLiteral());
				   
			}
			
			public Object getPropertyValue() {return value_.getPropertyValue();}		
			public Object getValue() {return value_.getValue();}		
			public void setValue(Object value) {
				value_.setValue(value);
				Object val = value_.getValue();
				conn_.setMechanism(Mechanism.get((String)val));
			}		
		}
    	
    	public EMFDeploymentProxy(){
    		conn_ = org.gems.test.deployment.emf.deployment.impl.DeploymentFactoryImpl.eINSTANCE.createDeploymentConnection();
    		init();
    	}
    	
    	public EMFDeploymentProxy(DeploymentConnection con){
    		conn_ = con;
    		init();
    	}
    	
    	protected void init(){
    	   
       	   org.eclipse.gmt.gems.model.AttributeValueFactory factory = 
       new org.eclipse.gmt.gems.model.AttributeValueFactory(AttributeValidators.getInstance());
       
org.eclipse.gmt.gems.model.AttributeValue[] attributes = {
  factory.getEnumerationValue("Mechanism","manual,automated","manual")
};



       	   attributeValues_ = new AttributeValue[1 + 1];
       	   attributeValues_[0] = new AttributeValue() {
        	public Object getPropertyValue() {return "EMF";}		
			public Object getValue() {return conn_;}		
			public void setValue(Object value) {}		
			};
       	   
       	   
       	   attributeValues_[1]= new MechanismAttributeValue(attributes[0]);
       	  
       	   
    	}
    	
    	public AttributeValue[] getAttributeValues(){
    		return attributeValues_;
    	}
}

 
       
       
   