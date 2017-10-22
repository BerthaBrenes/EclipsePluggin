package MVC;
import java.util.ArrayList;
import java.util.List;

import MVC.ConexionesModelo;
public class NodoModelo {
	private List<ConexionesModelo> sourcesC = new ArrayList<ConexionesModelo>();
	private List<ConexionesModelo> targetC = new ArrayList<ConexionesModelo>();
	private String label;
	public NodoModelo (String label) {
		this.label = label;
	}
	public List<ConexionesModelo> getSourcesC() {
		return sourcesC;
	}
	public List<ConexionesModelo> getTargetC() {
		return targetC;
	}
	public String getLabel() {
		return label;
	}
	public void AgregarSource (ConexionesModelo iConect) {
		sourcesC.add(iConect);
	}
	public void AgregarTarget (ConexionesModelo iConect) { 
		targetC.add(iConect);
	}
}
