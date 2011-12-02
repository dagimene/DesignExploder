package designexploder.model.extension.classnode.build;

import designexploder.editor.DexDiagramEditor;
import designexploder.util.DexUtils;
import org.eclipse.jdt.core.*;
import org.eclipse.swt.widgets.Display;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.eclipse.jdt.core.IJavaElement.*;
import static org.eclipse.jdt.core.IJavaElement.TYPE_PARAMETER;

/**
* Created by IntelliJ IDEA.
* User: dagimene
* Date: 11/24/11
* Time: 2:41 PM
* To change this template use File | Settings | File Templates.
*/
public class JDTUpdateListener implements IElementChangedListener {

    private IJavaProject project;
    private DexDiagramEditor dexDiagramEditor;

    public JDTUpdateListener(DexDiagramEditor dexDiagramEditor, IJavaProject project) {
        this.dexDiagramEditor = dexDiagramEditor;
        this.project = project;
    }

    private static final Set<Integer> listenedElements = new HashSet<Integer>(Arrays.asList(
            COMPILATION_UNIT, CLASS_FILE, FIELD, INITIALIZER, LOCAL_VARIABLE, METHOD, PACKAGE_DECLARATION,
            PACKAGE_FRAGMENT, PACKAGE_FRAGMENT_ROOT, TYPE, TYPE_PARAMETER
    ));

    @Override
    public void elementChanged(ElementChangedEvent event) {
        IPackageFragmentRoot beansPackageRoot = DexUtils.getBeansPackageRoot(project);
        IJavaElementDelta delta = event.getDelta();
        IJavaElement javaElement = delta.getElement();
        int elementType = javaElement.getElementType();
        if(listenedElements.contains(elementType) &&
                delta.getKind() != IJavaElementDelta.CHANGED ||
                (delta.getFlags() & (0xFFFFFFFF - (IJavaElementDelta.F_AST_AFFECTED |
                        IJavaElementDelta.F_FINE_GRAINED | IJavaElementDelta.F_CONTENT))) != 0) {
            if(elementType == IJavaElement.JAVA_MODEL || elementType == IJavaElement.JAVA_PROJECT) {
                javaElement = null;
                for (IJavaElementDelta child : delta.getChangedChildren()) {
                    if(child.getElement().getElementType() == IJavaElement.JAVA_PROJECT) {
                        for (IJavaElementDelta child2 : child.getChangedChildren()) {
                            if(child2.getElement().equals(beansPackageRoot)) {
                                javaElement = child2.getElement();
                                break;
                            }
                        }
                        if(javaElement != null) {
                            break;
                        }
                    } else if(child.getElement().equals(beansPackageRoot)) {
                        javaElement = child.getElement();
                        break;
                    }
                }
            } else {
                while(javaElement != null && !javaElement.equals(beansPackageRoot)) {
                    javaElement = javaElement.getParent();
                }
            }
            if(javaElement != null) {
                dexDiagramEditor.reloadModel(project);
            }
        }
    }
}
