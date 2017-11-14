package parseoast.views;


import java.io.PrintStream;
import java.util.List;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.EnhancedForStatement;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;
import org.eclipse.jdt.core.dom.WhileStatement;
import org.eclipse.swt.widgets.Composite;

import Estructuras_de_datos.Lista;
import Estructuras_de_datos.Pila;

public class recur {
	
	private int posx = 100;
	private int posy = 50;
	private int derecha = 150;
	private int width = 150;
	private int height = 50;
	
	private String CurrentClass;
	private boolean First = true;
	private Pila<Figure> bases = new Pila<>();
	/**
	 * Retorna el tamaño maximo para el ScrolledComposite
	 * @return
	 */
	public int getWidth() {
		return width;
	}
	/**
	 * Retorna el tamaño maximo para el ScrolledComposite
	 * @return
	 */
	public int getHeight() {
		return height;
		
	}
	
	  
	  
	public Figure Draw (Lista<Statement> list,Composite composite, Figure root, Lista<Figure> figuras, String current,String method) {
		CurrentClass = current;
		ThickLinkDraw recus = new ThickLinkDraw();
		Figure start = recus.Start_End(method);
		root.add(start, 
	    new Rectangle( new Point(posx, posy),  
	      start.getPreferredSize() ) );
		figuras.Insertar(start);
		posy+=100;
		if (list.Largo() == 0) {
			Figure fin = recus.Start_End("End");
			root.add(fin,new Rectangle( new Point(posx, posy),  
				      fin.getPreferredSize() ) );
			root.add(recus.connect(fin, start, ""));
			height+=100;
		}
		else {
		for (int i = 0;i<list.Largo();i++) {
			if (i==0) {
				int curr = posy;
				Figure uno = Diagrama(list.Iterador(i), 0, composite,figuras,root);
				root.add(uno, 
						new Rectangle( new Point(posx, curr),  
								start.getPreferredSize() ) );
				root.add(recus.connect (uno, start,""));
				bases.Push(uno);	
			}
			else {
				Figure base = Diagrama(list.Iterador(i), 0, composite,figuras,root);
				root.add(base,new Rectangle( new Point(posx, posy),  
					      start.getPreferredSize() ) );
				root.add(recus.connect(base, bases.Pop(), ""));
				bases.Push(base);	
				
			}
			width+=120;
			posy+=100;
			height+=100;
			posx=150;
			derecha=150;
		
			Compara();
			
		}
		Figure fin = recus.Start_End("End");
		root.add(fin,new Rectangle( new Point(posx, posy),  
			      fin.getPreferredSize() ) );
		root.add(recus.connect(fin, bases.Pop(), ""));
		height+=100;
		}
		return root;
	}
	
