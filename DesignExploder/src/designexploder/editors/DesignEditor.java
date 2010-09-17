package designexploder.editors;

import org.eclipse.ui.editors.text.TextEditor;

public class DesignEditor extends TextEditor {

	private ColorManager colorManager;

	public DesignEditor() {
		super();
		colorManager = new ColorManager();
		setSourceViewerConfiguration(new XMLConfiguration(colorManager));
		setDocumentProvider(new XMLDocumentProvider());
	}
	public void dispose() {
		colorManager.dispose();
		super.dispose();
	}

}
