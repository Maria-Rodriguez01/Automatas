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
import estructuras.NodoTransicion;

public class UnionDFA {

    public DFA unir(DFA dfa1, DFA dfa2) {
        if (dfa1 == null || dfa2 == null || !mismoAlfabeto(dfa1, dfa2))
            return null;

        DFA r = new DFA();

        NodoSimbolo s = dfa1.getSimbolos().getCabeza();
        while (s != null) {
            r.agregarSimbolo(s.getSimbolo());
            s = s.getSiguiente();
        }

        String inicial = "(" + dfa1.getEstadoInicial() + ","
                + dfa2.getEstadoInicial() + ")";

        r.agregarEstado(inicial);
        r.setEstadoInicial(inicial);

        if (dfa1.getEstadosFinales().existe(dfa1.getEstadoInicial())
                || dfa2.getEstadosFinales().existe(dfa2.getEstadoInicial()))
            r.agregarEstadoFinal(inicial);

        NodoEstado actual = r.getEstados().getCabeza();

        while (actual != null) {
            String[] partes = separar(actual.getNombre());

            NodoSimbolo simbolo = r.getSimbolos().getCabeza();

            while (simbolo != null) {
                String d1 = destino(dfa1, partes[0], simbolo.getSimbolo());
                String d2 = destino(dfa2, partes[1], simbolo.getSimbolo());

                if (d1 != null && d2 != null) {
                    String nuevo = "(" + d1 + "," + d2 + ")";

                    if (!r.getEstados().existe(nuevo)) {
                        r.agregarEstado(nuevo);

                        if (dfa1.getEstadosFinales().existe(d1)
                                || dfa2.getEstadosFinales().existe(d2))
                            r.agregarEstadoFinal(nuevo);
                    }

                    if (!r.getTransiciones().existe(
                            actual.getNombre(), simbolo.getSimbolo()))
                        r.agregarTransicion(
                                actual.getNombre(),
                                simbolo.getSimbolo(),
                                nuevo);
                }

                simbolo = simbolo.getSiguiente();
            }

            actual = actual.getSiguiente();
        }

        return r;
    }

    private boolean mismoAlfabeto(DFA dfa1, DFA dfa2) {
        NodoSimbolo s = dfa1.getSimbolos().getCabeza();

        while (s != null) {
            if (!dfa2.getSimbolos().existe(s.getSimbolo()))
                return false;
            s = s.getSiguiente();
        }

        s = dfa2.getSimbolos().getCabeza();

        while (s != null) {
            if (!dfa1.getSimbolos().existe(s.getSimbolo()))
                return false;
            s = s.getSiguiente();
        }

        return true;
    }

    private String destino(DFA dfa, String origen, String simbolo) {
        NodoTransicion t = dfa.getTransiciones().getCabeza();

        while (t != null) {
            if (t.getOrigen().equals(origen)
                    && t.getSimbolo().equals(simbolo))
                return t.getDestino();

            t = t.getSiguiente();
        }

        return null;
    }

    private String[] separar(String estado) {
        String texto = estado.substring(1, estado.length() - 1);
        int coma = texto.indexOf(",");

        return new String[]{
            texto.substring(0, coma),
            texto.substring(coma + 1)
        };
    }
}