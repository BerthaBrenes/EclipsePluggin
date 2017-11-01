package MoViCo;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Slider;



public class FigureFactoryMethod {
	private int posx = 150;
	private int posy = 20;
	private ChartFigure flowchart;
	
	public FigureFactoryMethod (Canvas canvas) {
		
		
		
		Slider a = new Slider(canvas,SWT.VERTICAL | SWT.HORIZONTAL);
		a.setMinimum(60);
		
		LightweightSystem lb = new LightweightSystem(canvas);
		flowchart = new ChartFigure();
		
		
	    lb.setContents(flowchart);
	    
	}
	public void createfigure (String Type, String Name) {
		if (Type == "if") {
			DecisionFigure dec = new DecisionFigure();
			dec.setName(Name);
			dec.setBounds(new Rectangle(posx,posy,100+Name.length()*2,60+Name.length()*2));
			flowchart.add(dec);
			new Dnd(dec, dec.getName());
			
		
		}
		
	}
}
