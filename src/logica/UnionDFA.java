
package logica;

/**
 *
 * @author maria
 */
import estructuras.NodoEstado;
import estructuras.NodoSimbolo;

public class UnionDFA {

    private StringBuilder demostracion = new StringBuilder();

    public DFA unir(DFA a, DFA b) {

        if (a == null || b == null || !mismoAlfabeto(a, b))
            return null;

        demostracion.setLength(0);

        DFA r = new DFA();

        NodoSimbolo s = a.getSimbolos().getCabeza();
        while (s != null) {
            r.agregarSimbolo(s.getSimbolo());
            s = s.getSiguiente();
        }

        NodoEstado e1 = a.getEstados().getCabeza();

        while (e1 != null) {

            NodoEstado e2 = b.getEstados().getCabeza();

            while (e2 != null) {

                String estado =
                        "(" + e1.getNombre() + "," + e2.getNombre() + ")";

                r.agregarEstado(estado);

                if (a.getEstadosFinales().existe(e1.getNombre())
                        || b.getEstadosFinales().existe(e2.getNombre()))
                    r.agregarEstadoFinal(estado);

                e2 = e2.getSiguiente();
            }

            e1 = e1.getSiguiente();
        }

        String inicial =
                "(" + a.getEstadoInicial() + ","
                + b.getEstadoInicial() + ")";

        r.setEstadoInicial(inicial);

        e1 = a.getEstados().getCabeza();

        while (e1 != null) {

            NodoEstado e2 = b.getEstados().getCabeza();

            while (e2 != null) {

                String origen =
                        "(" + e1.getNombre() + ","
                        + e2.getNombre() + ")";

                s = r.getSimbolos().getCabeza();

                while (s != null) {

                    String d1 =
                            a.getTransiciones().obtenerDestino(
                                    e1.getNombre(),
                                    s.getSimbolo());

                    String d2 =
                            b.getTransiciones().obtenerDestino(
                                    e2.getNombre(),
                                    s.getSimbolo());

                    if (d1 != null && d2 != null) {

                        String destino =
                                "(" + d1 + "," + d2 + ")";

                        r.agregarTransicion(
                                origen,
                                s.getSimbolo(),
                                destino);

                        demostracion.append(
                                "δU(")
                                .append(origen)
                                .append(", ")
                                .append(s.getSimbolo())
                                .append(")\n");

                        demostracion.append(
                                "= (δ1(")
                                .append(e1.getNombre())
                                .append(", ")
                                .append(s.getSimbolo())
                                .append("), δ2(")
                                .append(e2.getNombre())
                                .append(", ")
                                .append(s.getSimbolo())
                                .append("))\n");

                        demostracion.append(
                                "= (")
                                .append(d1)
                                .append(", ")
                                .append(d2)
                                .append(")\n\n");
                    }

                    s = s.getSiguiente();
                }

                e2 = e2.getSiguiente();
            }

            e1 = e1.getSiguiente();
        }

        return r;
    }

    public String getDemostracion() {
        return demostracion.toString();
    }

    private boolean mismoAlfabeto(DFA a, DFA b) {

        NodoSimbolo s = a.getSimbolos().getCabeza();

        while (s != null) {
            if (!b.getSimbolos().existe(s.getSimbolo()))
                return false;
            s = s.getSiguiente();
        }

        s = b.getSimbolos().getCabeza();

        while (s != null) {
            if (!a.getSimbolos().existe(s.getSimbolo()))
                return false;
            s = s.getSiguiente();
        }

        return true;
    }
}