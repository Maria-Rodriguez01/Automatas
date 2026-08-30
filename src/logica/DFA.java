/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

/**
 *
 * @author maria
 */

import estructuras.ListaEstados;
import estructuras.ListaSimbolos;
import estructuras.ListaEstadosFinales;
import estructuras.ListaTransiciones;

public class DFA {

    private ListaEstados estados;
    private ListaSimbolos simbolos;
    private String estadoInicial;
    private ListaEstadosFinales estadosFinales;
    private ListaTransiciones transiciones;

    public DFA() {

    estados = new ListaEstados();
    simbolos = new ListaSimbolos();
    estadoInicial = null;
    estadosFinales = new ListaEstadosFinales();
    transiciones = new ListaTransiciones();
}
    
    public ListaEstados getEstados() {
    return estados;
}

public ListaSimbolos getSimbolos() {
    return simbolos;
}

public String getEstadoInicial() {
    return estadoInicial;
}

public ListaEstadosFinales getEstadosFinales() {
    return estadosFinales;
}

public ListaTransiciones getTransiciones() {
    return transiciones;
}

public void setEstadoInicial(String estadoInicial) {
    this.estadoInicial = estadoInicial;
}
}