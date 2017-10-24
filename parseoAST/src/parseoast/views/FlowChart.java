package parseoast.views;

import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import MoViCo.ChartFigure;
import MoViCo.DecisionFigure;
import MoViCo.Dnd;
import MoViCo.PathFigure;
import MoViCo.ProcessFigure;
import MoViCo.TerminatorFigure;

public class FlowChart extends ViewPart{
	public FlowChart() {
	}
	public void createPartControl(Composite parent) {
		Canvas a = new Canvas (parent,SWT.NONE);
		a.setBounds(0,0,500,500);
		
	   
	    LightweightSystem lws = new LightweightSystem(a);
	    ChartFigure flowchart = new ChartFigure();
	    lws.setContents(flowchart);

	    TerminatorFigure start = new TerminatorFigure();
	    start.setName("Start");
	    start.setBounds(new Rectangle(60, 20, 80, 20));
	    DecisionFigure dec = new DecisionFigure();
	    dec.setName("Should I?");
	    dec.setBounds(new Rectangle(30, 60, 100, 60));
	    ProcessFigure proc = new ProcessFigure();
	    proc.setName("Do it!");
	    proc.setBounds(new Rectangle(40, 140, 80, 40));
	    TerminatorFigure stop = new TerminatorFigure();
	    stop.setName("End");
	    stop.setBounds(new Rectangle(40, 200, 80, 20));

	    PathFigure path1 = new PathFigure();
	    path1.setSourceAnchor(start.outAnchor);
	    path1.setTargetAnchor(dec.inAnchor);
	    PathFigure path2 = new PathFigure();
	    path2.setSourceAnchor(dec.yesAnchor);
	    path2.setTargetAnchor(proc.inAnchor);
	    PathFigure path3 = new PathFigure();
	    path3.setSourceAnchor(dec.noAnchor);
	    path3.setTargetAnchor(stop.inAnchor);
	    PathFigure path4 = new PathFigure();
	    path4.setSourceAnchor(proc.outAnchor);
	    path4.setTargetAnchor(stop.inAnchor);

	    flowchart.add(start);
	    flowchart.add(dec);
	    flowchart.add(proc);
	    
	    flowchart.add(stop);
	    flowchart.add(path1);
	    flowchart.add(path2);
	    flowchart.add(path3);
	    flowchart.add(path4);

	    new Dnd(start);
	    new Dnd(proc);
	    new Dnd(dec);
	    new Dnd(stop);
		
}
	public void prub (Composite parent) {
		
	}
	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		
	}
}