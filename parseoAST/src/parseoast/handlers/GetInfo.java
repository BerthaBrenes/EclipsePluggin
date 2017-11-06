package parseoast.handlers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.sql.StatementEventListener;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IThread;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.EnhancedForStatement;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.ThisExpression;
import org.eclipse.jdt.core.dom.WhileStatement;
import org.eclipse.jdt.ui.IPackagesViewPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import listas.Lista;
import parseoast.views.Fabrica_de_simbolos;

import org.eclipse.jdt.debug.*;
import org.eclipse.jdt.internal.debug.core.breakpoints.*;

//import org.osgi.framework.BundleContext;

public class GetInfo extends AbstractHandler {
	private static final String JDT_Nature = "org.eclipse.jdt.core.javanature";

	public String Currente;

	public String eleccion = "getNobombre";

	public void setEleccion(String eleccion) {
		this.eleccion = eleccion;
	}

	// public DebugActivitor debug;
	private ArrayList<String> condicionales = new ArrayList<>();
	private Lista<Statement> flujo = new Lista<>();
	public Composite composite;

	public void setComposite(Composite composite) {
		this.composite = composite;
	}

	/**
	 * execute ejecuta el primer codigo que encuentra a raiz de un evento Con el
	 * activePage logra visualizar en cual clase esta ubicado
	 */

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		/**Thread debugThread = new Thread(new Runnable() {
			         public void run() {
			            while (true) {
			               try { Thread.sleep(500); } catch (Exception e) { }
			               Display.getDefault().asyncExec(new Runnable() {
			                  public void run() {
			                   try {
			        leerdebug();
			       } catch (DebugException e) {
			        e.printStackTrace();
			        System.out.println("se cayo ");
			       }
			                  }
			               });
			            }
			         }
			      });
		debugThread.start();*/
		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();

		IWorkbenchPage activePage = window.getActivePage();
		IEditorPart activeEditor = activePage.getActiveEditor();
		
		Currente = activeEditor.getTitle();
		if (activeEditor != null) {
			IEditorInput input = activeEditor.getEditorInput();

			IProject project = input.getAdapter(IProject.class);
			if (project == null) {
				IResource resource = input.getAdapter(IResource.class);
				if (resource != null) {
					project = resource.getProject();
					
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
				
				// System.out.println("nombre paquete padre: " + mypackage.getParent());
				createAST(mypackage);

			}

		}
	}
	
	private void createAST(IPackageFragment mypackage) throws JavaModelException {
		Fabrica_de_simbolos simbolos = new Fabrica_de_simbolos();
		
		for (ICompilationUnit unit : mypackage.getCompilationUnits()) {
			CompilationUnit parse = parse(unit);
			MethodVisitor visitor = new MethodVisitor();
			parse.accept(visitor);
			
			if (unit.getElementName().equals(Currente)) {
				// System.out.println(visitor.getMethods());
				ListaMetodos(visitor.getMethods());
				
				for (MethodDeclaration method : visitor.getMethods()) {
					
					List<Statement> arraySta = method.getBody().statements();
					
					
					if (eleccion.equals(method.getName().toString())) {
						method.getBody().statements();
						
						int i = 0;
						int limite = arraySta.size();
						System.out.print(method.getBody().getClass());
						while (i != limite) {
							int nivel =0 ;

							
							String a = arraySta.get(i).toString().trim().substring(0, 2);
							System.out.print(recursividad(method.getBody().statements().get(i),0));
							

							i++;
						
						}
					}
				}
			}
		}

	}



	private List<Statement> recursividad(Object obj, int nivel) {
		
		List<Statement> nuevaIf = null;
		List<Statement> nuevaFor = null;
		List<Statement> nuevaWhile = null;
		List<Statement> nuevaForEnc = null;
		List<Statement> nuevaDecla = null;
 		
		
		if (obj instanceof IfStatement) {
		
			IfStatement statement = (IfStatement) obj;
			nuevaIf = ((Block) (statement).getThenStatement()).statements();
			flujo.Insertar(statement);
			Block bloque = ((Block) (statement).getThenStatement());
			
			

		}

		if (obj instanceof ForStatement) {
		
			ForStatement statement = (ForStatement) obj;
			nuevaFor = ((Block) (statement).getBody()).statements();
			flujo.Insertar(statement);
			// nuevaFor.add(statement.getExpression());
			Block bloque = ((Block) (statement).getBody());

			

			return nuevaFor;
		}
		

		if (obj instanceof WhileStatement) {
			
			WhileStatement statement = (WhileStatement) obj;
			nuevaWhile = ((Block) (statement).getBody()).statements();
			flujo.Insertar(statement);
			Block bloque = ((Block) (statement).getBody());
			
			return nuevaWhile;
		}
		
		
		
		if (obj instanceof EnhancedForStatement) {
		
			EnhancedForStatement statement = (EnhancedForStatement) obj;
			nuevaForEnc = ((Block) (statement).getBody()).statements();
			flujo.Insertar(statement);
			Block bloque = ((Block) (statement).getBody());
			
			return nuevaForEnc;

		}
		else {
			return null;
		}
	}

	public Lista<Statement> getFlujo() {
		return flujo;
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
			condicionales.add(methods.next().getName().toString());
		}

	}
	public static int leerdebug() throws DebugException{
		try {
			DebugPlugin plugin = DebugPlugin.getDefault();
			ILaunchManager manager = plugin.getLaunchManager();
			IDebugTarget[] target = manager.getDebugTargets();
			System.out.print("entro aqui");
			
			return target[0].getThreads()[4].getStackFrames()[0].getLineNumber();
		}catch(Exception e) {
			
		}
		return 0;
		
	}
	public String[] getLista() {

		return this.condicionales.toArray(new String[condicionales.size()]);

	}
}