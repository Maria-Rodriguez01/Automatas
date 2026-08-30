package estructuras;

/**
 *
 * @author maria
 */
public class NodoSimbolo {

    String simbolo;
    NodoSimbolo siguiente;

    public NodoSimbolo(String simbolo) {
        this.simbolo = simbolo;
        this.siguiente = null;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public NodoSimbolo getSiguiente() {
        return siguiente;
    }
}
