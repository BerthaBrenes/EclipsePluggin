package src.parseoast.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import src.org.eclipse.wb.swt.ResourceManager;

public class ProcessMethod extends genLabel {
	

		public ProcessMethod(String Texto, int posx, int posy,Composite parent) {
			super(Texto, posx, posy, parent);
		}
		
		
		public Label label() {
			Label label = new Label(getParent(), SWT.NONE);
			label.setText("\n"+getText());
			label.setAlignment(SWT.CENTER);
			label.setBackgroundImage(ResourceManager.getPluginImage("parseoAST", "Iconos de Diagrama/action-process-symbol.png"));
			label.setBounds(getPosx(), getPosy(), 124, 60);
			return label;
		}

	}

