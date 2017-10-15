package parseoast.views;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.wb.swt.ResourceManager;

import parseoast.handlers.GetInfo;

import org.eclipse.swt.layout.GridLayout;

<<<<<<< HEAD
//import swing2swt.layout.BoxLayout;

=======
>>>>>>> 1d00fb97487cd98890cca3b6750049eb22b610e8


public class Diagrama extends ViewPart {
	public Diagrama() {
	}
	private Action action;
	private Action action1;
	private Action action2;
	

	@Override
    public void createPartControl(Composite parent) {
<<<<<<< HEAD
		//parent.layout(new BoxLayout(BoxLayout.Y_AXIS));
=======
		parent.setLayout(new GridLayout(2, false));
		
>>>>>>> 1d00fb97487cd98890cca3b6750049eb22b610e8
		
		Label image2 = new Label(parent, SWT.SHADOW_IN | SWT.CENTER);
		image2.setToolTipText("");
		image2.setImage(ResourceManager.getPluginImage("parseoAST", "Iconos de Diagrama/decision-symbol.png"));
		
		Label image = new Label(parent, SWT.IMAGE_PNG);
		image.setImage(ResourceManager.getPluginImage("parseoAST", "Iconos de Diagrama/action-process-symbol.png"));
		
		
	        
		


		
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
    			public void run () {
    				GetInfo a = new GetInfo();
    				try {
						a.execute(new ExecutionEvent());
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
