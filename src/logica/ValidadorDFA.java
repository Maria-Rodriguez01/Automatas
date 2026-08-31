package logica;

/**
 *
 * @author maria
 */
import estructuras.NodoEstado;
import estructuras.NodoSimbolo;
import estructuras.NodoTransicion;

public class ValidadorDFA {

    public String validar(DFA dfa) {

        if (dfa == null) {
            return "DFA inválido: el DFA no existe.";
        }
        if (dfa.getEstados().obtenerTexto().isEmpty()) {
            return "DFA inválido: no se han agregado estados.";
        }
        if (dfa.getSimbolos().obtenerTexto().isEmpty()) {
            return "DFA inválido: no se ha definido el alfabeto.";
        }
        if (dfa.getEstadoInicial() == null
                || dfa.getEstadoInicial().isEmpty()) {
            return "DFA inválido: no se ha definido el estado inicial.";
        }
        if (!dfa.getEstados().existe(
                dfa.getEstadoInicial())) {
            return "DFA inválido: el estado inicial '"
                    + dfa.getEstadoInicial()
                    + "' no pertenece al conjunto de estados.";
        }
        if (dfa.getEstadosFinales().obtenerTexto().isEmpty()) {
            return "DFA inválido: no se han definido estados finales.";
        }

        NodoEstado finalActual =
                dfa.getEstadosFinales().getCabeza();

        while (finalActual != null) {

            if (!dfa.getEstados().existe(
                    finalActual.getNombre())) {

                return "DFA inválido: el estado final '"
                        + finalActual.getNombre()
                        + "' no pertenece al conjunto de estados.";
            }

            finalActual =
                    finalActual.getSiguiente();
        }

        NodoTransicion transicionActual =
                dfa.getTransiciones().getCabeza();

        while (transicionActual != null) {

            if (!dfa.getEstados().existe(
                    transicionActual.getOrigen())) {

                return "DFA inválido: el estado origen '"
                        + transicionActual.getOrigen()
                        + "' no pertenece al conjunto de estados.";
            }

            if (!dfa.getSimbolos().existe(
                    transicionActual.getSimbolo())) {

                return "DFA inválido: el símbolo '"
                        + transicionActual.getSimbolo()
                        + "' no pertenece al alfabeto.";
            }

            if (!dfa.getEstados().existe(
                    transicionActual.getDestino())) {

                return "DFA inválido: el estado destino '"
                        + transicionActual.getDestino()
                        + "' no pertenece al conjunto de estados.";
            }

            transicionActual =
                    transicionActual.getSiguiente();
        }

        NodoEstado estadoActual =
                dfa.getEstados().getCabeza();

        while (estadoActual != null) {

            NodoSimbolo simboloActual =
                    dfa.getSimbolos().getCabeza();

            while (simboloActual != null) {

                int cantidad =
                        dfa.getTransiciones().contar(
                                estadoActual.getNombre(),
                                simboloActual.getSimbolo()
                        );

                if (cantidad == 0) {

                    return "DFA inválido: falta la transición ("
                            + estadoActual.getNombre()
                            + ", "
                            + simboloActual.getSimbolo()
                            + ").";
                }

                if (cantidad > 1) {

                    return "DFA inválido: existen "
                            + cantidad
                            + " transiciones para ("
                            + estadoActual.getNombre()
                            + ", "
                            + simboloActual.getSimbolo()
                            + ").";
                }

                simboloActual =
                        simboloActual.getSiguiente();
            }

            estadoActual =
                    estadoActual.getSiguiente();
        }

        return "DFA válido.";
    }
}