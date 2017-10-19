package parseoast.views;



import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.draw2d.GridData;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Drawable;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.GCData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.wb.swt.ResourceManager;
import parseoast.handlers.GetInfo;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.wb.swt.SWTResourceManager;



public class Diagrama extends ViewPart {
	public Diagrama() {
	}
	private Action action;
	private Action action1;
	private Action action2;
	private String[] array= new String[]{"Hola","AST"};
			

	@Override
    public void createPartControl(final Composite parent) {
		Color bg = new Color(parent.getDisplay(), 248, 248, 248);
		Canvas canvas = new Canvas(parent, SWT.NONE);
		
		Label lblNuevoEjemplo = new Label(canvas, SWT.VERTICAL | SWT.CENTER);
		
		lblNuevoEjemplo.setBackgroundImage(ResourceManager.getPluginImage("parseoAST", "Iconos de Diagrama/action-process-symbol.png"));
		lblNuevoEjemplo.setBounds(10,28,124,60);
		lblNuevoEjemplo.setLayoutData(new GridData(SWT.CENTER,SWT.CENTER,true,true));
		lblNuevoEjemplo.setBackground(bg);
		lblNuevoEjemplo.setText("QS");
		
		Label label = new Label(canvas, SWT.CENTER);
		label.setText("QS");
		label.setBackgroundImage(ResourceManager.getPluginImage("parseoAST", "Iconos de Diagrama/action-process-symbol.png"));
		label.setBackground(SWTResourceManager.getColor(248, 248, 248));
		label.setBounds(163, 28, 124, 60);
		
		
		
		
		
		
		
		
		

        createActions();;
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

