package Estructuras_de_datos;

public class ListaSimple<T> {
	private NodoSimple<T> inicio;

    private int size = 0;


    public ListaSimple()
    {
        inicio = null;

    }
    public void InsertarInicio(T dato)
    {
        if (inicio == null)
        {
            inicio = new NodoSimple<T>(dato, null);

        }
        else
        {
            NodoSimple<T> nuevo = new NodoSimple<T>(dato, inicio);
            nuevo.setSiguiente(inicio);
            inicio = nuevo;
        }
        size++;
    }
    public T EliminarPrimero () {
    	if (inicio != null) {
    		T dato = inicio.getDato();
    		inicio = inicio.getSiguiente();
    		return dato;
    	}
    	else {
    		return null;
    	}
    }
    public T Primero () {
    	return inicio.getDato();
    }
}
