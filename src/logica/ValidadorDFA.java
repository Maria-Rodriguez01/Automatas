package logica;

/**
 *
 * @author maria
 */
import estructuras.NodoEstado;
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
        
        if (!dfa.getEstados().existe(dfa.getEstadoInicial())) {

            return "DFA inválido: el estado inicial '"
                    + dfa.getEstadoInicial()
                    + "' no pertenece al conjunto de estados.";
        }

        if (dfa.getEstadosFinales().obtenerTexto().isEmpty()) {
            return "DFA inválido: no se han definido estados finales.";
        }
        
        NodoEstado finalActual
                = dfa.getEstadosFinales().getCabeza();

        while (finalActual != null) {

            if (!dfa.getEstados().existe(finalActual.getNombre())) {

                return "DFA inválido: el estado final '"
                        + finalActual.getNombre()
                        + "' no pertenece al conjunto de estados.";
            }

            finalActual = finalActual.getSiguiente();
        }

        NodoTransicion actual =
        dfa.getTransiciones().getCabeza();

        while (actual != null) {

            if (!dfa.getEstados().existe(actual.getOrigen())) {

                return "DFA inválido: el estado origen '"
                        + actual.getOrigen()
                        + "' no pertenece al conjunto de estados.";
            }
            
            if (!dfa.getSimbolos().existe(actual.getSimbolo())) {

                return "DFA inválido: el símbolo '"
                        + actual.getSimbolo()
                        + "' no pertenece al alfabeto.";
            }
            
            if (!dfa.getEstados().existe(actual.getDestino())) {

                return "DFA inválido: el estado destino '"
                        + actual.getDestino()
                        + "' no pertenece al conjunto de estados.";
            }

            actual = actual.getSiguiente();
        }
        
        return "DFA válido.";
    }
}
