package designexploder.editor.controllers.policies;

import designexploder.editor.graphics.ClassItemFigure;
import org.eclipse.gef.editpolicies.SelectionEditPolicy;

public class ClassItemSelectionEditPolicy extends SelectionEditPolicy {

    @Override
    protected void showSelection() {
        ((ClassItemFigure) getHostFigure()).setSelected(true);
    }

    @Override
    protected void hideSelection() {
        ((ClassItemFigure) getHostFigure()).setSelected(false);
    }

}
