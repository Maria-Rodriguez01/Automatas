package estructuras;

/**
 *
 * @author maria
 */
public class ListaTransiciones {

    private NodoTransicion cabeza;

    public ListaTransiciones() {
        cabeza = null;
    }

    public void insertar(String origen, String simbolo, String destino) {

        NodoTransicion nuevo =
                new NodoTransicion(origen, simbolo, destino);

        if (cabeza == null) {
            cabeza = nuevo;
            return;
        }

        NodoTransicion actual = cabeza;

        while (actual.siguiente != null) {
            actual = actual.siguiente;
        }

        actual.siguiente = nuevo;
    }

    public boolean existe(String origen, String simbolo) {

        NodoTransicion actual = cabeza;

        while (actual != null) {

            if (actual.origen.equals(origen)
                    && actual.simbolo.equals(simbolo)) {

                return true;
            }

            actual = actual.siguiente;
        }

        return false;
    }

    public String obtenerDestino(String origen, String simbolo) {

        NodoTransicion actual = cabeza;

        while (actual != null) {

            if (actual.origen.equals(origen)
                    && actual.simbolo.equals(simbolo)) {

                return actual.destino;
            }

            actual = actual.siguiente;
        }

        return null;
    }

    public String obtenerTexto() {

        String texto = "";

        NodoTransicion actual = cabeza;

        while (actual != null) {

            texto = texto
                    + actual.origen
                    + " --"
                    + actual.simbolo
                    + "--> "
                    + actual.destino
                    + "\n";

            actual = actual.siguiente;
        }

        return texto;
    }
    
    public NodoTransicion getCabeza() {
        return cabeza;
    }
}
