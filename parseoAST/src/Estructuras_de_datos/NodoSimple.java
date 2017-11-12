package Estructuras_de_datos;

public class NodoSimple<T> {
	private T dato;
    private NodoSimple<T> siguiente;

    public T getDato()
    {
        return dato;
    }

    public void setDato(T dato2)
    {
        this.dato = dato2;
    }

    public NodoSimple<T> getSiguiente()
    {
        return siguiente;
    }

    public void setSiguiente(NodoSimple<T> siguiente)
    {
        this.siguiente = siguiente;
    }



    public NodoSimple(T dato2, NodoSimple<T> sig)
    {
        this.dato = dato2;
        this.siguiente = sig;
    }
}
