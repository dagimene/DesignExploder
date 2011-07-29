package designexploder.editor.tools;

import java.util.ArrayList;
import java.util.List;

public class ListenableCreationTool extends ResizeAwareCreationTool implements ListenableTool {

	private List<ToolActivationListener> activationListeners = new ArrayList<ToolActivationListener>();

	public void addActivationListener(ToolActivationListener listener) {
		activationListeners.add(listener);
	}

	public void removeActivationListener(ToolActivationListener listener) {
		activationListeners.remove(listener);
	}

	@Override
	public void activate() {
		super.activate();
		for (ToolActivationListener activationListener : activationListeners) {
			activationListener.setToolActive(true, this);
		}
	}
	
	@Override
	public void deactivate() {
		super.deactivate();
		for (ToolActivationListener activationListener : activationListeners) {
			activationListener.setToolActive(false, this);
		}
	}
}
