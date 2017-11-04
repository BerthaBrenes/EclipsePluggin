package parseoast.views;

import java.util.List;

import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.EnhancedForStatement;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.WhileStatement;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.widgets.Composite;

import listas.Lista;

public class Recursion {
	private Lista<List<Statement>> flujo = new Lista<>();
	private int posx = 150;
	private int posy = 180;
	private int derecha = 150;
	private int width = 150;
	private int height = 50;
	
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public Recursion (Lista<Statement> list,Composite composite, Lista<CLabel> labels,Lista<PaintListener> active) {
		Fabrica_de_simbolos fabrica = new Fabrica_de_simbolos();
		for (int i = 0;i<list.Largo();i++) {
			
			Diagrama(list.Iterador(i), 0, composite,labels,active);
			
			posy+=100;
			derecha=150;
			if (posy>width) {
				width = posy;
			}
			if (derecha>height) {
				height = derecha;
			}
		}
	}
	public void Diagrama (Object obj, int nivel,Composite composite,Lista<CLabel> lista,Lista<PaintListener> active) {
		List<Statement> nuevaIf = null;
		List<Statement> nuevaFor = null;
		List<Statement> nuevaWhile = null;
		List<Statement> nuevaForEnc = null;
		List<Statement> Expresion = null;
		Fabrica_de_simbolos fabrica = new Fabrica_de_simbolos();
	
		
			
			
			if (obj instanceof IfStatement) {
			
				IfStatement statement = (IfStatement) obj;
				
				nuevaIf = ((Block) (statement).getThenStatement()).statements();
				//Block bloque2 = ((Block) (statement).getElseStatement());
				//System.out.println(bloque2.statements().isEmpty());
				
				posx = derecha;
				if (posy>width) {
					width = posy;
				}
				if (derecha>height) {
					height = derecha;
				}
				CLabel label = fabrica.IF(posx, posy, composite, statement.getExpression().toString());
				
				lista.Insertar(label);
				PaintListener listener2 = fabrica.Process_Lineas(posx, posy, composite.getDisplay());
				composite.addPaintListener(listener2);
				active.Insertar(listener2);
				
				
				Block bloque = ((Block) (statement).getThenStatement());
				
				if (!bloque.statements().isEmpty()) {
					int o = 0;
					while (o != bloque.statements().size()) {
						
						derecha+=120;
						PaintListener listener = fabrica.horizontal(posx, posy, composite.getDisplay());
						composite.addPaintListener(listener);
						active.Insertar(listener);
						Diagrama(nuevaIf.get(o), nivel++,composite,lista,active);
						o++;
						
					}
					composite.removePaintListener(active.Iterador(active.Largo()-1));
				}
			}

			if (obj instanceof ForStatement) {
				
				ForStatement statement = (ForStatement) obj;
				nuevaFor = ((Block) (statement).getBody()).statements();
				posx = derecha;
				if (posy>width) {
					width = posy;
				}
				if (posx>height) {
					height = posx;
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
				posy+=100;
				if (statement.getExpression().toString().trim().contains("System")) {
					String print = statement.getExpression().toString().trim().substring(18);
					CLabel label =fabrica.Process(posx, posy, composite, print);
					lista.Insertar(label);
				}
				
				
				
			}
		
	}
}
