package parseoast.views;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.wb.swt.ResourceManager;

import listas.Lista;
import parseoast.handlers.GetInfo;

public class Precaucion extends ViewPart{
	/**
	 * Constructor
	 */
	public Precaucion() {
		
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
	    combo.setBounds(10, 10, 330, 23);
	    Fabrica_de_simbolos symbol = new Fabrica_de_simbolos();
	    symbol.For(posx, posy, composite, "Prueba");
	    /**
	     * Final create part
	     */
	    sc.setContent(composite);
	    sc.setMinSize(new Point(posx, posy));
	   
		createActions();
        initializeToolBar();
		
}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
	
	}
	
	/**
	 * Acciones de los botones del toolbar
	 */
	private void createActions() {
        // Create the actions
    	{   /**
    		*Obtiene el paso siguiente
    		*/	
    		action = new Action("Step Into") {
    			public void run () {	
    				
    			}
    		};
    		action.setImageDescriptor(ResourceManager.getPluginImageDescriptor("parseoAST", "icons/stepinto_co.png"));
    		/**
    		 * Obtiene el interior en un paso
    		 */
    		action1 = new Action("Step Over") {
    			public void run () {
    				
    			}
    		};
    		//ejecuta el get info 
    		action1.setImageDescriptor(ResourceManager.getPluginImageDescriptor("parseoAST", "icons/stepover_co.png"));
    		/**
    		 * Inicia el proceso del diagrama
    		 */
    		action2  = new Action ("Start Debug with Flowchart") {
    			@SuppressWarnings("static-access")
				public void run () {
    				GetInfo nuevo = new GetInfo();
    				//Fabrica_de_simbolos simbolos = new Fabrica_de_simbolos();
    				
    				nuevo.setEleccion(combo.getText());
    				
    				/**listener = simbolos.Process_Lineas(posx, posy,composite.getDisplay());
					 active.Insertar(listener);
					   composite.addPaintListener(listener);
					   composite.redraw();
					   posy+=100;
    				   sc.setMinSize(new Point(posx, posy));
    				   sc.update();
    				   action2.setEnabled(false);
    				   */
    				try {
						nuevo.execute(new ExecutionEvent());
						
					} catch (ExecutionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    				
    			}
    		};
    		
    		action2.setEnabled(false);
    		action2.setImageDescriptor(ResourceManager.getPluginImageDescriptor("parseoAST", "icons/resume_co.png"));
    		/**
    		 * Actualiza el combo, borra el contenido del composite
    		 */
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