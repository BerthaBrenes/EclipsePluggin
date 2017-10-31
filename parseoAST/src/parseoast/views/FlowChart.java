package parseoast.views;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Drawable;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.GCData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Path;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.wb.swt.ResourceManager;

import MoViCo.ChartFigure;
import MoViCo.DecisionFigure;
import MoViCo.PathFigure;
import MoViCo.TerminatorFigure;
import listas.Lista;
import parseoast.handlers.GetInfo;
import org.eclipse.swt.custom.CCombo;

public class FlowChart extends ViewPart{
	/**
	 * Constructor
	 */
	public FlowChart() {
		
	}
	/**
	 * Variables needed
	 */
	public String[] array= new String[]{"Método1","Método2","Método3","Método4"};

	private Lista<PaintListener> active = new Lista<>();
	private int posx = 150;
	private int posy = 50;
	
	private Action action;
	private Action action1;
	private Action action2;
	private Action refresh;
	
	private Combo combo;
	private Canvas canvas_1;
	private Composite composite;
	private ScrolledComposite sc;
	private PaintListener listener;
	/**
	 * Se inicia al ejecutar el plugin, este contiene todos los widgets que deben abrirse al iniciar este
	 * el parametro que recibe es el area donde se colocan cada widget
	 */
	public void createPartControl(Composite parent) {
		/**
		 * Body create part control
		 */
		
		sc = new ScrolledComposite(parent, SWT.H_SCROLL | SWT.V_SCROLL);
	    sc.setExpandHorizontal(true);
	    sc.setExpandVertical(true);
	    
	    composite = new Composite(sc, SWT.NONE);
	    
	    /**
	     * Defect widgets in the part control
	     */
	    
	  
	    combo = new Combo(composite, SWT.NONE);
	    combo.setBounds(124, 10, 330, 23);
	    Label lblNewLabel = new Label(composite, SWT.NONE);
	    lblNewLabel.setBounds(10, 13, 108, 15);
	    lblNewLabel.setText("Select one method");
	    
	    
	   
	
	   
	  
	    
	    
	    /**
	     * Final create part
	     */
	    sc.setContent(composite);
	    sc.setMinSize(new Point(posx, posy));
	   
		createActions();
        initializeToolBar();
		
}
	
	
	
	public PaintListener paint2 () {
		PaintListener listener = new PaintListener() {
			
			@Override
			public void paintControl(PaintEvent e) {
				
				// TODO Auto-generated method stub
				for (int i = 0; i<5;i++) {
				e.gc.drawRectangle(160, i*120, 90, 90);
				}
				
				
			}
		};
		return listener;
	}
	/**
	 * Metodo para dibujar lineas de flujo con una posicion en x y una posicion en y
	 * @param gc
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @param arrowLength
	 * @param arrowAngle
	 */
	

	    

	    
		
		
		
		
	    	
	    
	
	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
	
	}
	
	/**
	 * Acciones de los botones del toolbar
	 */
	private void createActions() {
        // Create the actions
    	{
    		action = new Action("Step Into") {
    			public void run () {	
    				
    			}
    		};
    		action.setImageDescriptor(ResourceManager.getPluginImageDescriptor("parseoAST", "icons/stepinto_co.png"));
    		
    		action1 = new Action("Step Over") {
    			public void run () {
    				
    			}
    		};
    		//ejecuta el get info 
    		action1.setImageDescriptor(ResourceManager.getPluginImageDescriptor("parseoAST", "icons/stepover_co.png"));
    		
    		action2  = new Action ("Start Debug with Flowchart") {
    			@SuppressWarnings("static-access")
				public void run () {
    				GetInfo nuevo = new GetInfo();
    				Fabrica_de_simbolos simbolos = new Fabrica_de_simbolos();
    				
    				nuevo.setEleccion(combo.getText());
    				
    				listener = simbolos.Process(posx, posy,composite.getDisplay(),combo.getText());
					 active.Insertar(listener);
					   composite.addPaintListener(listener);
					   composite.redraw();
					   posy+=100;
    				   sc.setMinSize(new Point(posx, posy));
    				   sc.update();
    				   action2.setEnabled(false);
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
						action2.setEnabled(true);
					} catch (ExecutionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					/**
					 * Elimina todos los simbolos existentes
					 */
					for (int i = 0; i<active.Largo();i++) {
	    				composite.removePaintListener(active.Iterador(i));
	    				}
	    				posy = 50;
	    				composite.redraw();
	    				sc.setMinSize(new Point(posx, posy));
	    				sc.update();
    			}
			};
			refresh.setImageDescriptor(ResourceManager.getPluginImageDescriptor("parseoAST", "icons/refresh_nav.png"));

    	}
    }
	/**
	 * Metodo para añadir iconos al toolbar
	 */
    private void initializeToolBar() {
        IToolBarManager toolbarManager= getViewSite().getActionBars().getToolBarManager();
        toolbarManager.add(refresh);
        toolbarManager.add(action2);
        toolbarManager.add(action);
        toolbarManager.add(action1);
        
    }
}