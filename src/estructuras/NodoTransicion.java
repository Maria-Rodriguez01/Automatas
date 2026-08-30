/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estructuras;

/**
 *
 * @author maria
 */
public class NodoTransicion {

    String origen;
    String simbolo;
    String destino;
    NodoTransicion siguiente;

    public NodoTransicion(String origen, String simbolo, String destino) {
        this.origen = origen;
        this.simbolo = simbolo;
        this.destino = destino;
        this.siguiente = null;
    }

    public String getOrigen() {
        return origen;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public String getDestino() {
        return destino;
    }

    public NodoTransicion getSiguiente() {
        return siguiente;
    }
}