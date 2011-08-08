package designexploder.editor.controllers.listeners;

public interface RefreshableEditPart {

	void refreshVisuals();
	
	void refreshChildren();
	
	void refreshSourceConnections();
	
	void refreshTargetConnections();
	
}
