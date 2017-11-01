package parseoast.views;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public interface LabelFactoryMethod {
	public Label createLabel (String Texto,int posx,int posy, Composite parent);

	Label createLabel(String Texto, int posx, int posy, Composite parent, String Yes, String No);
}
