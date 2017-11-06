package parseoast.views;

import java.util.List;

import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.EnhancedForStatement;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.WhileStatement;

import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.widgets.Composite;

import listas.Lista;

public class Recursion {
	
	private int posx = 150;
	private int posy = 180;
	private int derecha = 150;
	private int width = 150;
	private int height = 50;
	private int nivel = 0;
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
		
	}
	public Recursion (Lista<Statement> list,Composite composite, Lista<CLabel> labels,Lista<PaintListener> active) {
		
		for (int i = 0;i<list.Largo();i++) {
			
			Diagrama(list.Iterador(i), 0, composite,labels,active);
			
			posy+=100;
			height+=100;
			
			derecha=150;
		
			nivel++;
		
		}
	}
	@SuppressWarnings("unchecked")
	public void Diagrama (Object obj, int nivel,Composite composite,Lista<CLabel> lista,Lista<PaintListener> active) {
		List<Statement> nuevaIf = null;
		List<Statement> nuevaFor = null;
		List<Statement> nuevaWhile = null;
		List<Statement> nuevaForEnc = null;
		List<Statement> nuevaelse = null;
		Fabrica_de_simbolos fabrica = new Fabrica_de_simbolos();
	
		
			
			
			if (obj instanceof IfStatement) {
			
				IfStatement statement = (IfStatement) obj;
				
				nuevaIf = ((Block) (statement).getThenStatement()).statements();
				
				//Block bloque2 = ((Block) (statement).getElseStatement());
				//System.out.println(bloque2.statements().isEmpty());
				
				posx = derecha;
				if (posy>height) {
					height = posy;
				}
				if (derecha>width) {
					width = derecha;
				}
				CLabel label = fabrica.IF(posx, posy, composite, statement.getExpression().toString());
				
				lista.Insertar(label);
				
				
				
				Block bloque = ((Block) (statement).getThenStatement());
				
				if (!bloque.statements().isEmpty()) {
					int o = 0;
					derecha+=120;
					width+=120;
					while (o != bloque.statements().size()) {
						
						posy+=100;
						
						Diagrama(nuevaIf.get(o), nivel++,composite,lista,active);
						o++;
						
					}
					derecha-=120;
					
				}
			
					
				
			}
			

			if (obj instanceof ForStatement) {
				
				ForStatement statement = (ForStatement) obj;
				nuevaFor = ((Block) (statement).getBody()).statements();
				posx = derecha;
				if (posy>height) {
					height = posy;
				}
				if (derecha>width) {
					width = derecha;
				}
				CLabel label  = fabrica.FOR(posx, posy, composite, statement.getExpression().toString());
				
				lista.Insertar(label);
				// nuevaFor.add(statement.getExpression());
				Block bloque = ((Block) (statement).getBody());
				if (!bloque.statements().isEmpty()) {
					int o = 0;
					while (o != bloque.statements().size()) {
						
						derecha+=120;
						Diagrama(nuevaFor.get(o), nivel++,composite,lista,active);
						o++;
					}
				}
				
			}

			if (obj instanceof WhileStatement) {
				
				WhileStatement statement = (WhileStatement) obj;
				nuevaWhile = ((Block) (statement).getBody()).statements();
				
				Block bloque = ((Block) (statement).getBody());
				if (!bloque.statements().isEmpty()) {
					int o = 0;
					while (o != bloque.statements().size()) {
						
						Diagrama(nuevaWhile.get(o), nivel++,composite,lista,active);
						o++;
					}
				}
			
			}
			
			
			
			if (obj instanceof EnhancedForStatement) {
				
				EnhancedForStatement statement = (EnhancedForStatement) obj;
				nuevaForEnc = ((Block) (statement).getBody()).statements();
			
				Block bloque = ((Block) (statement).getBody());
				if (!bloque.statements().isEmpty()) {
					int o = 0;
					while (o != bloque.statements().size()) {
						
						Diagrama(nuevaForEnc.get(o), nivel++,composite,lista,active);
						o++;
					}
				}
			
		}
			if (obj instanceof ExpressionStatement) {
				ExpressionStatement statement = (ExpressionStatement) obj;
				
				if (statement.getExpression().toString().trim().contains("System")) {
					@SuppressWarnings("unused")
					String print = statement.getExpression().toString().trim().substring(18);
					//CLabel label =fabrica.Process(posx, posy, composite, print);
					//lista.Insertar(label);
				}
				
				
				
			}
			if (obj.getClass().getSimpleName().equals("IfStatement")) {
				IfStatement statement = (IfStatement) obj;
				
				if (statement.getElseStatement() != null) {
					Block bloque = (Block) statement.getElseStatement();
					nuevaelse= bloque.statements();
					if (!bloque.statements().isEmpty()) {
						int o = 0;
						
						
						while (o != bloque.statements().size()) {
							posy+=100;
							Diagrama(nuevaelse.get(o), nivel++,composite,lista,active);
							o++;
						}
						
					}
				}
			}
			
	
		
	}
}
