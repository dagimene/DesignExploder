

/*
 * Created on Fri Sep 17 14:59:59 ART 2010
 *
 * Generated by GEMS 
 */
 
package org.gems.test.deployment.deployment;



import org.eclipse.gmt.gems.metamodel.gen.AttributeInfo;
import org.eclipse.gmt.gems.model.AttributeValidator;
import org.eclipse.gmt.gems.ModelProvider;
import org.eclipse.gmt.gems.ModelRepository;
import org.eclipse.gmt.gems.model.Visitor;
import org.eclipse.gmt.gems.model.ConnectionType;
import org.eclipse.gmt.gems.model.Wire;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import java.util.Vector;
import org.eclipse.gmt.gems.model.ModelObject;
import org.eclipse.gmt.gems.model.LogicElement;
import java.util.List;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gmt.gems.model.ModelUtilities;
import org.eclipse.gmt.gems.model.props.CustomProperty;
import org.eclipse.gmt.gems.model.props.CustomPropertyEx;
import org.eclipse.gmt.gems.model.props.PropertyRepository;
import org.eclipse.emf.ecore.EObject;
import org.gems.test.deployment.emf.deployment.*;

public class ComponentProperty extends org.eclipse.gmt.gems.model.LinkedModel  implements Adapter, EMFModelObject, org.eclipse.gmt.gems.model.EMFModelElement{

	private org.gems.test.deployment.emf.deployment.ComponentProperty model_;
	private Notifier target_;
	
	public ComponentProperty(){		
	}
	
	public ComponentProperty(org.gems.test.deployment.emf.deployment.ComponentProperty model){
		model_ = model;
		super.setName(model_.getName());
		super.setID(model_.getId());
    	model_.eAdapters().add(this);
	}
	
	public org.gems.test.deployment.emf.deployment.ComponentProperty getModel(){
		if(model_ == null){
			model_ = org.gems.test.deployment.emf.deployment.impl.DeploymentFactoryImpl.eINSTANCE.createComponentProperty();
			super.setName(model_.getName());
			model_.eAdapters().add(this);
		}
		return model_;
	}
	
	public org.gems.test.deployment.emf.deployment.ComponentProperty getExtendedModel(){
		return getModel();
	}
	
	public org.gems.test.deployment.emf.deployment.ComponentProperty getEMFObject(){
		return getModel();
	}
	
       
    public String getName(){
    	return getExtendedModel().getName();
    }
    
    public boolean isVisible(){
    	return getExtendedModel().isVisible();
    }
    
    
    public void setVisible(boolean b){
    	getExtendedModel().setVisible(b);
    	super.setVisible(b);
    }
    
    public void setName(String name){
    	getExtendedModel().setName(name);
    	super.setName(name);
    }
    
    public String getID(){
    	return getExtendedModel().getId();
    }
    
    public boolean isSubtype(){
    	return getExtendedModel().isSubtype();
    }
    
    public void setSubtype(boolean b){
    	getExtendedModel().setSubtype(b);
    	super.setSubtype(b);
    }
    
    public void setID(String id){
    	getExtendedModel().setId(id);
    	super.setID(id);
    }
    
    public Point getLocation(){
    	return new Point(getExtendedModel().getX(),getExtendedModel().getY());
    }
    
    public void setLocation(Point p) {
    	getExtendedModel().setX(p.x);
    	getExtendedModel().setY(p.y);
        super.setLocation(p);
    }
    
    public Dimension getSize() {
        return new Dimension(getExtendedModel().getWidth(),getExtendedModel().getHeight());
    }
    
    public org.eclipse.gmt.gems.Subtype createSubtype(){
    	return new EMFSubtypeImpl(this,getName());
    }
    
    public void setSize(Dimension d){
    	getExtendedModel().setWidth(d.width);
    	getExtendedModel().setHeight(d.height);
    	super.setSize(d);
    }
    
