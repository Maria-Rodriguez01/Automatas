/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

/**
 *
 * @author maria
 */
import estructuras.NodoTransicion;

public class ProbadorCadena {

    public boolean probar(DFA dfa, String cadena) {

        if (dfa == null) {
            return false;
        }

        if (dfa.getEstadoInicial() == null) {
            return false;
        }

        String estadoActual =
                dfa.getEstadoInicial();

        for (int i = 0; i < cadena.length(); i++) {

            String simbolo =
                    String.valueOf(cadena.charAt(i));

            if (!dfa.getSimbolos().existe(simbolo)) {
                return false;
            }

            String destino =
                    obtenerDestino(
                            dfa,
                            estadoActual,
                            simbolo
                    );

            if (destino == null) {
                return false;
            }

            estadoActual = destino;
        }

        return dfa.getEstadosFinales().existe(
                estadoActual
        );
    }

    public String obtenerRecorrido(
            DFA dfa,
            String cadena) {

        if (dfa == null) {
            return "";
        }

        if (dfa.getEstadoInicial() == null) {
            return "";
        }

        String estadoActual =
                dfa.getEstadoInicial();

        String recorrido =
                estadoActual;

        for (int i = 0; i < cadena.length(); i++) {

            String simbolo =
                    String.valueOf(cadena.charAt(i));

            if (!dfa.getSimbolos().existe(simbolo)) {
                return recorrido;
            }

            String destino =
                    obtenerDestino(
                            dfa,
                            estadoActual,
                            simbolo
                    );

            if (destino == null) {
                return recorrido;
            }

            recorrido =
                    recorrido
                    + " --"
                    + simbolo
                    + "--> "
                    + destino;

            estadoActual = destino;
        }

        return recorrido;
    }

    private String obtenerDestino(
            DFA dfa,
            String origen,
            String simbolo) {

        NodoTransicion actual =
                dfa.getTransiciones().getCabeza();

        while (actual != null) {

            if (actual.getOrigen().equals(origen)
                    && actual.getSimbolo().equals(simbolo)) {

                return actual.getDestino();
            }

            actual =
                    actual.getSiguiente();
        }

        return null;
    }
}