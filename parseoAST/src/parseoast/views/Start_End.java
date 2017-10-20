package parseoast.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.ResourceManager;

public class Start_End extends genLabel {

	public Start_End(String Texto, int posx, int posy, Composite parent) {
		super(Texto, posx, posy, parent);
		
	}

	@Override
	public Label label() {
		Label label = new Label(getParent(), SWT.NONE);
		label.setText("\n"+getText());
		label.setAlignment(SWT.CENTER);
		label.setBackgroundImage(ResourceManager.getPluginImage("parseoAST", "Iconos de Diagrama/start-end-process.jpg"));
		label.setBounds(getPosx(), getPosy(), 124, 60);
		return label;
	}

}
