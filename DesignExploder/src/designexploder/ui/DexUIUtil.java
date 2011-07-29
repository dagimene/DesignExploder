package designexploder.ui;

import org.eclipse.core.resources.IFolder;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.part.EditorPart;
import org.springframework.ide.eclipse.beans.core.model.IBeansConfig;
import org.springframework.ide.eclipse.beans.ui.wizards.NewBeansConfigWizard;

public class DexUIUtil {

	public static IBeansConfig openNewBeansConfigWizard(EditorPart part, IFolder suggestedFolder) {
		NewBeansConfigWizard wizard = new NewBeansConfigWizard();
		IWorkbenchWindow workbenchWindow = part.getSite().getWorkbenchWindow();
		wizard.init(workbenchWindow.getWorkbench(), new StructuredSelection(suggestedFolder));
		WizardDialog dialog = new WizardDialog(workbenchWindow.getShell(), wizard);
		dialog.create();
		dialog.setBlockOnOpen(true);
		return dialog.open() == Dialog.OK ? wizard.getNewConfig() : null;
	}
	

	
}
