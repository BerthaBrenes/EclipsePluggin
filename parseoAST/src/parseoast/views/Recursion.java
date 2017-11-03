package parseoast.views;

import java.util.List;

import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.EnhancedForStatement;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.WhileStatement;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.widgets.Composite;

import listas.Lista;

public class Recursion {
	private Lista<List<Statement>> flujo = new Lista<>();
	private int posx = 270;
	private int posy = 100;
	public int getPosx() {
		return posx;
	}
	public int getPosy() {
		return posy;
	}
	public void Recursion (Object obj, int nivel,Composite composite,Lista<CLabel> lista) {
		List<Statement> nuevaIf = null;
		List<Statement> nuevaFor = null;
		List<Statement> nuevaWhile = null;
		List<Statement> nuevaForEnc = null;
		
		Fabrica_de_simbolos fabrica = new Fabrica_de_simbolos();
	
		
			
			
			if (obj instanceof IfStatement) {
				System.out.println("Entre If");
				IfStatement statement = (IfStatement) obj;
				
				nuevaIf = ((Block) (statement).getThenStatement()).statements();
				//Block bloque2 = ((Block) (statement).getElseStatement());
				//System.out.println(bloque2.statements().isEmpty());
				for (int i = 0;i<((Block) (statement).getThenStatement()).statements().size();i++) {
					System.out.println(((Block) (statement).getThenStatement()).statements().get(i));
				}
				System.out.println();
				CLabel label = fabrica.For(posx, posy, composite, statement.getExpression().toString());
				lista.Insertar(label);
				posx+=120;
				Block bloque = ((Block) (statement).getThenStatement());
				System.out.println("State pruebas: " + bloque.statements().isEmpty());
				if (!bloque.statements().isEmpty()) {
					int o = 0;
					while (o != bloque.statements().size()) {
						System.out.println("entre aqui");
						Recursion(nuevaIf.get(o), nivel++,composite,lista);
						o++;
						
					}
				}
			}

			if (obj instanceof ForStatement) {
				System.out.println("Entre For");
				ForStatement statement = (ForStatement) obj;
				nuevaFor = ((Block) (statement).getBody()).statements();
				
				// nuevaFor.add(statement.getExpression());
				Block bloque = ((Block) (statement).getBody());
				if (!bloque.statements().isEmpty()) {
					int o = 0;
					while (o != bloque.statements().size()) {
						System.out.println("entre aqui");
						Recursion(nuevaFor.get(o), nivel++,composite,lista);
						o++;
					}
				}
				
			}

			if (obj instanceof WhileStatement) {
				System.out.println("Entre While");
				WhileStatement statement = (WhileStatement) obj;
				nuevaWhile = ((Block) (statement).getBody()).statements();
				
				Block bloque = ((Block) (statement).getBody());
				if (!bloque.statements().isEmpty()) {
					int o = 0;
					while (o != bloque.statements().size()) {
						System.out.println("entre aqui");
						Recursion(nuevaWhile.get(o), nivel++,composite,lista);
						o++;
					}
				}
			
			}
			
			
			
			if (obj instanceof EnhancedForStatement) {
				System.out.println("Entre ForEnhance");
				EnhancedForStatement statement = (EnhancedForStatement) obj;
				nuevaForEnc = ((Block) (statement).getBody()).statements();
			
				Block bloque = ((Block) (statement).getBody());
				if (!bloque.statements().isEmpty()) {
					int o = 0;
					while (o != bloque.statements().size()) {
						System.out.println("entre aqui");
						Recursion(nuevaForEnc.get(o), nivel++,composite,lista);
						o++;
					}
				}
			
		}
	}
}
