package modyfingelement.handlers;


import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class ModifyHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot root = workspace.getRoot();
		IProject[] projects = root.getProjects();
		for(IProject project: projects) {
			try {
				if(project.isOpen() & project.isNatureEnabled(JavaCore.NATURE_ID)) {
					createPackage(project);
				}
			}catch(CoreException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	private void createPackage(IProject project) throws JavaModelException {
		IJavaProject javaProject = JavaCore.create(project);
		IFolder folder = project.getFolder("src");
		IPackageFragmentRoot scrFolder = javaProject.getPackageFragmentRoot(folder);
		IPackageFragment fragment = scrFolder.createPackageFragment(project.getName(), true,null);
		
	}
}
