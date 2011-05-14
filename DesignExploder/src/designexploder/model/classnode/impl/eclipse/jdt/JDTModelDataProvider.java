package designexploder.model.classnode.impl.eclipse.jdt;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;

import designexploder.model.classnode.build.ClassNodeModelDataProvider;

@SuppressWarnings("unchecked")
public class JDTModelDataProvider implements ClassNodeModelDataProvider {

	@SuppressWarnings("rawtypes")
	private static final Iterator EMPTY_ITERATOR = Collections.emptySet().iterator();

	private IJavaProject project;
	
	public JDTModelDataProvider(IJavaProject project) {
		this.project = project;
	}

	@Override
	public Iterator<NodeDataProvider> getNodes() {
		return new NodesIterator();
	}

	@Override
	public Iterator<ClassConnectionDataProvider> getConnections() {
		return EMPTY_ITERATOR;
	}

	class NodesIterator implements Iterator<NodeDataProvider> {
		
		//Iterator<IProject> projects;
		Iterator<IPackageFragment> packages;
		Iterator<ICompilationUnit> compilationUnits = EMPTY_ITERATOR;
		Iterator<IType> types = EMPTY_ITERATOR;
		
		IType next;
		
		NodesIterator() {
			//IWorkspace workspace = ResourcesPlugin.getWorkspace();
			//IWorkspaceRoot root = workspace.getRoot();
			//projects = Arrays.asList(root.getProjects()).iterator();
			 try {
				packages = Arrays.asList(project.getPackageFragments()).iterator();
			} catch (JavaModelException e) {
				e.printStackTrace();
				packages = EMPTY_ITERATOR;
			}
			loadNext();
		}
		
		private void loadNext() {
			next = null;
			while(next == null) {
				if(types.hasNext()) {
					try {
						IType nextType = types.next();
						if(!nextType.getFullyQualifiedName('$').contains("$") && !nextType.isAnnotation()) {
							next = nextType;
						}
					} catch (JavaModelException e) {
						e.printStackTrace();
					}
				} else if(compilationUnits.hasNext()) {
					try {
						types = Arrays.asList(compilationUnits.next().getTypes()).iterator();
					} catch (JavaModelException e) {
						e.printStackTrace();
					}
				} else if(packages.hasNext()) {
					try {
						IPackageFragment nextPackage = packages.next();
						if(nextPackage.getKind() == IPackageFragmentRoot.K_SOURCE) {
							compilationUnits = Arrays.asList(nextPackage.getCompilationUnits()).iterator();
						}
					} catch (JavaModelException e) {
						e.printStackTrace();
					}
				/*} else if(projects.hasNext()) {
					IProject nextProject = projects.next();
					try {
						if(nextProject.isNatureEnabled("org.eclipse.jdt.core.javanature")) {
							packages = Arrays.asList(JavaCore.create(nextProject).getPackageFragments()).iterator();
						}
					} catch (CoreException e) {
						e.printStackTrace();
					}*/
				} else {
					break;
				}
			}
		}
		
		@Override
		public boolean hasNext() {
			return next != null;
		}

		@Override
		public NodeDataProvider next() {
			JDTNodeDataProvider nodeDataProvider = new JDTNodeDataProvider(next);
			loadNext();
			return nodeDataProvider;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
}
