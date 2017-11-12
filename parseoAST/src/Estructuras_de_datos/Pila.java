package Estructuras_de_datos;

public class Pila<T> {
	private ListaSimple<T> pila;
    public Pila()
    {
        pila = new ListaSimple<T>();
    }
    public T Pop()
    {
        return pila.EliminarPrimero();
    }
    public void Push (T elemento)
    {
        pila.InsertarInicio(elemento);
    }
    public T Peek()
    {
        return pila.Primero();
    }
}
