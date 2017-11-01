package src.parseoast.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Path;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import src.org.eclipse.wb.swt.ResourceManager;

public class Fabrica_de_simbolos {
	private Image decision = ResourceManager.getPluginImage("parseoAST", "Iconos de Diagrama/decision_symbol-60x46.PNG");
	private Image process = ResourceManager.getPluginImage("parseoAST", "Iconos de Diagrama/action-process-symbol.png");
	private Image start_end = ResourceManager.getPluginImage("parseoAST", "Iconos de Diagrama/start-end-process-symbol.png");
	private Image f_decision = ResourceManager.getPluginImage("parseoAST", "Iconos de Diagrama/filled-decision_symbol-60x46.PNG");
	private Image f_process = ResourceManager.getPluginImage("parseoAST", "Iconos de Diagrama/filled-action-process-symbol.png");
	private Image f_start_end = ResourceManager.getPluginImage("parseoAST", "Iconos de Diagrama/filled-start-end-process-symbol.png");
	
	public PaintListener Process_Lineas (int posx, int posy,Display display) {
		PaintListener listener = new PaintListener() {
		Image image = ResourceManager.getPluginImage("parseoAST", "Iconos de Diagrama/start-end-process-symbol.png");
			@Override
			public void paintControl(PaintEvent e) {
				
				// TODO Auto-generated method stub
				
				
				e.gc.setLineWidth(1);
				e.gc.setForeground(display.getSystemColor(SWT.COLOR_BLACK));
				e.gc.setBackground(display.getSystemColor(SWT.COLOR_BLACK));
				drawArrow(e.gc, posx+55, posy+60, posx+55, posy+100, 10, Math.toRadians(40));
				
			}
		};
		return listener;
		
}
	public PaintListener For_Lineas (int posx, int posy,Display display) {
		PaintListener listener = new PaintListener() {
		Image image = ResourceManager.getPluginImage("parseoAST", "Iconos de Diagrama/decision_symbol-60x46.PNG");
			@Override
			public void paintControl(PaintEvent e) {
				
				// TODO Auto-generated method stub
				
				e.gc.drawImage(image, posx, posy);
				e.gc.setLineWidth(1);
				e.gc.setForeground(display.getSystemColor(SWT.COLOR_BLACK));
				e.gc.setBackground(display.getSystemColor(SWT.COLOR_BLACK));
				drawArrow(e.gc, posx+55, posy+60, posx+55, posy+100, 10, Math.toRadians(40));
				
				
			}
		};
		return listener;
}
	public CLabel For (int posx,int posy,Composite parent, String name) {
		CLabel label = new CLabel(parent, SWT.NONE);
		label.setBackground(decision);
		label.setBounds(posx,posy,110,65);
		label.addPaintListener(new PaintListener() {
				
				@Override
				public void paintControl(PaintEvent e) {
					// TODO Auto-generated method stub
					
					e.gc.drawString(name, label.getBounds().width/3, label.getBounds().height/3,true);
				}
			});
		label.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseUp(MouseEvent e) {
				// TODO Auto-generated method stub
				label.setBackground(decision);
			}
			
			@Override
			public void mouseDown(MouseEvent e) {
				// TODO Auto-generated method stub
				label.setBackground(f_decision);
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				// TODO Auto-generated method stub
				System.out.println(name);
			}
		});
		return label;
	}
	public CLabel Process (int posx,int posy,Composite parent, String name) {
		CLabel label = new CLabel(parent, SWT.NONE);
		label.setBackground(process);
		label.setBounds(posx, posy, 110, 65);
		label.addPaintListener(new PaintListener() {
			
			@Override
			public void paintControl(PaintEvent e) {
				// TODO Auto-generated method stub
				e.gc.drawString(name, label.getBounds().width/3, label.getBounds().height/3,true);
			}
		});
		label.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseUp(MouseEvent e) {
				// TODO Auto-generated method stub
				label.setBackground(process);
			}
			
			@Override
			public void mouseDown(MouseEvent e) {
				// TODO Auto-generated method stub
				label.setBackground(f_process);
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				// TODO Auto-generated method stub
				System.out.println(name);
			}
		});
		return label;
	}
	public static void drawArrow(GC gc, int x1, int y1, int x2, int y2, double arrowLength, double arrowAngle) {
	    double theta = Math.atan2(y2 - y1, x2 - x1);
	    double offset = (arrowLength - 2) * Math.cos(arrowAngle);

	    gc.drawLine(x1, y1, (int)(x2 - offset * Math.cos(theta)), (int)(y2 - offset * Math.sin(theta)));

	    Path path = new Path(gc.getDevice());
	    path.moveTo((float)(x2 - arrowLength * Math.cos(theta - arrowAngle)), (float)(y2 - arrowLength * Math.sin(theta - arrowAngle)));
	    path.lineTo((float)x2, (float)y2);
	    path.lineTo((float)(x2 - arrowLength * Math.cos(theta + arrowAngle)), (float)(y2 - arrowLength * Math.sin(theta + arrowAngle)));
	    path.close();

	    gc.fillPath(path);

	    path.dispose();
	}
}
