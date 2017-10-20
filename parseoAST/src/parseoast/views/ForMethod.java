package parseoast.views;

import java.util.concurrent.locks.Condition;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.ResourceManager;

public class ForMethod extends genLabel {
	private String Yes;
	private String No;
	public ForMethod(String Texto, int posx, int posy,Composite parent,String Yes,String No) {
		super(Texto, posx, posy, parent);
		this.Yes = Yes;
		this.No = No;
	}
	
	public Label label() {
		Label label = new Label(getParent(), SWT.NONE);
		label.setText("\n"+getText());
		label.setAlignment(SWT.CENTER);
		label.setBackgroundImage(ResourceManager.getPluginImage("parseoAST", "Iconos de Diagrama/decision-symbol.jpg"));
		label.setBounds(getPosx(), getPosy(), 124, 60);
		conditions();
		return label;
	}
	public void conditions () {
		Yes (getPosx()-100,getPosy()+80);
		No(getPosx()+100,getPosy()+80);
		getParent().addPaintListener(Lineas());
	}
	public Label Yes (int x,int y) {
		Label label = new Label(getParent(), SWT.NONE);
		label.setText("\n"+Yes);
		label.setAlignment(SWT.CENTER);
		label.setBackgroundImage(ResourceManager.getPluginImage("parseoAST", "Iconos de Diagrama/action-process-symbol.png"));
		label.setBounds(x, y, 124, 60);
		return label;
	}
	public Label No (int x,int y) {
		Label label = new Label(getParent(), SWT.NONE);
		label.setText("\n"+No);
		label.setAlignment(SWT.CENTER);
		label.setBackgroundImage(ResourceManager.getPluginImage("parseoAST", "Iconos de Diagrama/action-process-symbol.png"));
		label.setBounds(x, y, 124, 60);
		return label;
	}
	public PaintListener Lineas () {
		PaintListener pintar = new PaintListener() {
			
			@Override
			public void paintControl(PaintEvent e) {
				e.gc.drawLine(getPosx(), getPosy()+30, getPosx()-50, getPosy()+80);
				e.gc.drawLine(getPosx()+124, getPosy()+30, getPosx()+50+124, getPosy()+80);
				
			}
		};
		return pintar;
	}

}
