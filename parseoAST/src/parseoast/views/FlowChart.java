package parseoast.views;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.wb.swt.ResourceManager;

import listas.Lista;
import parseoast.handlers.GetInfo;

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
	private Lista<CLabel> labels = new Lista<>();
	private int posx = 150;
	private int posy = 50;
	private int bucle=0;
	private int current =0;
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
    		action.setEnabled(false);
    		/**
    		 * Obtiene el interior en un paso
    		 */
    		action1 = new Action("Step Over") {
    			
    			public void run () {
    				if (bucle <= labels.Largo()) {
    					Color red = new Color(composite.getDisplay(), 255,0,0);
    					if (bucle == current) {
    						labels.Iterador(bucle).setForeground(red);
    						bucle++;
    					}
    					else if (current < bucle && bucle == labels.Largo()) {
    						labels.Iterador(current).setForeground(null);
    						bucle =0;
    						current = 0;
        					refresh.run();
    					}
    					else if (current < bucle) {
    						labels.Iterador(current).setForeground(null);
    						labels.Iterador(bucle).setForeground(red);
    						bucle++;
    						current++;
    					}
    					
    				}
    				
    			}
    		};
    		action1.setEnabled(false);
    		//ejecuta el get info 
    		action1.setImageDescriptor(ResourceManager.getPluginImageDescriptor("parseoAST", "icons/stepover_co.png"));
    		/**
    		 * Inicia el proceso del diagrama
    		 */
    		action2  = new Action ("Start Debug with Flowchart") {
    			@SuppressWarnings("static-access")
				public void run () {
    				if (combo.getText().isEmpty()) {
    					MessageDialog.openConfirm(composite.getShell(), "Alerta", "Debe ingresar el nombre de un m�todo");
    				}
    				
    				else {
    				//Obtener datos del metodo
    				GetInfo nuevo = new GetInfo();
    				Fabrica_de_simbolos simbolos = new Fabrica_de_simbolos();
    				nuevo.setEleccion(combo.getText());
    				//Inicio del proceso con el label respectivo
    				CLabel label = simbolos.start(posx, posy, composite,combo.getText());
    				labels.Insertar(label);
    				listener = simbolos.Process_Lineas(posx, posy,composite.getDisplay());
					active.Insertar(listener);
					composite.addPaintListener(listener);
					composite.redraw();
					posy+=100;
					//Botones del toolbar
    				action2.setEnabled(false);
    				action.setEnabled(true);
    				action1.setEnabled(true);
    				//Variables del step over
    				current = 0;
    				bucle = 0;
    				try {
						nuevo.execute(new ExecutionEvent());
						Recursion recur = new Recursion(nuevo.getFlujo(), composite,labels,active);
						sc.setMinSize(new Point(recur.getWidth(),recur.getHeight()));
	    				sc.update();
					} catch (ExecutionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
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
						action.setEnabled(false);
	    				action1.setEnabled(false);
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
					for (int i = 0; i<labels.Largo();i++) {
						labels.Iterador(i).dispose();
					}
					/**
					 * Modifica las variables como al inicio
					 */
	    				posy =50;
	    				posx=150;
	    			/**
	    			 * Actualizamos el area
	    			 */
	    				composite.redraw();
	    				sc.setMinSize(new Point(posx, posy));
	    				sc.update();
	    				current = 0;
	    				bucle = 0;
	    				labels = new Lista<>();
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