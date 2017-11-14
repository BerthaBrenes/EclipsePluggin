package parseoast.views;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.wb.swt.ResourceManager;

import Estructuras_de_datos.Lista;
import parseoast.handlers.GetInfo;

public class Flowcreate extends ViewPart{
	/**
	 * Constructor
	 */
	public Flowcreate() {
		
	}
	/**
	 * Variables needed
	 */
	public String[] array= new String[]{"Método1","Método2","Método3","Método4"};

	private Lista<PaintListener> active = new Lista<>();
	private Lista<Figure> figuras = new Lista<>();
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
	private Canvas canvas;
	private Figure root;
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
	    composite.setLayout(new GridLayout());
	    
	    canvas = new Canvas(composite, SWT.DOUBLE_BUFFERED);
	    canvas.setLayoutData(new GridData(GridData.FILL_BOTH));
	    
	    combo = new Combo(canvas, SWT.NONE);
	    combo.setBounds(10, 10, 330, 23);
	    
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
    					MessageDialog.openInformation(composite.getShell(), "Alerta", "Debe ingresar el nombre de un m�todo");
    				}
    				
    				else {
    				//Obtener datos del metodo
    				GetInfo nuevo = new GetInfo();
    				
    				nuevo.setEleccion(combo.getText());
    				//Inicio del proceso con el label respectivo
    				
					
					//Botones del toolbar
    				action2.setEnabled(false);
    				action.setEnabled(true);
    				action1.setEnabled(true);
    				//Variables del step over
    				
    			    
    			    
    			    
    				
    				try {
						nuevo.execute(new ExecutionEvent());
						root = new Figure();
	    				root.setFont(composite.getFont());
	    				LightweightSystem lws = new LightweightSystem(canvas);
	    				lws.setContents(root);
						root.setLayoutManager( new XYLayout() );
	    				recur recur = new recur();
	    				root = recur.Draw(nuevo.getFlujo(), composite,root, figuras,nuevo.getCurrente(),combo.getText());
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
					if (root != null) {
	    				root.removeAll();
	    				} 
					for (int i = 0; i<figuras.Largo();i++) {
						figuras.Iterador(i).erase();
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
	    				figuras = new Lista<>();
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