package MVC;
import MVC.NodoModelo;

import java.util.ArrayList;
import java.util.List;
public class Modelo {
	private List<NodoModelo> nodos;
	
	public List<NodoModelo> getNodos() {
		return nodos;
	}
	public Modelo () {
		nodos = new ArrayList<NodoModelo>();
		for (int i = 0; i<10;i++) {
			NodoModelo nodo = new NodoModelo("Nodo "+i);
			nodos.add(nodo);
		}
		for (int i =0;i<10-1;i++) {
			ConexionesModelo conexion = new ConexionesModelo();
			conexion.setSource((NodoModelo) nodos.get(i));
			conexion.setTarget((NodoModelo) nodos.get(i+1));
			((NodoModelo) nodos.get(i)).AgregarSource(conexion);
			((NodoModelo) nodos.get(i+1)).AgregarTarget(conexion);
		}
		
	
	}
}
