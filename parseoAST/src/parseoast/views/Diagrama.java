package parseoast.views;





import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.wb.swt.ResourceManager;

import parseoast.handlers.GetInfo;
import org.eclipse.swt.widgets.Combo;



public class Diagrama extends ViewPart {
	public Diagrama() {
		
	}
	private Action action;
	private Action action1;
	private Action action2;
	private String[] array= new String[]{"Start","For","Proceso","For","Proceso","End"};
	private Image imagen;
	
	@Override
    public void createPartControl(Composite parent) {
		
		int posx =254;
		int posy = 23;
		parent.setLayout(null);
		
		Combo combo = new Combo(parent, SWT.NONE);
		combo.setBounds(0, 0, 600, 23);
		combo.setItems(array);
		System.out.println(combo.getText());
		combo.addKeyListener(new KeyListener() {
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
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

