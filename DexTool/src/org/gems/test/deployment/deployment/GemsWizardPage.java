
package org.gems.test.deployment.deployment;



import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.util.Hashtable;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;
import org.eclipse.ui.ide.IDE;

import org.eclipse.gmt.gems.InitialContentProvider;
import org.eclipse.gmt.gems.ModelInstance;
import org.eclipse.gmt.gems.ModelInstanceRepository;
import org.eclipse.gmt.gems.ModelRepository;
import org.eclipse.gmt.gems.PluginUtilities;


public class GemsWizardPage 
	extends WizardNewFileCreationPage 
	implements SelectionListener
{


private IWorkbench	workbench;
private static int count = 1;


public GemsWizardPage(IWorkbench aWorkbench, IStructuredSelection selection) {
	super("Deployment DSML", selection);  
	this.setTitle("Deployment DSML");
	this.setDescription("Create a Deployment DSML instance");
	this.setImageDescriptor(ImageDescriptor.createFromFile(getClass(),"icons/logicbanner.gif"));  
	this.workbench = aWorkbench;
}

public void createControl(Composite parent) {
	super.createControl(parent);
	this.setFileName("Deployment" + count + ".dmod"); 
	
	Composite composite = (Composite)getControl();

	new Label(composite,SWT.NONE);

	setPageComplete(validatePage());
}

protected InputStream getInitialContents() {
	ByteArrayInputStream bais = null;
	try{

			String intial = "<?xml version=\"1.0\" encoding=\"ASCII\"?>\n<deployment:Root xmlns:deployment=\"http://www.sf.net/projects/gems/dsml/deployment\"></deployment:Root>";
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            java.io.OutputStreamWriter out = new java.io.OutputStreamWriter(baos);
            out.write(intial);
            out.flush();
  			out.close();
            baos.close();
            bais = new ByteArrayInputStream(baos.toByteArray());
            bais.close();
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	return bais;
}

public boolean finish() {
	IFile newFile = createNewFile();
	if (newFile == null) 
		return false;  

    Hashtable useroptions = new Hashtable();
	InitialContentProvider[] initcontents = PluginUtilities.getInitialContentProviders(DeploymentProvider.MODEL_ID);
	for(InitialContentProvider init : initcontents)
		init.provideContent(DeploymentProvider.getInstance(),newFile, useroptions);
	

	try {
		IWorkbenchWindow dwindow = workbench.getActiveWorkbenchWindow();
		IWorkbenchPage page = dwindow.getActivePage();
		if (page != null)
			IDE.openEditor(page, newFile, true);
	} 
	catch (org.eclipse.ui.PartInitException e) {
		e.printStackTrace();
		return false;
	}
	count++;
	return true;
}

/**
 * @see org.eclipse.swt.events.SelectionListener#widgetSelected(SelectionEvent)
 */
public void widgetSelected(SelectionEvent e) {
	
}

public void widgetDefaultSelected(SelectionEvent e) {
}

}