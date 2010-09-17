
package org.gems.test.deployment.deployment;


import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gmt.gems.AbstractPaletteProvider;
import org.eclipse.gmt.gems.ModelProvider;
import org.eclipse.gmt.gems.PaletteProvider;

/**
 * @author Jules White
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DeploymentPaletteProvider extends AbstractPaletteProvider implements PaletteProvider{
    public PaletteRoot createPalette() {
        return super.createPalette();
    }
    public ModelProvider getModelProvider() {
        return DeploymentProvider.getInstance();
    }
}
