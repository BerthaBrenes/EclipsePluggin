package parseoast.views;

import java.awt.Point;
import java.util.List;

import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.EnhancedForStatement;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;
import org.eclipse.jdt.core.dom.WhileStatement;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.widgets.Composite;

import com.sun.prism.paint.Paint;

import Estructuras_de_datos.Lista;
import Estructuras_de_datos.Pila;

public class Recursion {
	
	private int posx = 150;
	private int posy = 150;
	private int derecha = 150;
	private int width = 150;
	private int height = 50;
	
	private String CurrentClass;
	private boolean First = true;
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
	
	public Recursion (Lista<Statement> list,Composite composite, Lista<CLabel> labels,Lista<PaintListener> active,String current) {
		CurrentClass = current;
		Fabrica_de_simbolos simbolos = new Fabrica_de_simbolos();
		for (int i = 0;i<list.Largo();i++) {
			
			Diagrama(list.Iterador(i), 0, composite,labels,active);
			
			posy+=100;
			height+=100;
			posx=150;
			derecha=150;
		
			Compara();
			
		}
		CLabel label = simbolos.start(posx, posy, composite,"End");
		labels.Insertar(label);
		height+=100;
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
				Compara();

				CLabel label = fabrica.IF(posx, posy, composite, statement.getExpression().toString());
				lista.Insertar(label);
				
				Block bloque = ((Block) (statement).getThenStatement());
				
				if (!bloque.statements().isEmpty()) {
					int o = 0;
					derecha+=120;
					width+=120;
					while (o != bloque.statements().size()) {
						posy+=100;
						height+=100;
						Diagrama(nuevaIf.get(o), nivel++,composite,lista,active);
						o++;
					}
					derecha-=120;
					posx-=120;
				}
			}
			if (obj instanceof ForStatement) {
				
				ForStatement statement = (ForStatement) obj;
				nuevaFor = ((Block) (statement).getBody()).statements();
				Compara();
				String variable = statement.initializers().get(0).toString()+" ; "+statement.getExpression().toString()+
						" ; "+statement.updaters().get(0).toString();
				CLabel label  = fabrica.FOR(posx, posy, composite, variable);
				lista.Insertar(label);
				Block bloque = ((Block) (statement).getBody());
				if (!bloque.statements().isEmpty()) {
					int o = 0;
					derecha+=120;
					width+=120;
					while (o != bloque.statements().size()) {
						posy+=100;
						height+=100;
						Diagrama(nuevaFor.get(o), nivel++,composite,lista,active);
						o++;
					}
					derecha-=120;
					posx-=120;
				}
			}
			
			Pila<Point> while_pila = new Pila<>();
			
			
			if (obj instanceof WhileStatement) {
				WhileStatement statement = (WhileStatement) obj;
				nuevaWhile = ((Block) (statement).getBody()).statements();
				Compara();
				CLabel label  = fabrica.IF(posx, posy, composite, statement.getExpression().toString());
				lista.Insertar(label);
				while_pila.Push(new Point(posx, posy));
				Block bloque = ((Block) (statement).getBody());
				if (!bloque.statements().isEmpty()) {
					int o = 0;
					derecha+=120;
					width+=120;
					while (o != bloque.statements().size()) {
						posy+=100;
						height+=100;
						Diagrama(nuevaWhile.get(o), nivel++,composite,lista,active);
						o++;
					}
					derecha-=120;
					posx-=120;
					Point punto = while_pila.Pop();
					int[] points = {posx+220,posy+33,posx+420,punto.y+110,posx+420,punto.y+110};
					PaintListener listener = fabrica.witharray(points 
					, composite.getDisplay(), 255, 10);
					composite.addPaintListener(listener);
					composite.redraw();
				}
				else {
					
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
					
					
					CLabel label =fabrica.Process(derecha, posy, composite, statement.getExpression().toString());
					
					lista.Insertar(label);
				}
				
				else if (statement.getExpression() instanceof MethodInvocation) {
					MethodInvocation method = (MethodInvocation) statement.getExpression();
					if (CurrentClass.contains(method.resolveMethodBinding().getDeclaringClass().getName())) {
						CLabel label =fabrica.Process(derecha, posy, composite, method.getName().toString());
						label.setData(method);
						lista.Insertar(label);
					}
					else {
					CLabel label =fabrica.MethodExtern(derecha, posy, composite, method.getName().toString());
					lista.Insertar(label);
					}
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
			
			if (obj instanceof VariableDeclarationStatement) {
				VariableDeclarationStatement statement = (VariableDeclarationStatement) obj;
				
				CLabel label =fabrica.Process(derecha, posy, composite, statement.toString());
				
				lista.Insertar(label);
						
				
			
			}
			
			
			
	
		
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
