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

        if (dfa1 == null || dfa2 == null) {
            return null;
        }

        if (!mismoAlfabeto(dfa1, dfa2)) {
            return null;
        }

        DFA resultado = new DFA();

        NodoSimbolo simbolo =
                dfa1.getSimbolos().getCabeza();

        while (simbolo != null) {

            resultado.agregarSimbolo(
                    simbolo.getSimbolo()
            );

            simbolo = simbolo.getSiguiente();
        }

        String inicial =
                crearNombreEstado(
                        dfa1.getEstadoInicial(),
                        dfa2.getEstadoInicial()
                );

        resultado.agregarEstado(inicial);
        resultado.setEstadoInicial(inicial);

        if (dfa1.getEstadosFinales().existe(
                dfa1.getEstadoInicial())
                ||
                dfa2.getEstadosFinales().existe(
                        dfa2.getEstadoInicial())) {

            resultado.agregarEstadoFinal(inicial);
        }

        NodoEstado estadoActual =
                resultado.getEstados().getCabeza();

        while (estadoActual != null) {

            String nombre =
                    estadoActual.getNombre();

            String[] componentes =
                    separarEstado(nombre);

            String estadoDFA1 =
                    componentes[0];

            String estadoDFA2 =
                    componentes[1];

            NodoSimbolo simboloActual =
                    resultado.getSimbolos().getCabeza();

            while (simboloActual != null) {

                String simboloTexto =
                        simboloActual.getSimbolo();

                String destinoDFA1 =
                        obtenerDestino(
                                dfa1,
                                estadoDFA1,
                                simboloTexto
                        );

                String destinoDFA2 =
                        obtenerDestino(
                                dfa2,
                                estadoDFA2,
                                simboloTexto
                        );

                if (destinoDFA1 != null
                        && destinoDFA2 != null) {

                    String nuevoEstado =
                            crearNombreEstado(
                                    destinoDFA1,
                                    destinoDFA2
                            );

                    if (!resultado.getEstados().existe(
                            nuevoEstado)) {

                        resultado.agregarEstado(
                                nuevoEstado
                        );

                        if (dfa1.getEstadosFinales().existe(
                                destinoDFA1)
                                ||
                                dfa2.getEstadosFinales().existe(
                                        destinoDFA2)) {

                            resultado.agregarEstadoFinal(
                                    nuevoEstado
                            );
                        }
                    }

                    resultado.agregarTransicion(
                            nombre,
                            simboloTexto,
                            nuevoEstado
                    );
                }

                simboloActual =
                        simboloActual.getSiguiente();
            }

            estadoActual =
                    estadoActual.getSiguiente();
        }

        return resultado;
    }

    private boolean mismoAlfabeto(
            DFA dfa1,
            DFA dfa2) {

        NodoSimbolo simbolo =
                dfa1.getSimbolos().getCabeza();

        while (simbolo != null) {

            if (!dfa2.getSimbolos().existe(
                    simbolo.getSimbolo())) {

                return false;
            }

            simbolo =
                    simbolo.getSiguiente();
        }

        simbolo =
                dfa2.getSimbolos().getCabeza();

        while (simbolo != null) {

            if (!dfa1.getSimbolos().existe(
                    simbolo.getSimbolo())) {

                return false;
            }

            simbolo =
                    simbolo.getSiguiente();
        }

        return true;
    }

    private String crearNombreEstado(
            String estado1,
            String estado2) {

        return "("
                + estado1
                + ","
                + estado2
                + ")";
    }

    private String[] separarEstado(
            String nombre) {

        String contenido =
                nombre.substring(
                        1,
                        nombre.length() - 1
                );

        int coma =
                contenido.indexOf(",");

        String estado1 =
                contenido.substring(
                        0,
                        coma
                );

        String estado2 =
                contenido.substring(
                        coma + 1
                );

        return new String[]{
            estado1,
            estado2
        };
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