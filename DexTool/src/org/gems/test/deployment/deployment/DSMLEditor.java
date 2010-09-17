
package org.gems.test.deployment.deployment;


import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.ui.palette.FlyoutPaletteComposite.FlyoutPreferences;
import org.eclipse.gmt.gems.GemsEditor;
import org.eclipse.gmt.gems.GemsPlugin;

/**
 * @author Jules White
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DSMLEditor extends GemsEditor {

    static {
        DeploymentPlugin.getDefault().getPreferenceStore().setDefault(
           PALETTE_SIZE, DEFAULT_PALETTE_SIZE);
    }
    /**
     * 
     */
    public DSMLEditor() {
        super();
        
    }
    
    protected String getModelID() {
   	 	return DeploymentProvider.MODEL_ID;
	}
    
    protected PaletteRoot getPaletteRoot() {
        if( root == null ){
            root = DeploymentProvider.getInstance().getPaletteProvider().createPalette();
        }
        return root;
    }
    
    protected FlyoutPreferences getPalettePreferences() {
        return new FlyoutPreferences() {
            public int getDockLocation() {
                return DeploymentPlugin.getDefault().getPreferenceStore()
                      .getInt(PALETTE_DOCK_LOCATION);
                
            }
            public int getPaletteState() {
                return DeploymentPlugin.getDefault().getPreferenceStore().getInt(PALETTE_STATE);
            }
            public int getPaletteWidth() {
                return DEFAULT_PALETTE_SIZE;
            }
            public void setDockLocation(int location) {
              DeploymentPlugin.getDefault().getPreferenceStore()
                      .setValue(PALETTE_DOCK_LOCATION, location);
            }
            public void setPaletteState(int state) {
              DeploymentPlugin.getDefault().getPreferenceStore()
                      .setValue(PALETTE_STATE, state);
            }
            public void setPaletteWidth(int width) {
              DeploymentPlugin.getDefault().getPreferenceStore()
                      .setValue(PALETTE_SIZE, width);
            }
        };
    }
    
     protected ClassLoader getClassLoaderForSerialization() {
        return getClass().getClassLoader();
    }
    

}