	@SuppressWarnings("unchecked")
	public Figure Diagrama (Object obj, int nivel,Composite composite,Lista<Figure> lista,Figure root) {
		List<Statement> nuevaIf = null;
		List<Statement> nuevaFor = null;
		List<Statement> nuevaWhile = null;
		List<Statement> nuevaForEnc = null;
		List<Statement> nuevaelse = null;
		ThickLinkDraw draw = new ThickLinkDraw();
			if (obj instanceof IfStatement) {
				
				IfStatement statement = (IfStatement) obj;
				nuevaIf = ((Block) (statement).getThenStatement()).statements();
				Compara();
				
				Figure a = draw.decision(statement.getExpression().toString());
				root.add(a, new Rectangle( new Point(posx, posy), a.getPreferredSize() ) );      
				
				
				Block bloque = ((Block) (statement).getThenStatement());
				
				if (!bloque.statements().isEmpty()) {
					int o = 0;
					derecha+=120;
					width+=120;
					while (o != bloque.statements().size()) {
						posy+=100;
						height+=100;
						
						if (First== true) {
						Figure IF = Diagrama(nuevaIf.get(o), nivel++,composite,lista,root);
						root.add(draw.connect(IF, a, "Yes"));
						bases.Push(IF);
						First = false;
						}
						else {
							Figure IF = Diagrama(nuevaIf.get(o), nivel++,composite,lista,root);
							root.add(draw.connect(IF, bases.Pop(),""));
							bases.Push(IF);
						}
						
						o++;
					}
					First = true;
					derecha-=120;
					posx-=120;
					
				}
				if (statement.getElseStatement() != null) {
					Block bloqu = (Block)statement.getElseStatement();
					
					nuevaIf = bloqu.statements();
					if (!bloqu.statements().isEmpty()) {
						int o = 0;
						
						while (o != bloqu.statements().size()) {
							posy+=100;
							height+=100;
							
							if (First== true) {
							Figure IF = Diagrama(nuevaIf.get(o), nivel++,composite,lista,root);
							root.add(draw.connect(IF, a, ""));
							bases.Push(IF);
							First = false;
							}
							else {
								Figure IF = Diagrama(nuevaIf.get(o), nivel++,composite,lista,root);
								root.add(draw.connect(IF, bases.Pop(),""));
								bases.Push(IF);
							}
							
							o++;
						}
						First = true;
						derecha-=120;
						posx-=120;
					}
					
				}
				return a;
			
			}
			if (obj instanceof ForStatement) {
				
				ForStatement statement = (ForStatement) obj;
				nuevaFor = ((Block) (statement).getBody()).statements();
				Compara();
				String variable = statement.initializers().get(0).toString()+" ; "+statement.getExpression().toString()+
						" ; "+statement.updaters().get(0).toString();
				Figure a = draw.FOR(variable);
				root.add(a, new Rectangle( new Point(posx, posy), a.getPreferredSize() ) );   
				
				Block bloque = ((Block) (statement).getBody());
				if (!bloque.statements().isEmpty()) {
					int o = 0;
					derecha+=120;
					width+=120;
					while (o != bloque.statements().size()) {
						posy+=100;
						height+=100;
						if (First== true) {
							Figure IF = Diagrama(nuevaFor.get(o), nivel++,composite,lista,root);
							root.add(draw.connect(IF, a, "Yes"));
							bases.Push(IF);
							First = false;
							}
							else {
								Figure IF = Diagrama(nuevaFor.get(o), nivel++,composite,lista,root);
								root.add(draw.connect(IF, bases.Pop(),""));
								bases.Push(IF);
							}
						
						o++;
					}
					
					root.add(draw.connect2(a, bases.Pop(), "++"));
					First = true;
					derecha-=120;
					posx-=120;
				}
				if (bloque.statements().isEmpty()) {
					root.add(draw.connectbucle( a, "++"));
				}
				return a;
			
			}
			
			
			
			
			if (obj instanceof WhileStatement) {
				WhileStatement statement = (WhileStatement) obj;
				nuevaWhile = ((Block) (statement).getBody()).statements();
				Compara();
				Figure a = draw.While(statement.getExpression().toString());
				root.add(a, new Rectangle( new Point(posx, posy), a.getPreferredSize() ) );      
				
				Block bloque = ((Block) (statement).getBody());
				if (!bloque.statements().isEmpty()) {
					int o = 0;
					derecha+=120;
					width+=120;
					while (o != bloque.statements().size()) {
						posy+=100;
						height+=100;
						
						if (First== true) {
							Figure IF = Diagrama(nuevaWhile.get(o), nivel++,composite,lista,root);
							root.add(draw.connect(IF, a, "Yes"));
							bases.Push(IF);
							First = false;
							}
							else {
								Figure IF = Diagrama(nuevaWhile.get(o), nivel++,composite,lista,root);
								root.add(draw.connect(IF, bases.Pop(),""));
								bases.Push(IF);
							}
						o++;
					}
					First = true;
					root.add(draw.connect2(a, bases.Pop(), "next"));
					derecha-=120;
					posx-=120;
					
					
					
				}
				if (bloque.statements().isEmpty()) {
					root.add(draw.connectbucle( a, "next"));
				}
				return a;
				
				
				
			}
			
			
			
			if (obj instanceof EnhancedForStatement) {
				
				EnhancedForStatement statement = (EnhancedForStatement) obj;
				nuevaForEnc = ((Block) (statement).getBody()).statements();
				Compara();
				
				Figure a = draw.FOR(statement.getParameter().toString()+" : "+statement.getExpression().toString());
				root.add(a, new Rectangle( new Point(posx, posy), a.getPreferredSize() ) );   
				Block bloque = ((Block) (statement).getBody());
				if (!bloque.statements().isEmpty()) {
					int o = 0;
					derecha+=120;
					width+=120;
					while (o != bloque.statements().size()) {
						posy+=100;
						height+=100;
						if (First== true) {
							Figure IF = Diagrama(nuevaForEnc.get(o), nivel++,composite,lista,root);
							root.add(draw.connect(IF, a, "Yes"));
							bases.Push(IF);
							First = false;
							}
							else {
								Figure IF = Diagrama(nuevaForEnc.get(o), nivel++,composite,lista,root);
								root.add(draw.connect(IF, bases.Pop(),""));
								bases.Push(IF);
							}
						o++;
					}
					root.add(draw.connect2(a, bases.Pop(), "++"));
					First = true;
					derecha-=120;
					posx-=120;
				}
				if (bloque.statements().isEmpty()) {
					root.add(draw.connectbucle( a, "++"));
				}
				return a;
				}
			
			
		
			if (obj instanceof ExpressionStatement) {
				ExpressionStatement statement = (ExpressionStatement) obj;
			
				
				Compara();
				
				
				if (statement.getExpression() instanceof MethodInvocation) {
					MethodInvocation method = (MethodInvocation) statement.getExpression();
					
					if (CurrentClass.contains(method.resolveMethodBinding().getDeclaringClass().getName())) {
						
						Figure a = draw.process(statement.getExpression().toString());
						root.add(a, new Rectangle( new Point(posx, posy), a.getPreferredSize() ) );  
						
						return a;
					}
					if (statement.getExpression().toString().trim().contains("System")) {
						Figure a = draw.input_output(statement.getExpression().toString());
						root.add(a, new Rectangle( new Point(posx, posy), a.getPreferredSize() ) );  
						
						return a;
					}
					else {
						Figure a = draw.Extern(statement.getExpression().toString());
						root.add(a, new Rectangle( new Point(posx, posy), a.getPreferredSize() ) ); 
						
						return a;
					}
				}
				else {
					Figure a = draw.process(statement.getExpression().toString());
					root.add(a, new Rectangle( new Point(posx, posy), a.getPreferredSize() ) );   
					
					return a;
				}
				
				
				
				
			}
			
			if (obj.getClass().getSimpleName().equals("IfStatement")) {
				IfStatement statement = (IfStatement) obj;
				System.out.println(statement.toString());
				if (statement.getElseStatement() != null) {
					Block bloque = (Block) statement.getElseStatement();
					nuevaelse= bloque.statements();
					if (!bloque.statements().isEmpty()) {
						int o = 0;
						
						
						while (o != bloque.statements().size()) {
							posy+=100;
							Diagrama(nuevaelse.get(o), nivel++,composite,lista,root);
							o++;
						}
						
					}
				}
			}
			
			if (obj instanceof VariableDeclarationStatement) {
				
				
				Compara();
				VariableDeclarationStatement statement = (VariableDeclarationStatement) obj;
				
				Figure a = draw.process(statement.toString());
				root.add(a, new Rectangle( new Point(posx, posy), a.getPreferredSize() ) );   
				
						
				return a;
			
			}
			if (obj instanceof ReturnStatement) {
				Compara();
				ReturnStatement statement = (ReturnStatement) obj;
				Figure a = draw.input_output(statement.getExpression().toString());
				root.add(a, new Rectangle( new Point(posx, posy), a.getPreferredSize() ) );   
				
				return a;
			}
			if (obj instanceof PrintStream) {
				Compara();
				PrintStream statement = (PrintStream) obj;
				Figure a = draw.input_output(statement.toString());
				root.add(a, new Rectangle( new Point(posx, posy), a.getPreferredSize() ) );   
			
				return a;
			}
			
			
			else {
				System.out.println(obj.getClass());
			}
				
			return null;
			
			
			
	
		
	}
	private void Compara () {
		posx = derecha;
		if (posy>height) {
			height = posy;
		}
		if (derecha>width) {
			width = derecha;
		}
	}
	
}
