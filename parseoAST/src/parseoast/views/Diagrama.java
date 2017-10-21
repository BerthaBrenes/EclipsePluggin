package parseoast.views;





import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.wb.swt.ResourceManager;

import parseoast.handlers.GetInfo;



public class Diagrama extends ViewPart {
	public Diagrama() {
		
	}
	private Action action;
	private Action action1;
	private Action action2;
	private String[] array= new String[]{"Start","For","Proceso","Start","End"};
	private Image imagen;
	
	@Override
    public void createPartControl(Composite parent) {
<<<<<<< HEAD

		parent.setLayout(new GridLayout(2, false));		
		Label image2 = new Label(parent, SWT.SHADOW_IN | SWT.CENTER);
		image2.setToolTipText("");
		image2.setImage(ResourceManager.getPluginImage("parseoAST", "Iconos de Diagrama/decision-symbol.png"));
=======
		
		int posx =254;
		int posy = 23;
		parent.setLayout(null);
>>>>>>> 584e6d79a27eabdc6bf96d258164475c4890a8e4
		
		for (int i = 0; i<array.length;i++) {
			
			if (array[i] == "For") {
				
			LabelFactory label = new LabelFactory();
			label.createLabel(array[i], posx, posy, parent,"Comer","Break");
			posy+=160;
			Lineas lineas = new Lineas(posx, posy, parent);
			
			}
			
			else {
				if (array[i] != "End") {
					
					Lineas lineas = new Lineas(posx, posy, parent);
				}
				LabelFactory label = new LabelFactory();
				label.createLabel(array[i], posx, posy, parent);
				posy+=80;
		}
		}
	
		
		
		
		
	
		
		
		
	    
        
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
    				GetInfo a = new GetInfo();
    				try {
						a.execute(new ExecutionEvent());
						System.out.println(a.condicionales);
						
					} catch (ExecutionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    			}
    		};
    		
    		action2.setImageDescriptor(ResourceManager.getPluginImageDescriptor("parseoAST", "icons/resume_co.png"));
    		
    	}
    }

    private void initializeToolBar() {
        IToolBarManager toolbarManager= getViewSite().getActionBars().getToolBarManager();
        toolbarManager.add(action2);
        toolbarManager.add(action);
        toolbarManager.add(action1);
    }

    

    @Override
    public void setFocus() {
        // set the focus
    	
    }
}

