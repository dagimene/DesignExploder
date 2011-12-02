/*******************************************************************************
 * Copyright (c) 2000, 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package designexploder.editor;

import java.util.*;

import designexploder.editor.actions.*;
import designexploder.model.extension.IoC.IoCModelNatures;
import designexploder.model.extension.classnode.build.JDTUpdateListener;
import org.eclipse.core.internal.resources.Project;
import org.eclipse.gef.editparts.ScalableRootEditPart;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.ui.actions.*;
import org.eclipse.jdt.core.*;
import org.eclipse.swt.widgets.Composite;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.*;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.ui.views.properties.PropertySheetPage;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;

import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.commands.CommandStackListener;
import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;
import org.eclipse.gef.ui.parts.SelectionSynchronizer;
import org.eclipse.gef.ui.properties.UndoablePropertySheetEntry;

import designexploder.editor.controllers.IoCModelUpdateListener;
import designexploder.editor.controllers.factory.DexDiagramPartsFactory;
import designexploder.editor.tools.EditorToolManager;
import designexploder.editor.tools.TriggerableSelectionTool;
import designexploder.model.NodeContainer;
import designexploder.model.build.ChainedModelBuilder;
import designexploder.model.build.ModelBasicDataSetter;
import designexploder.model.build.ModelBuilder;
import designexploder.model.extension.IoC.build.IoCIDsNormalizer;
import designexploder.model.extension.IoC.impl.spring.SpringCodeGenerator;
import designexploder.model.extension.IoC.impl.spring.SpringContextsWriter;
import designexploder.model.extension.IoC.impl.spring.SpringModelBuilder;
import designexploder.model.extension.classnode.build.ClassMembersCondensatorBuilder;
import designexploder.model.extension.classnode.build.ClassRelationsModelBuilder;
import designexploder.model.extension.classnode.impl.eclipse.jdt.JDTModelBuilder;
import designexploder.model.impl.BasicModelFactory;
import designexploder.model.impl.build.XMLBasicModelDataProvider;
import designexploder.model.impl.build.XMLBasicModelWriter;
import designexploder.util.DexUtils;
import designexploder.util.EclipseUtil;

import static org.eclipse.jdt.core.IJavaElement.*;
import static org.eclipse.jdt.core.IJavaElement.TYPE_PARAMETER;

/**
 * This class serves as a quick starting point for clients who are new to GEF.
 * It will create an Editor containing a single GraphicalViewer as its control.
 * <P>z
 * <EM>IMPORTANT</EM>This class should only be used as a reference for creating
 * your own EditorPart implementation. This class will not suit everyone's
 * needs, and may change in the future. Clients may copy the implementation.
 * 
 * @author hudsonr
 */
