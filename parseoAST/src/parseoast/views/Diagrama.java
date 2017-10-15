package parseoast.views;



import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.widgets.ProgressBar;

public class Diagrama extends ViewPart {

	public Diagrama() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout(2,false));
		
		Label lblNewLabel = new Label (parent,SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.FILL,SWT.CENTER,false,false,1,1));
		lblNewLabel.setText("Hello World");
		
		ProgressBar progressBar = new ProgressBar(parent, SWT.NONE);
		
		Button btnNewButton_1 = new Button(parent, SWT.NONE);
		btnNewButton_1.setText("New Button");
		new Label(parent, SWT.NONE);

	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
