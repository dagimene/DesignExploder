package designexploder.editor.tools;

import org.eclipse.gef.Tool;

public interface ListenableTool extends Tool {

	void addActivationListener(ToolActivationListener listener);
	
	void removeActivationListener(ToolActivationListener listener);

}
