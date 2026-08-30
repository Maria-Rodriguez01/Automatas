/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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