package designexploder.model.extension.classnode.impl.eclipse.jdt;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;

class ITypesIterator implements Iterator<IType> {
	
	@SuppressWarnings("rawtypes")
	public static final Iterator EMPTY_ITERATOR = Collections.emptySet().iterator();

	private Iterator<IPackageFragment> packages;
	private Iterator<ICompilationUnit> compilationUnits;
	private Iterator<IType> types;
	
	private IType next;
	
	public ITypesIterator(IJavaProject project) throws JavaModelException {
		this(Arrays.asList(project.getAllPackageFragmentRoots()));
	}
	
	public ITypesIterator(IPackageFragmentRoot packageRoot) throws JavaModelException {
		this(Collections.singletonList(packageRoot));
	}
	
	@SuppressWarnings("unchecked")
	private ITypesIterator(List<IPackageFragmentRoot> packagesRoots) throws JavaModelException {
		compilationUnits = EMPTY_ITERATOR;
		types = EMPTY_ITERATOR;

		List<IPackageFragment> packagesList = new LinkedList<IPackageFragment>();
		for (IPackageFragmentRoot root : packagesRoots) {
			for (IJavaElement fragment : root.getChildren()) {
				packagesList.add((IPackageFragment) fragment);
			}
		}
		packages = packagesList.iterator();
		
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
	public IType next() {
		IType tmp = next;
		loadNext();
		return tmp;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}
}