package listas;

public class Nodo<T> {
	private Nodo<T> next;
    private T valor;
    private Nodo<T> prev;
    /**
     * constructor por defecto
     */
    public Nodo(){
        this.next = null;
        this.prev =null; 
    }
    /**
     * para el final el final de la lista
     * @param value
     */ 
    public Nodo(T value){
        this.valor = value;
        this.next = null;
        this.prev = null;
        
    }

    /**
     * Para cualquier otro momento de la lista
     * @param value
     * @param n next
     * @param p previ
     */
    public Nodo(T value, Nodo<T> n, Nodo<T> p){
        this.next = n;
        this.prev = p;
        this.valor = value;
    }
    public T GetDato() {
        return valor;
    }
    public void SetDato(T value){
        this.valor = value;
    }
    public Nodo<T> GetNext(){
        return this.next;
    }
    public void SetNext(Nodo<T> t){
        this.next = t;
    }
    public void SetPrev(Nodo<T> p){
        this.prev = p;
    }
    public Nodo<T> GetPrev(){
        return this.prev;
    }
}
