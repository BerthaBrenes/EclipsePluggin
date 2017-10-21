package parseoast.views;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.widgets.Composite;


public class Lineas {

	public Lineas(int posx, int posy, Composite parent) {
		parent.addPaintListener(new PaintListener() {
			
			@Override
			public void paintControl(PaintEvent e) {
				e.gc.drawLine(posx+62, posy+60, posx+62, posy+140);
				
				
			}
		});
		
	}

	
	
}
