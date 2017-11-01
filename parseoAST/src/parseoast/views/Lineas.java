package src.parseoast.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Path;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;


public class Lineas {

	public Lineas(int posx, int posy, Composite parent) {
		parent.addPaintListener(new PaintListener() {
			
			@Override
			public void paintControl(PaintEvent e) {
				Display display = parent.getDisplay();
				e.gc.setLineWidth(1);
				e.gc.setForeground(display.getSystemColor(SWT.COLOR_BLACK));
				e.gc.setBackground(display.getSystemColor(SWT.COLOR_BLACK));
				drawArrow(e.gc, posx, posy-62, posx+62, posy+50, 8, Math.toRadians(40));
				
			}
		});
				
	
		
		
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