    public EObject getEMFModelElement(){
    	return getEMFObject();
    }
    
    public String getModelLinkTarget(){
    	return getExtendedModel().getModelLinkTarget();
    }
    
    public void setModelLinkTarget(String target){
    	getExtendedModel().setModelLinkTarget(target);
 		super.setModelLinkTarget(target);
    }
    	
    	public Notifier getTarget() {
    		return target_;
    	}
    	
    	public void setTarget(Notifier newTarget) {
    		target_ = newTarget;
    	}
    	
    	public boolean isAdapterForType(Object type) {
    		return getModel() == type;
    	}
    
    
    

    public static final IPropertyDescriptor[] COMPONENTPROPERTY_ATTRIBUTE_DESCRIPTORS =
    {
new org.eclipse.ui.views.properties.TextPropertyDescriptor("CPropertyValue","CPropertyValue")


    };
    public static final Object[] COMPONENTPROPERTY_DEFAULT_ATTRIBUTE_VALUES = {
       
            "0"
    };
    
      	 

    
    protected void getPropertyDescriptors(Vector desc){
    	desc.addAll(java.util.Arrays.asList(COMPONENTPROPERTY_ATTRIBUTE_DESCRIPTORS));    	
    	super.getPropertyDescriptors(desc);
    }
    
   
    	
    	private String CPropertyValue_ = "0";
    public String findAttributeType(String attr){
      if(attr.equals("CPropertyValue")){
        return STRING_MTYPE;
      }
      return super.findAttributeType(attr);
    }
   public String getCPropertyValue(){
   	return getModel().getCPropertyValue();
   	
   }
   public void setCPropertyValue(String val){
     Object old = getCPropertyValue();
   	 getModel().setCPropertyValue(val);
   	 
   	  Object[] valuepair = {"CPropertyValue",old};        
      org.eclipse.gmt.gems.model.event.ModelChangeEvent event = new org.eclipse.gmt.gems.model.event.ModelChangeEvent(this,
                org.eclipse.gmt.gems.model.event.ModelChangeEvent.ELEMENT_ATTRIBUTE_CHANGED,false,(Object)valuepair);
      org.eclipse.gmt.gems.model.event.ModelEventDispatcher.dispatch(event);
      
      if(event.vetoed()){
      	setCPropertyValue((String)old);
      }
   }
    public Object getAttribute(String attr){
    	
	 if(attr != null &&
	    attr.equals("CPropertyValue")){
	    return getCPropertyValue().toString();
	  }
     return super.getAttribute(attr);
   }
  
   
    public void setAttribute(String attr, Object val){
         if(attr != null){
           AttributeValidator validator = AttributeValidators.getInstance().getValidator(attr);
           if(validator != null &&
              !validator.validValue(this,attr,val)){
                return;
              }
         }	
         if(attr.equals(org.eclipse.gmt.gems.model.Atom.NAME)){
        	 getModel().setName((String)val);
         }   													
	 if(attr != null &&
	    attr.equals("CPropertyValue")){
	    setCPropertyValue(new String((String)val));
	  }
      else {
        super.setAttribute(attr,val); }
   }
   
   public void notifyChanged(Notification notification) {
   		int type = notification.getEventType();
   		int featureId = notification.getFeatureID(DeploymentPackage.class);
    		
   		switch(type) {
   		case Notification.SET:
   		
   			switch(featureId) {
    			case DeploymentPackage.COMPONENT_PROPERTY__CPROPERTY_VALUE :
   				firePropertyChange(ATTRIBUTE_PREFIX+"CPropertyValue", notification.getOldStringValue(), notification.getNewStringValue());
   				break;
   			}
   	    
   		case Notification.ADD:
   		case Notification.REMOVE:
   		}
   }
   
    

	
	
		







public void connectInput(Wire w, boolean modifymodel) {
	if(!modifymodel)
		super.connectInput(w);
	else
		connectInput(w);
}

public void disconnectInput(Wire w, boolean modifymodel) {
	if(!modifymodel)
		super.disconnectInput(w);
	else
		disconnectInput(w);
}



public void connectOutput(Wire w, boolean modifymodel) {
	if(!modifymodel)
		super.connectOutput(w);
	else
		connectOutput(w);
}

public void disconnectOutput(Wire w, boolean modifymodel) {
	if(!modifymodel)
		super.disconnectOutput(w);
	else
		disconnectOutput(w);
}



    
    
