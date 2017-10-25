package parseoast.views;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.part.ViewPart;

import MoViCo.ActivityFigure;
import listas.Lista;
import parseoast.handlers.GetInfo;

public class FlowChart extends ViewPart{
	public FlowChart() {
	}
	public String[] array= new String[]{"Start","For","Proceso","For","Proceso","End","For"};
	private Lista<ActivityFigure> active = new Lista<>();
	private int posx = 150;
	private int posy = 20;
	public void createPartControl(Composite parent) {
		addcontrol(parent);
}
	public void prub (Composite parent) {
		
	}
	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		
	}
	public void addcontrol (Composite parent) {
		parent.setLayout(null);
		
		Label lblPruebaQ = new Label(parent, SWT.NONE);
		lblPruebaQ.setBounds(10, 10, 55, 15);
		lblPruebaQ.setText("Prueba q");
		
		Button btnGen = new Button(parent, SWT.NONE);
		btnGen.setBounds(10, 49, 75, 25);
		btnGen.setText("Gen");
	    btnGen.addListener(SWT.Selection, new Listener() {
			
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				switch (event.type) {
		        case SWT.Selection:
					try {
						ListMethods();
					} catch (ExecutionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		          break;
				}
			}
		});
	}
	public void ListMethods () throws ExecutionException {
		GetInfo info = new GetInfo();
		info.execute(new ExecutionEvent());
		info.condicionales.Imprimir();
	}
}