public class DexDiagramEditor extends EditorPart implements
		CommandStackListener, ISelectionListener {

	private DefaultEditDomain editDomain;
	private GraphicalViewer graphicalViewer;
	private ActionRegistry actionRegistry;
	private SelectionSynchronizer synchronizer;
	private EditorToolManager toolManager;
	private List<String> selectionActions = new ArrayList<String>();
	private List<String> stackActions = new ArrayList<String>();
	private List<String> propertyActions = new ArrayList<String>();
    private IElementChangedListener jdtListener;

    /**
	 * Constructs the editor part
	 */
	public DexDiagramEditor() {
		DefaultEditDomain editDomain = new DefaultEditDomain(this);
		editDomain.setDefaultTool(new TriggerableSelectionTool());
		setEditDomain(editDomain);
		toolManager = new EditorToolManager(getEditDomain());
	}

	/**
	 * Override to set the contents of the GraphicalViewer after it has been
	 * created.
	 * 
	 * @see #createGraphicalViewer(Composite)
	 */
	protected void initializeGraphicalViewer() {
		IFile file = getEditorInput().getFile();
		IJavaProject project = EclipseUtil.getJavaProject(file.getProject());
		
		DexUtils.initializeDexProjectStructure(project);

        setGraphicalViewerContents(project);

        jdtListener = new JDTUpdateListener(this, project);
        JavaCore.addElementChangedListener(jdtListener);
	}

    private void setGraphicalViewerContents(IJavaProject project) {
        IFile file = getEditorInput().getFile();

        ChainedModelBuilder modelBuilder = new ChainedModelBuilder();
        JDTModelBuilder jdtMB = JDTModelBuilder.create(DexUtils.getBeansPackageRoot(project));
        if(jdtMB != null) {
            modelBuilder.addBuilder(jdtMB);
        }
        modelBuilder.addBuilder(new ClassMembersCondensatorBuilder());
        modelBuilder.addBuilder(new ClassRelationsModelBuilder());
        ModelBuilder springMB = SpringModelBuilder.create(DexUtils.getContextsPackageRoot(project));
        if(springMB != null) {
            modelBuilder.addBuilder(springMB);
        }
        modelBuilder.addBuilder(new ModelBasicDataSetter(new XMLBasicModelDataProvider(file)));
        modelBuilder.addBuilder(new IoCModelUpdateListener());

        getGraphicalViewer().setContents(modelBuilder.build(BasicModelFactory.getInstance().createNodeContainer()));
    }


    public void reloadModel(final IJavaProject project) {
        Display.getDefault().asyncExec(new Runnable() {
            @Override
            public void run() {
                doSave(null);
                setGraphicalViewerContents(project);
                getCommandStack().flush();
            }
        });
    }

    @Override
	public void doSave(IProgressMonitor monitor) {
		IFile file = getEditorInput().getFile();
		IJavaProject project = EclipseUtil.getJavaProject(file.getProject());

		ChainedModelBuilder modelBuilder = new ChainedModelBuilder();
		modelBuilder.addBuilder(new IoCIDsNormalizer());
		modelBuilder.addBuilder(new XMLBasicModelWriter(file));
		modelBuilder.addBuilder(SpringCodeGenerator.create(DexUtils.getGeneratedPackageRoot(project)));
		modelBuilder.addBuilder(SpringContextsWriter.create(DexUtils.getContextsPackageRoot(project)));
		//modelBuilder.addBuilder(DexRuntimeGenerator.create(DexUtils.getGeneratedPackageRoot(project)));
		NodeContainer diagram = (NodeContainer) getGraphicalViewer().getContents().getModel();
		modelBuilder.build(diagram);

        getCommandStack().markSaveLocation();
	}

	public NodeContainer getModel() {
		return (NodeContainer) getGraphicalViewer().getContents().getModel();
	}

	/**
	 * When the command stack changes, the actions interested in the command
	 * stack are updated.
	 * 
	 * @param event
	 *            the change event
	 */
	public void commandStackChanged(EventObject event) {
		updateActions(stackActions);
        firePropertyChange(PROP_DIRTY);
	}

	public void forceUpdateSelectionActions() {
		updateActions(selectionActions);
	}

	/**
	 * Called to configure the graphical viewer before it receives its contents.
	 * This is where the root editpart should be configured. Subclasses should
	 * extend or override this method as needed.
	 */
	protected void configureGraphicalViewer() {
		getGraphicalViewer().getControl().setBackground(ColorConstants.listBackground);
		getGraphicalViewer().setContextMenu(new DexDiagramEditorContextMenu(getGraphicalViewer(), getActionRegistry()));
		getGraphicalViewer().setEditPartFactory(new DexDiagramPartsFactory());
	}

	/**
	 * Creates actions for this editor. Subclasses should override this method
	 * to create and register actions with the {@link ActionRegistry}.
	 */
	protected void createActions() {
		ActionRegistry registry = getActionRegistry();
		IAction action;

		action = new UndoAction(this);
		registry.registerAction(action);
		getStackActions().add(action.getId());

		action = new RedoAction(this);
		registry.registerAction(action);
		getStackActions().add(action.getId());

		action = new SelectAllAction(this);
		registry.registerAction(action);

		action = new DeleteAction((IWorkbenchPart) this);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());

		action = new SaveAction(this);
		registry.registerAction(action);
		getPropertyActions().add(action.getId());
		
		action = new CreateApplicationContextAction(this);
		registry.registerAction(action);

        action = new ShowInheritedMembersAction(this);
        registry.registerAction(action);
        getSelectionActions().add(action.getId());

		action = new TransformToBeanAction(this);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());

		action = new TransformToFacadeAction(this);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());

        for(IoCModelNatures nature : TransformToIoCAwareMethodAction.getSupportedNatures()) {
            action = new TransformToIoCAwareMethodAction(this, nature);
            registry.registerAction(action);
            getSelectionActions().add(action.getId());
        }

		action = new InjectClassItemAction(this);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());

		registry.registerAction(new PrintAction(this));
	}

	/**
	 * Creates the GraphicalViewer on the specified <code>Composite</code>.
	 * 
	 * @param parent
	 *            the parent composite
	 */
	protected void createGraphicalViewer(Composite parent) {
		GraphicalViewer viewer = new ScrollingGraphicalViewer();
		viewer.createControl(parent);
		setGraphicalViewer(viewer);
		configureGraphicalViewer();
		hookGraphicalViewer();
		initializeGraphicalViewer();
	}

	/**
	 * Realizes the Editor by creating it's Control.
	 * <P>
	 * WARNING: This method may or may not be called by the workbench prior to
	 * {@link #dispose()}.
	 * 
	 * @param parent
	 *            the parent composite
	 */
	public void createPartControl(Composite parent) {
		createGraphicalViewer(parent);
	}

	/**
	 * @see org.eclipse.ui.IWorkbenchPart#dispose()
	 */
	public void dispose() {
        JavaCore.removeElementChangedListener(jdtListener);
		getCommandStack().removeCommandStackListener(this);
		getSite().getWorkbenchWindow().getSelectionService()
				.removeSelectionListener(this);
		getEditDomain().setActiveTool(null);
		getActionRegistry().dispose();
		super.dispose();
	}

	/**
	 * Does nothing be default. This method should be overridden if
	 * {@link #isSaveAsAllowed()} has been overridden to return
	 * <code>true</code>.
	 * 
	 * @see org.eclipse.ui.ISaveablePart#doSaveAs()
	 */
	public void doSaveAs() {
		throw new UnsupportedOperationException("cannot do SaveAs");
	}

	/**
	 * @see org.eclipse.ui.part.WorkbenchPart#firePropertyChange(int)
	 */
	protected void firePropertyChange(int property) {
		super.firePropertyChange(property);
		updateActions(propertyActions);
	}

	/**
	 * Lazily creates and returns the action registry.
	 * 
	 * @return the action registry
	 */
	protected ActionRegistry getActionRegistry() {
		if (actionRegistry == null)
			actionRegistry = new ActionRegistry();
		return actionRegistry;
	}

	/**
	 * Returns the adapter for the specified key.
	 * 
	 * <P>
	 * <EM>IMPORTANT</EM> certain requests, such as the property sheet, may be
	 * made before or after {@link #createPartControl(Composite)} is called. The
	 * order is unspecified by the Workbench.
	 * 
	 * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
	 */
	public Object getAdapter(@SuppressWarnings("rawtypes") Class type) {
		if (type == org.eclipse.ui.views.properties.IPropertySheetPage.class) {
			PropertySheetPage page = new PropertySheetPage();
			page.setRootEntry(new UndoablePropertySheetEntry(getCommandStack()));
			return page;
		}
		if (type == GraphicalViewer.class)
			return getGraphicalViewer();
		if (type == CommandStack.class)
			return getCommandStack();
		if (type == ActionRegistry.class)
			return getActionRegistry();
		if(type == EditorToolManager.class)
			return getToolManager();
		if (type == EditPart.class && getGraphicalViewer() != null)
			return getGraphicalViewer().getRootEditPart();
		if (type == IFigure.class && getGraphicalViewer() != null)
			return ((GraphicalEditPart) getGraphicalViewer().getRootEditPart())
					.getFigure();
        if(type == ZoomManager.class && getGraphicalViewer() != null)
            return getZoomManager();

		return super.getAdapter(type);
	}

    private ZoomManager getZoomManager() {
        return (((ScalableRootEditPart)getGraphicalViewer().getRootEditPart())).getZoomManager();
    }

    public EditorToolManager getToolManager() {
		return toolManager;
	}

	/**
	 * Returns the command stack.
	 * 
	 * @return the command stack
	 */
	protected CommandStack getCommandStack() {
		return getEditDomain().getCommandStack();
	}

	/**
	 * Returns the edit domain.
	 * 
	 * @return the edit domain
	 */
	protected DefaultEditDomain getEditDomain() {
		return editDomain;
	}

	/**
	 * Returns the graphical viewer.
	 * 
	 * @return the graphical viewer
	 */
	protected GraphicalViewer getGraphicalViewer() {
		return graphicalViewer;
	}

	/**
	 * Returns the list of {@link IAction IActions} dependant on property
	 * changes in the Editor. These actions should implement the
	 * {@link UpdateAction} interface so that they can be updated in response to
	 * property changes. An example is the "Save" action.
	 * 
	 * @return the list of property-dependant actions
	 */
	protected List<String> getPropertyActions() {
		return propertyActions;
	}

	/**
	 * Returns the list of <em>IDs</em> of Actions that are dependant on changes
	 * in the workbench's {@link ISelectionService}. The associated Actions can
	 * be found in the action registry. Such actions should implement the
	 * {@link UpdateAction} interface so that they can be updated in response to
	 * selection changes.
	 * 
	 * @see #updateActions(List)
	 * @return the list of selection-dependant action IDs
	 */
	protected List<String> getSelectionActions() {
		return selectionActions;
	}

	/**
	 * Returns the selection syncronizer object. The synchronizer can be used to
	 * sync the selection of 2 or more EditPartViewers.
	 * 
	 * @return the syncrhonizer
	 */
	protected SelectionSynchronizer getSelectionSynchronizer() {
		if (synchronizer == null)
			synchronizer = new SelectionSynchronizer();
		return synchronizer;
	}

	/**
	 * Returns the list of <em>IDs</em> of Actions that are dependant on the
	 * CommmandStack's state. The associated Actions can be found in the action
	 * registry. These actions should implement the {@link UpdateAction}
	 * interface so that they can be updated in response to command stack
	 * changes. An example is the "undo" action.
	 * 
	 * @return the list of stack-dependant action IDs
	 */
	protected List<String> getStackActions() {
		return stackActions;
	}

	/**
	 * Hooks the GraphicalViewer to the rest of the Editor. By default, the
	 * viewer is added to the SelectionSynchronizer, which can be used to keep 2
	 * or more EditPartViewers in sync. The viewer is also registered as the
	 * ISelectionProvider for the Editor's PartSite.
	 */
	protected void hookGraphicalViewer() {
		getSelectionSynchronizer().addViewer(getGraphicalViewer());
		getSite().setSelectionProvider(getGraphicalViewer());
        getZoomManager().setZoomLevels(new double[]{0.25, 0.5, 0.75, 1, 1.5, 2});
        getZoomManager().setZoomLevelContributions(Arrays.asList(ZoomManager.FIT_ALL));
        getZoomManager().setZoomAnimationStyle(ZoomManager.ANIMATE_ZOOM_IN_OUT);
	}

	/**
	 * Sets the site and input for this editor then creates and initializes the
	 * actions. Subclasses may extend this method, but should always call
	 * <code>super.init(site, input)
	 * </code>.
	 * 
	 * @see org.eclipse.ui.IEditorPart#init(IEditorSite, IEditorInput)
	 */
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		if(!(input instanceof IFileEditorInput)) {
			throw new PartInitException("Invalid editor input of type "+input.getClass()+". Must be a IFileEditorInput.");
		}
		
		setSite(site);
		setInput(input);
		getCommandStack().addCommandStackListener(this);
		getSite().getWorkbenchWindow().getSelectionService()
				.addSelectionListener(this);
		initializeActionRegistry();
	}
	
	@Override
	public IFileEditorInput getEditorInput() {
		return (IFileEditorInput) super.getEditorInput();
	}

	/**
	 * Initializes the ActionRegistry. This registry may be used by
	 * {@link ActionBarContributor ActionBarContributors} and/or
	 * {@link ContextMenuProvider ContextMenuProviders}.
	 * <P>
	 * This method may be called on Editor creation, or lazily the first time
	 * {@link #getActionRegistry()} is called.
	 */
	protected void initializeActionRegistry() {
		createActions();
		updateActions(propertyActions);
		updateActions(stackActions);
	}

	/**
	 * Returns <code>true</code> if the command stack is dirty
	 * 
	 * @see org.eclipse.ui.ISaveablePart#isDirty()
	 */
	public boolean isDirty() {
		return getCommandStack().isDirty();
	}

	/**
	 * Returns <code>false</code> by default. Subclasses must return
	 * <code>true</code> to allow {@link #doSaveAs()} to be called.
	 * 
	 * @see org.eclipse.ui.ISaveablePart#isSaveAsAllowed()
	 */
	public boolean isSaveAsAllowed() {
		return false;
	}

	/**
	 * @see org.eclipse.ui.ISelectionListener#selectionChanged(IWorkbenchPart,
	 *      ISelection)
	 */
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		// If not the active editor, ignore selection changed.
		if (this.equals(getSite().getPage().getActiveEditor()))
			updateActions(selectionActions);
	}

	/**
	 * Sets the ActionRegistry for this EditorPart.
	 * 
	 * @param registry
	 *            the registry
	 */
	protected void setActionRegistry(ActionRegistry registry) {
		actionRegistry = registry;
	}

	/**
	 * Sets the EditDomain for this EditorPart.
	 * 
	 * @param ed
	 *            the domain
	 */
	protected void setEditDomain(DefaultEditDomain ed) {
		this.editDomain = ed;
	}

	/**
	 * @see org.eclipse.ui.IWorkbenchPart#setFocus()
	 */
	public void setFocus() {
		getGraphicalViewer().getControl().setFocus();
	}

	/**
	 * Sets the graphicalViewer for this EditorPart.
	 * 
	 * @param viewer
	 *            the graphical viewer
	 */
	protected void setGraphicalViewer(GraphicalViewer viewer) {
		getEditDomain().addViewer(viewer);
		this.graphicalViewer = viewer;
	}

	/**
	 * A convenience method for updating a set of actions defined by the given
	 * List of action IDs. The actions are found by looking up the ID in the
	 * {@link #getActionRegistry() action registry}. If the corresponding action
	 * is an {@link UpdateAction}, it will have its <code>update()</code> method
	 * called.
	 * 
	 * @param actionIds
	 *            the list of IDs to update
	 */
	protected void updateActions(List<String> actionIds) {
		ActionRegistry registry = getActionRegistry();
		Iterator<String> iter = actionIds.iterator();
		while (iter.hasNext()) {
			IAction action = registry.getAction(iter.next());
			if (action instanceof UpdateAction)
				((UpdateAction) action).update();
		}
	}

}