    public String getConnectionRole(ConnectionType ct, boolean src){
	
	  return super.getConnectionRole(ct,src);
	}
	
	public List<String> getRelationshipRoles(){
    	java.util.List<String> roles = super.getRelationshipRoles();
    	
    	return roles;
    }
	
	
	public ConnectionType getConnectionTypeForRole(String role){
	
	  return super.getConnectionTypeForRole(role);
	}
	
    public Object getRoleValue(String role){
	
	
		if("CPropertyOf".equalsIgnoreCase(role)){
	       return getCPropertyOf();
	    }
	
	
	  return super.getRoleValue(role);
	}
	
	
	public Component getCPropertyOf(){
	       if(getParent() instanceof Component){
	       	  return (Component)getParent();
	       }
	       return null;
	}
	
    
    public boolean isAbstract(){return false;}
     
    public ModelProvider getModelProvider() {
        ModelProvider provider = super.getModelProvider();
        if(provider == null){
        	provider = new DeploymentProvider();
        	ModelRepository.getInstance().registerModelProvider(provider);
        }
        return provider;
    }
    
    protected void buildContainmentNames() {
    }
    
    	public List<String> getContainmentRoles(){
    	List<String> roles = super.getContainmentRoles();
    	
    	roles.add("CPropertyOf");
    	
    	return roles;
    }
    
    public Class getParentForRole(String role){
    	
    	if("CPropertyOf".equals(role)){
    		return Component.class;
    	}
    	
    	return super.getParentForRole(role);
    }
    
    public String getRoleForParent(Class c){
    	
    	if(Component.class.isAssignableFrom(c)){
    	   return "CPropertyOf";
    	}
    	
    	return super.getRoleForParent(c);
    }	
    
public void accept(Visitor visitor) {
   if(visitor instanceof DeploymentVisitor)
   	((DeploymentVisitor)visitor).visitComponentProperty(this);
   else
     super.accept(visitor);
}
   
    
    public void addChild(LogicElement child, int index) {
       addChild(child,index,true);
    }
    
    public void addChild(LogicElement child, int index, boolean modifymodel) {
       	if(modifymodel){
    	}
    	
    	super.addChild(child,index);
    	
    }
    
    public void removeChild(LogicElement child){
		removeChild(child,true);
    }
    
    public void removeChild(LogicElement child, boolean modifymodel){
    	if(modifymodel){
     	
    	}
    	
    		super.removeChild(child);
    	
    }
    
    
    public boolean isExpanded(){
    	return getExtendedModel().isExpanded();
    }
    
    public void setExpanded(boolean ex){
    	getExtendedModel().setExpanded(ex);
    	super.setExpanded(ex);
    }
    
    public Dimension getExpandSize() {
        return new Dimension(getModel().getExpandedWidth(),getModel().getExpandedHeight());
    }
    
    public void setExpandSize(Dimension d){
    	getExtendedModel().setExpandedWidth(d.width);
    	getExtendedModel().setExpandedHeight(d.height);
    	super.setExpandSize(d);
    }
    
    public void setAnnotation(String a){
       getExtendedModel().setAnnotation(a);
       super.setAnnotation(a);
    }
    
    public String getAnnotation(){
      return getExtendedModel().getAnnotation();
    }
    
    public String getModelID() {
        return DeploymentProvider.MODEL_ID;
    }
    

}

