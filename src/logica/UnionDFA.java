/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

/**
 *
 * @author maria
 */
import estructuras.NodoEstado;
import estructuras.NodoSimbolo;

public class UnionDFA {

    public DFA unir(DFA dfa1, DFA dfa2) {

        DFA resultado = new DFA();

        // Copiar el alfabeto
        NodoSimbolo simbolo = dfa1.getSimbolos().getCabeza();

        while (simbolo != null) {

            resultado.agregarSimbolo(
                    simbolo.getSimbolo()
            );

            simbolo = simbolo.getSiguiente();
        }

        // Estado inicial de la unión
        String inicial = "("
                + dfa1.getEstadoInicial()
                + ","
                + dfa2.getEstadoInicial()
                + ")";

        resultado.agregarEstado(inicial);
        resultado.setEstadoInicial(inicial);

        return resultado;
    }
}
