package src.MoViCo;

import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

public class Model {
	public Canvas datos (Composite parent) {
		
			Canvas canvas = new Canvas(parent, SWT.NONE);
			LightweightSystem lws = new LightweightSystem(canvas);
		    ChartFigure flowchart = new ChartFigure();
		    lws.setContents(flowchart);
		    
		
		return canvas;
	}
}
