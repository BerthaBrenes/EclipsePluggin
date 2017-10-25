package parseoast.views;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.wb.swt.ResourceManager;


import MoViCo.*;
import listas.Lista;
import parseoast.handlers.GetInfo;
import org.eclipse.swt.widgets.Combo;

public class FlowChart extends ViewPart{
	public FlowChart() {
	}
	public String[] array= new String[]{"Método1","Método2","Método3","Método4"};
	private Lista<ActivityFigure> active = new Lista<>();
	private int posx = 150;
	private int posy = 20;
	private Action action;
	private Action action1;
	private Action action2;
	private Action refresh;
	private Combo combo;
	public void createPartControl(Composite parent) {
		
		Canvas canvas = new Canvas(parent, SWT.NONE);
		
		combo = new Combo(canvas, SWT.NONE);
		combo.setBounds(0, 0, 594, 23);
		
		
		
		
		createActions();
        initializeToolBar();
		
}
	public void getcontrol (Canvas canvas) {
		LightweightSystem lws = new LightweightSystem(canvas);
	    ChartFigure flowchart = new ChartFigure();
	    lws.setContents(flowchart);

	    TerminatorFigure start = new TerminatorFigure();
	    start.setName("Metodo1");
	    start.setBounds(new Rectangle(40, 20, 140, 20));
	    
	    DecisionFigure dec = new DecisionFigure();
	    dec.setName("Should I?");
	    dec.setBounds(new Rectangle(30, 60, 100, 60));
	    ProcessFigure proc = new ProcessFigure();
	    proc.setName("Do it!");
	    proc.setBounds(new Rectangle(40, 180, 80, 40));
	    TerminatorFigure stop = new TerminatorFigure();
	    stop.setName("End");
	    stop.setBounds(new Rectangle(40, 250, 80, 20));

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

	    
		
		
		
		
	    	
	    
	}
	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		
	}
	
	public Canvas clean (Composite parent) {
		Canvas canvas = new Canvas(parent, SWT.NONE);
		return canvas;
	}
	private void createActions() {
        // Create the actions
    	{
    		action = new Action("Step Into") {
    			public void run () {	
    			}
    		};
    		action.setImageDescriptor(ResourceManager.getPluginImageDescriptor("parseoAST", "icons/stepinto_co.png"));
    		action1 = new Action("Step Over") {
    		};
    		//ejecuta el get info 
    		action1.setImageDescriptor(ResourceManager.getPluginImageDescriptor("parseoAST", "icons/stepover_co.png"));
    		action2  = new Action ("Start Debug with Flowchart") {
    			@SuppressWarnings("static-access")
				public void run () {
    				System.out.println(combo.getText());
    				
    			}
    		};
    		
    		action2.setImageDescriptor(ResourceManager.getPluginImageDescriptor("parseoAST", "icons/resume_co.png"));
    		refresh = new Action("Refresh") {
    			public void run () {
    				GetInfo nuevo = new GetInfo();
					
					try {
						nuevo.execute(new ExecutionEvent());
						combo.setItems(nuevo.getLista());
						
					} catch (ExecutionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
    			}
			};
			refresh.setImageDescriptor(ResourceManager.getPluginImageDescriptor("parseoAST", "icons/refresh_nav.png"));

    	}
    }

    private void initializeToolBar() {
        IToolBarManager toolbarManager= getViewSite().getActionBars().getToolBarManager();
        toolbarManager.add(refresh);
        toolbarManager.add(action2);
        toolbarManager.add(action);
        toolbarManager.add(action1);
    }
}