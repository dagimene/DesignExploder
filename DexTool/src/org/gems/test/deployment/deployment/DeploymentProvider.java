

/*
 * Created on Fri Sep 17 14:59:59 ART 2010
 *
 * Generated by GEMS 
 */
 
package org.gems.test.deployment.deployment;

import org.eclipse.gmt.gems.ElementGroup;
import org.eclipse.gmt.gems.ElementGroupImpl;
import org.eclipse.gmt.gems.ConstraintsChecker;
import org.eclipse.gmt.gems.GenericElementFactory;
import org.eclipse.gmt.gems.GraphicsProvider;
import org.eclipse.gmt.gems.LabelProvider;
import org.eclipse.gmt.gems.ModelElementFactory;
import org.eclipse.gmt.gems.ModelProvider;
import org.eclipse.gmt.gems.ModelRepository;
import org.eclipse.gmt.gems.PaletteProvider;
import org.eclipse.gmt.gems.model.Atom;
import org.eclipse.gmt.gems.model.Model;
import org.eclipse.emf.ecore.EPackage;


/**
 * 
 */
 
public class DeploymentProvider implements ModelProvider, org.eclipse.gmt.gems.model.EMFModelProvider{
    public static final String MODEL_ID = "http://www.sf.net/projects/gems/dsml/deployment";
    private GraphicsProviderImpl graphicsProvider_ = new GraphicsProviderImpl();
    private LabelProviderImpl labelProvider_ = new LabelProviderImpl();
    private GenericElementFactory factory_ = new GenericElementFactory();
    private DeploymentPaletteProvider paletteProvider_ = new DeploymentPaletteProvider();
    private DeploymentConstraintsChecker checker_ = new DeploymentConstraintsChecker();
    private static DeploymentProvider instance_;
    
    
    public static DeploymentProvider getInstance(){
    	if(instance_ == null){
    		instance_ = new DeploymentProvider();
        	ModelRepository.getInstance().registerModelProvider(instance_);
    	}
    	return instance_;
    }
    
	private class RootModel extends Model{
		
		public Atom[] getChildAtoms() {
			
			Atom[] atoms = {
				
			};
			return atoms;
		}
		public org.eclipse.gmt.gems.model.Model[] getChildModels() {
			
			Model[] models = {
			     
				   new DeploymentPlan(),
				   new Component(),
				   new Node(),
				   new NodeResource(),
				   new ComponentProperty()};
			return models;
		}
        public String getModelID() {
            return MODEL_ID;
        }
}
	

	/* (non-Javadoc)
	 * @see org.eclipse.gmt.gems.ModelProvider#getRootModel()
	 */
	public org.eclipse.gmt.gems.model.Model getRootModel() {
		
		return new RootModel();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmt.gems.ModelProvider#getGraphicsProvider()
	 */
	public GraphicsProvider getGraphicsProvider() {
		
		return graphicsProvider_;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmt.gems.ModelProvider#getLabelProvider()
	 */
	public LabelProvider getLabelProvider() {
		
		return labelProvider_;
	}

	/**
	 * 
	 */
	public DeploymentProvider() {
		super();
	}

	public ModelElementFactory getModelElementFactory() {
		return factory_;
	}


    public String getModelID() {
        return MODEL_ID;
    }
    
    public ConstraintsChecker getConstraintsChecker() {
        return checker_;
    }
    
    public PaletteProvider getPaletteProvider(){
        return paletteProvider_;
    }
    
    public ElementGroup[] getElementGroups() {
		ElementGroup[] els = {
			new ElementGroupImpl(getRootModel().getChildAtoms(),"Atoms"),
			new ElementGroupImpl(getRootModel().getChildModels(),"Models")
		};
		return els;
	}
	
	public EPackage getEMFPackage(){
		return org.gems.test.deployment.emf.deployment.impl.DeploymentPackageImpl.eINSTANCE;
	}
 
}

