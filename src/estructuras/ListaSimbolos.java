package estructuras;

/**
 *
 * @author maria
 */

public class ListaSimbolos {

    private NodoSimbolo cabeza;

    public ListaSimbolos() {
        cabeza = null;
    }

    public void insertar(String simbolo) {

        NodoSimbolo nuevo = new NodoSimbolo(simbolo);

        if (cabeza == null) {
            cabeza = nuevo;
            return;
        }

        NodoSimbolo actual = cabeza;

        while (actual.siguiente != null) {
            actual = actual.siguiente;
        }

        actual.siguiente = nuevo;
    }

    public boolean existe(String simbolo) {

        NodoSimbolo actual = cabeza;

        while (actual != null) {

            if (actual.simbolo.equals(simbolo)) {
                return true;
            }

            actual = actual.siguiente;
        }

        return false;
    }

    public String obtenerTexto() {

        String texto = "";
        NodoSimbolo actual = cabeza;

        while (actual != null) {
            texto = texto + actual.simbolo + "\n";
            actual = actual.siguiente;
        }

        return texto;
    }
    
    public void llenarCombo(javax.swing.JComboBox<String> combo) {

        combo.removeAllItems();

        NodoSimbolo actual = cabeza;

        while (actual != null) {

            combo.addItem(actual.simbolo);

            actual = actual.siguiente;
        }
    }
}