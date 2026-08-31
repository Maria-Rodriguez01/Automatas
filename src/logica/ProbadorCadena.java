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
        if (dfa == null || dfa.getEstadoInicial() == null) return false;

        String estado = dfa.getEstadoInicial();

        for (int i = 0; i < cadena.length(); i++) {
            String simbolo = String.valueOf(cadena.charAt(i));

            if (!dfa.getSimbolos().existe(simbolo)) return false;

            String siguiente = obtenerDestino(dfa, estado, simbolo);
            if (siguiente == null) return false;

            estado = siguiente;
        }

        return dfa.getEstadosFinales().existe(estado);
    }

    public String obtenerPasos(DFA dfa, String cadena) {
        if (dfa == null || dfa.getEstadoInicial() == null) return "";

        StringBuilder pasos = new StringBuilder();
        String estado = dfa.getEstadoInicial();
        String prefijo = "";

        pasos.append("δ̂(").append(estado)
                .append(", ε) = ").append(estado).append("\n\n");

        for (int i = 0; i < cadena.length(); i++) {
            String simbolo = String.valueOf(cadena.charAt(i));
            String siguiente = obtenerDestino(dfa, estado, simbolo);

            prefijo += simbolo;

            pasos.append("δ̂(").append(dfa.getEstadoInicial())
                    .append(", ").append(prefijo).append(")\n");

            pasos.append("= δ(δ̂(").append(dfa.getEstadoInicial())
                    .append(", ");

            if (prefijo.length() == 1)
                pasos.append("ε");
            else
                pasos.append(prefijo.substring(0, prefijo.length() - 1));

            pasos.append("), ").append(simbolo).append(")\n");

            if (siguiente == null) {
                pasos.append("= transición inexistente\n");
                return pasos.toString();
            }

            pasos.append("= δ(").append(estado)
                    .append(", ").append(simbolo).append(")\n");

            pasos.append("= ").append(siguiente).append("\n\n");

            estado = siguiente;
        }

        pasos.append("Estado final: ").append(estado).append("\n");

        if (dfa.getEstadosFinales().existe(estado))
            pasos.append("Resultado: ACEPTADA");
        else
            pasos.append("Resultado: RECHAZADA");

        return pasos.toString();
    }

    private String obtenerDestino(DFA dfa, String origen, String simbolo) {
        NodoTransicion t = dfa.getTransiciones().getCabeza();

        while (t != null) {
            if (t.getOrigen().equals(origen) &&
                    t.getSimbolo().equals(simbolo))
                return t.getDestino();

            t = t.getSiguiente();
        }

        return null;
    }
}