

package parseoast.handlers;


import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;


public class GetInfo extends AbstractHandler {
	private static final String JDT_Nature = "org.eclipse.jdt.core.javanature";
	public static List condicionales;

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
	  IWorkbenchWindow window = PlatformUI.getWorkbench()
	              .getActiveWorkbenchWindow();
	  IWorkbenchPage activePage = window.getActivePage();

	  IEditorPart activeEditor = activePage.getActiveEditor();

	  if (activeEditor != null) {
	     IEditorInput input = activeEditor.getEditorInput();

	     IProject project = input.getAdapter(IProject.class);
	     if (project == null) {
	        IResource resource = input.getAdapter(IResource.class);
	        if (resource != null) {
	           project = resource.getProject();
	        }
	     }
	  }
			
			
	  return null;
	 }
	

	private void analyseMethods(IProject project) throws JavaModelException {
		IPackageFragment[] packages = JavaCore.create(project).getPackageFragments();
		for (IPackageFragment mypackage : packages) {
			if (mypackage.getKind() == IPackageFragmentRoot.K_SOURCE) {
				createAST(mypackage);
			}
		}
	}

	private void createAST(IPackageFragment mypackage) throws JavaModelException {
		for (ICompilationUnit unit : mypackage.getCompilationUnits()) {
			CompilationUnit parse = parse(unit);
			MethodVisitor visitor = new MethodVisitor();
			parse.accept(visitor);
			
			for (MethodDeclaration method : visitor.getMethods()) {
				System.out.println("Method name: " + method.getName() + "Return Type: " + method.getReturnType2());
				
				List arraySta = method.getBody().statements();
				System.out.println(arraySta.size());
				int i = 0;
				while( i != arraySta.size()) {
					String regex = method.getBody().statements().get(i).toString();
					String[] arrOfStr = regex.split(" ");
					if(method.getBody().VARIABLE_DECLARATION_STATEMENT != 0) {
						System.out.println(method.getBody().VARIABLE_DECLARATION_STATEMENT);
						System.out.println("una declaracion de metodos");
					}
					//System.out.println(regex);
					for (String a : arrOfStr) {
						 System.out.println("["+a+"]");
						if (a.equals("if")) {
							System.out.println("salio un if");
							//condicionales.add("if");
						} else if (a.equals("for")) {
							
							System.out.println("encontre un for");
							//condicionales.add("for");
						}
					}
					i ++;
				}
				break;
				// System.out.println("Pruebas"+ regex);

			}
		}

	}
	

	private static CompilationUnit parse(ICompilationUnit unit) {
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setSource(unit);
		parser.setResolveBindings(true);
		return (CompilationUnit) parser.createAST(null);
	}

}



