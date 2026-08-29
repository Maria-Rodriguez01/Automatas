/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estructuras;

/**
 *
 * @author maria
 */
public class ListaEstadosFinales {

    private NodoEstado cabeza;

    public ListaEstadosFinales() {
        cabeza = null;
    }

    public void insertar(NodoEstado estado) {

        NodoEstado nuevo = new NodoEstado(estado.nombre);

        if (cabeza == null) {
            cabeza = nuevo;
            return;
        }

        NodoEstado actual = cabeza;

        while (actual.siguiente != null) {
            actual = actual.siguiente;
        }

        actual.siguiente = nuevo;
    }

    public boolean existe(String nombre) {

        NodoEstado actual = cabeza;

        while (actual != null) {

            if (actual.nombre.equals(nombre)) {
                return true;
            }

            actual = actual.siguiente;
        }

        return false;
    }

    public String obtenerTexto() {

        String texto = "";
        NodoEstado actual = cabeza;

        while (actual != null) {
            texto = texto + actual.nombre + "\n";
            actual = actual.siguiente;
        }

        return texto;
    }
}
