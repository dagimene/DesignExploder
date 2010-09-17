

/*
 * Created on Fri Sep 17 14:59:59 ART 2010
 *
 * Generated by GEMS 
 */
 
package org.gems.test.deployment.deployment;

import org.eclipse.gmt.gems.Memento;

public class MementoImpl implements Memento {

	private org.gems.test.deployment.emf.deployment.Memento model_;
	
	public MementoImpl(){		
	}
	
	public MementoImpl(org.gems.test.deployment.emf.deployment.Memento model){
		model_ = model;
	}
	
	public org.gems.test.deployment.emf.deployment.Memento getModel(){
		if(model_ == null){
			model_ = org.gems.test.deployment.emf.deployment.impl.DeploymentFactoryImpl.eINSTANCE.createMemento();
		}
		return model_;
	}
	public void setId(String id){
		getModel().setId(id);
	}
	
	public String getId(){
		return getModel().getId();
	}
	
	public void setValue(String name, String value){
		org.gems.test.deployment.emf.deployment.MementoValue mv = org.gems.test.deployment.emf.deployment.impl.DeploymentFactoryImpl.eINSTANCE.createMementoValue();
		mv.setName(name);
		mv.setValue(value);
		getModel().getData().add(mv);
	}
	public String getValue(String name){
		for(Object curr : getModel().getData()){
			org.gems.test.deployment.emf.deployment.MementoValue mv = (org.gems.test.deployment.emf.deployment.MementoValue)curr;
			if(mv.getName().equals(name)){
				return mv.getValue();
			}
		}
		return null;
	}
    
}

