
package parseoast.handlers;

import java.util.Iterator;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.ThisExpression;
import org.eclipse.jdt.ui.IPackagesViewPart;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.jdt.debug.*;
import org.eclipse.jdt.internal.debug.core.breakpoints.*;



import listas.Lista;
//import org.osgi.framework.BundleContext;


public class GetInfo extends AbstractHandler {
	private static final String JDT_Nature = "org.eclipse.jdt.core.javanature";

	public String Currente;
	public String eleccion = "getNombre";
	//public DebugActivitor debug;
	public static Lista<String> condicionales = new Lista<String>();

	/**
	 * execute ejecuta el primer codigo que encuentra a raiz de un evento Con el
	 * activePage logra visualizar en cual clase esta ubicado
	 */
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
	/**

		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		IWorkbenchPage activePage = window.getActivePage();
		IEditorPart activeEditor = activePage.getActiveEditor();
		System.out.println(activeEditor.getTitle());
		Currente = activeEditor.getTitle();
		if (activeEditor != null) {
			IEditorInput input = activeEditor.getEditorInput();

			IProject project = input.getAdapter(IProject.class);
			if (project == null) {
				IResource resource = input.getAdapter(IResource.class);
				if (resource != null) {
					project = resource.getProject();
					System.out.println("nombre projecto: " + project.getName());
					try {
						analyseMethods(project);
					} catch (JavaModelException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}

		return null;
	}

**/
	/**
	 * llama al paquete donde esta situado y se queda ahi
	 * 
	 * @param project
	 *            el projecto donde hay que buscar
	 * @throws JavaModelException
	 */
	private void analyseMethods(IProject project) throws JavaModelException {
		IPackageFragment[] packages = JavaCore.create(project).getPackageFragments();
		for (IPackageFragment mypackage : packages) {
			if (mypackage.getKind() == IPackageFragmentRoot.K_SOURCE) {
				System.out.println("nombre paquete: " + mypackage.getElementName() + "\n");
				// System.out.println("nombre paquete padre: " + mypackage.getParent());
				createAST(mypackage);

			}

		}
	}

	private void createAST(IPackageFragment mypackage) throws JavaModelException {

		for (ICompilationUnit unit : mypackage.getCompilationUnits()) {
			CompilationUnit parse = parse(unit);
			MethodVisitor visitor = new MethodVisitor();
			parse.accept(visitor);
			System.out.println("unit:" + unit.getElementName());

			if (unit.getElementName().equals(Currente)) {
				// System.out.println(visitor.getMethods());
				ListaMetodos(visitor.getMethods());
				for (MethodDeclaration method : visitor.getMethods()) {
					System.out.println(Currente);
					System.out.println("Method name: " + method.getName() + "\nReturn Type: " + method.getReturnType2());
					List<Statement> arraySta = method.getBody().statements();
					System.out.println(arraySta.size());
					int i = 0;
					if (eleccion.equals(method.getName().toString())) {
						System.out.println("encontre a alguien:" + eleccion);
						System.out.println(arraySta);
						Ifsearch(method.getBody());
						if (method.getBody().IF_STATEMENT !=0) {
							System.out.println(arraySta.get(1).toString());
							System.out.println("salio un if");	
						}
					}else if(method.getBody().FOR_STATEMENT != 0) {
						System.out.println("salio un for");	
					}else if(method.getBody().WHILE_STATEMENT !=0) {
						System.out.println("salio un while");	
					}
					/**
					 * if (method.getBody().VARIABLE_DECLARATION_STATEMENT != 0) {
					 * System.out.println(method.getBody().VARIABLE_DECLARATION_STATEMENT);
					 * System.out.println("una declaracion de metodos");
					 * } 
					 * if (a.equals("if")) { 
					 * // condicionales.add("if");
					 *  }
					 *   else if (a.equals("for"))
					 * {
					 * 
					 * System.out.println("encontre un for"); // 
					 * condicionales.add("for"); }
					 * } i++;
					 * } //break; // System.out.println("Pruebas"+ regex);
					 * 
					 * } 
					 **/
				}

			}

		}
	}
	private static String Ifsearch(Block block) {
		if (block.IF_STATEMENT !=0) {
			System.out.println("salio un if capa 1");
			//System.out.print(block.structuralPropertiesForType());
			
		}else {
			System.out.println("no encontre a nadie");
		}
		return "encontre un if";
		
	}
	private static CompilationUnit parse(ICompilationUnit unit) {
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setSource(unit);
		parser.setResolveBindings(true);

		return (CompilationUnit) parser.createAST(null);
	}

	private void ListaMetodos(List<MethodDeclaration> list) throws JavaModelException {
		Iterator<MethodDeclaration> methods = list.iterator();
		while (methods.hasNext()) {
			condicionales.Insertar(methods.next().getName().toString());
		}
		condicionales.Imprimir();

	}
	public Lista getLista() {
		return this.condicionales;
	}

}
