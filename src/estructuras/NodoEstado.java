package estructuras;

/**
 *
 * @author maria
 */

public class NodoEstado {

    String nombre;
    NodoEstado siguiente;

    public NodoEstado(String nombre) {
        this.nombre = nombre;
        this.siguiente = null;
    }

    public String getNombre() {
        return nombre;
    }

    public NodoEstado getSiguiente() {
        return siguiente;
    }
}