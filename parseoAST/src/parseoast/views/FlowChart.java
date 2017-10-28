package parseoast.views;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Path;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.wb.swt.ResourceManager;

import MoViCo.ActivityFigure;
import listas.Lista;
import parseoast.handlers.GetInfo;
import org.eclipse.swt.widgets.ToolBar;

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
	private Canvas canvas;
	
	
	public Canvas getCanvas() {
		return canvas;
	}
	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}
	public void createPartControl(Composite parent) {
		
		ScrolledComposite sc = new ScrolledComposite(parent, SWT.H_SCROLL | SWT.V_SCROLL);
	    Composite composite = new Composite(sc, SWT.NONE);
	    sc.setContent(composite);

	    composite.setLayout(null);

	    combo = new Combo(composite, SWT.NONE);
	    Rectangle bou = parent.getBounds();
	    combo.setBounds(new Rectangle(0, 0, 592, 23));
	    
	    
	    for (int i = 0; i<20;i++) {
	    	Label label = new Label(composite, SWT.NONE);
	    	label.setText("Label: "+i);
	    	label.setBounds(new Rectangle(posx, posy, 60, 20));
	    	posy+=30;
	    }
	    sc.setExpandHorizontal(true);
	    sc.setExpandVertical(true);
	    sc.setMinSize(parent.computeSize(SWT.DEFAULT, SWT.DEFAULT));
        
		
		
    	
    	
    	
		
		
		
		createActions();
        initializeToolBar();
		
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

	    

	    
		
		
		
		
	    	
	    
	
	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		combo.setFocus();
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
    				GetInfo nuevo = new GetInfo();
    				
    				nuevo.setEleccion(combo.getText());
    				nuevo.canvas = canvas;
    				try {
						nuevo.execute(new ExecutionEvent());
						
					} catch (ExecutionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    				
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