package src.parseoast.views;





import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Path;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.part.ViewPart;
import src.org.eclipse.wb.swt.ResourceManager;

import src.parseoast.handlers.GetInfo;



public class Diagrama extends ViewPart {
	public Diagrama() {
		
	}
	private Action action;
	private Action action1;
	private Action action2;
	private Action refresh;
	private Combo combo;
	private String[] array= new String[]{"For","Ejemplo2","End"};
	private Image imagen;
	private int posx =254;
	private int posy = 23;

	@Override
	
    public void createPartControl(Composite parent) {
		
		combo = new Combo(parent, SWT.NONE);
    	Rectangle bounds = parent.getBounds();
    	combo.setBounds(0, 0, bounds.width, bounds.height);
    	
		LabelFactory fac = new LabelFactory();
		fac.createLabel("Process", posx, posy, parent);
		parent.addPaintListener(new PaintListener() {
			
			@Override
			public void paintControl(PaintEvent e) {
				// TODO Auto-generated method stub
				for (int i = 0 ; i<4; i++) {
					
					e.gc.drawRectangle(posx, posy, 124, 60);
					posy+=30;
				}
			}
		});
		
		
			
		
		
		
		
	
		createActions();
        initializeToolBar();
			
		
			
		
	
		
	
        
       
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

    

    @Override
    public void setFocus() {
        // set the focus
    	combo.setFocus();
    }
    
    

}

