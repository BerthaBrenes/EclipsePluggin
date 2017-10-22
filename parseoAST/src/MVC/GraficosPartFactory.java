package MVC;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
public class GraficosPartFactory implements EditPartFactory{

	@Override
	public EditPart createEditPart(EditPart iContext, Object iModel) {
		System.out.println("Called GraphicalPartFactory.createEditPart("
				+ iContext + "," + iModel + ")");
 
		EditPart editPart = null;
		if (iModel instanceof Modelo) {
			editPart = new EditorPart();
		} else if (iModel instanceof NodoModelo) {
			editPart = new AnodoEditorPart();
		}
 
		if (editPart != null) {
			editPart.setModel(iModel);
		}
		return editPart;
	}

}
