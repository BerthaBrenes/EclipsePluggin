package src.MoViCo;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.FreeformLayeredPane;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.MarginBorder;
/**
 * Clase para setear las configuraciones del panel o canvas sobre el que se setean
 * las figuras del digrama de flujo
 *
 */
public class ChartFigure extends FreeformLayeredPane {
	  public ChartFigure() {
	    setLayoutManager(new FreeformLayout());
	    setBorder(new MarginBorder(5));
	    setBackgroundColor(ColorConstants.white);
	    setOpaque(true);
	  }
	}