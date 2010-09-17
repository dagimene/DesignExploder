
package org.gems.test.deployment.deployment;




import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

public class GemsCreationWizard extends Wizard implements INewWizard {
	private GemsWizardPage logicPage = null;
	private IStructuredSelection selection;
	private IWorkbench workbench;

public GemsCreationWizard(){
	
}
	
public void addPages(){
	logicPage = new GemsWizardPage(workbench,selection);
	addPage(logicPage);
}

public void init(IWorkbench aWorkbench,IStructuredSelection currentSelection) {
	workbench = aWorkbench;
	selection = currentSelection;
}

public boolean performFinish(){
	return logicPage.finish();
}

}