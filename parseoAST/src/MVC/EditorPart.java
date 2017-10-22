package MVC;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import java.util.List;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.geometry.Dimension;

public class EditorPart extends AbstractGraphicalEditPart {

	@Override
	protected IFigure createFigure() {
		Figure figura = new FreeformLayer();
		figura.setLayoutManager(new FreeformLayout());
		figura.setBorder(new MarginBorder(1));
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 3;
		gridLayout.horizontalSpacing = 40;
		gridLayout.verticalSpacing = 40;
		gridLayout.marginHeight = 20;
		gridLayout.marginWidth = 20;
		figura.setLayoutManager(gridLayout);
		figura.setOpaque(true);
		return figura;
	}

	@Override
	protected void createEditPolicies() {
		// TODO Auto-generated method stub
		
	}
	protected List<NodoModelo> getModelChildren () {
		List<NodoModelo> children = (getChildren());
		return children;
	}

}
