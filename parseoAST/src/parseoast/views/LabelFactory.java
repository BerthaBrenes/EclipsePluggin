package parseoast.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class LabelFactory implements LabelFactoryMethod{

	@Override
	public Label createLabel(String Texto, int posx, int posy, Composite parent,String Yes,String No) {
		if (Texto == "For") {
			ForMethod For = new ForMethod(Texto, posx, posy, parent, Yes, No);
			return For.label();
		}
		else if (Texto == "Proceso") {
			ProcessMethod process = new ProcessMethod(Texto, posx, posy, parent);
			return process.label();
			
		}
		else if (Texto == "End" | Texto == "Start") {
			Start_End start_end = new Start_End(Texto, posx, posy, parent);
			return start_end.label();
		}
		else {
			return null;
		}
	}

	@Override
	public Label createLabel(String Texto, int posx, int posy, Composite parent) {
		if (Texto == "Proceso") {
			ProcessMethod process = new ProcessMethod(Texto, posx, posy, parent);
			return process.label();
			
		}
		else if (Texto == "End" | Texto == "Start") {
			Start_End start_end = new Start_End(Texto, posx, posy, parent);
			return start_end.label();
		}
		else {
			return null;
		}
	}

}
