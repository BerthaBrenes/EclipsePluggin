package parseojdtt.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.text.Document;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class SampleHandler extends AbstractHandler {

		@Override
		public Object execute(ExecutionEvent event) throws ExecutionException {
			IWorkspace workspace = ResourcesPlugin.getWorkspace();
			IWorkspaceRoot root = workspace.getRoot();
			IProject[] projects = root.getProjects();
			for (IProject project: projects) {
				try {
					printProjectInfo(project);
				}catch(CoreException e) {
					e.printStackTrace();
				}
			}
			
			return null;
		}
		private void printProjectInfo(IProject project) throws CoreException,
	    JavaModelException{
			System.out.println("Working in project: " + project.getName());
			if(project.isNatureEnabled("org.eclipse.jdt.core.javanature")) {
				IJavaProject javaProject = JavaCore.create(project);
				printPackageInfos(javaProject);
			}
		}
		private void printPackageInfos(IJavaProject javaProject) throws JavaModelException {
			IPackageFragment [] packages = javaProject.getPackageFragments();
			for(IPackageFragment mypackages: packages) {
				if(mypackages.getKind() == IPackageFragmentRoot.K_SOURCE) {
					System.out.println("Package: "+ mypackages.getElementName());
					printIComplationUnitInfo(mypackages);
				}
			}
			
		}
		private void printIComplationUnitInfo(IPackageFragment mypackages) throws JavaModelException {
			for(ICompilationUnit unitu : mypackages.getCompilationUnits()) {
			kprintICompilationUnitDetails( unitu);
			}
			}
		private void kprintICompilationUnitDetails(ICompilationUnit unit) throws JavaModelException{
			System.out.println("Source file: "+ unit.getElementName());
			Document doc = new Document(unit.getSource());
			System.out.println("Has number of lines"+ doc.getNumberOfLines());
			printlnIMethods(unit);
		}
		private void printlnIMethods(ICompilationUnit unit) throws JavaModelException {
			IType[] allTypes = unit.getAllTypes();
			for (IType type : allTypes) {
				printIMethodDetails(type);
			}
			
		}
		/**
		 * 
		 * @param type
		 * @throws JavaModelException
		 * method.getElementName(): nombre del metodo
		 * method.getSignature(): tipo de entrada
		 * method.getSource(): retorna todo el contenido del metodo 
		 * 
		 */
		private void printIMethodDetails(IType type) throws JavaModelException {
			IMethod[] methods = type.getMethods();
			for(IMethod method: methods) {
				System.out.println("Method name "+ method.getElementName());
				System.out.println("Signature "+ method.getSignature());
				System.out.println("Return Type "+ method.getReturnType());
				System.out.println("Conditional ");
			}
		}
	}
