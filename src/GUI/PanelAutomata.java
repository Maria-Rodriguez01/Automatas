package GUI;

/**
 *
 * @author maria
 */
import estructuras.NodoEstado;
import estructuras.NodoSimbolo;
import estructuras.NodoTransicion;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.*;

public class PanelAutomata extends JPanel {

    private logica.DFA dfa;
    private String titulo = "";
    private String demostracion = "";

    public PanelAutomata() {
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
        repaint();
    }

    public void setDFA(logica.DFA dfa) {
        this.dfa = dfa;
        repaint();
    }

    public void setDemostracion(String demostracion) {
        this.demostracion = demostracion == null ? "" : demostracion;
        revalidate();
        repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        int alto = 300;

        if (!demostracion.isEmpty())
            alto += 40 + demostracion.split("\n").length * 20;

        return new Dimension(700, alto);
    }

    private String estados() {
        StringBuilder s = new StringBuilder("{");
        NodoEstado a = dfa.getEstados().getCabeza();

        while (a != null) {
            s.append(a.getNombre());
            if (a.getSiguiente() != null) s.append(", ");
            a = a.getSiguiente();
        }

        return s.append("}").toString();
    }

    private String simbolos() {
        StringBuilder s = new StringBuilder("{");
        NodoSimbolo a = dfa.getSimbolos().getCabeza();

        while (a != null) {
            s.append(a.getSimbolo());
            if (a.getSiguiente() != null) s.append(", ");
            a = a.getSiguiente();
        }

        return s.append("}").toString();
    }

    private String finales() {
        StringBuilder s = new StringBuilder("{");
        NodoEstado a = dfa.getEstadosFinales().getCabeza();

        while (a != null) {
            s.append(a.getNombre());
            if (a.getSiguiente() != null) s.append(", ");
            a = a.getSiguiente();
        }

        return s.append("}").toString();
    }

    private String destino(String estado, String simbolo) {
        NodoTransicion a = dfa.getTransiciones().getCabeza();

        while (a != null) {
            if (a.getOrigen().equals(estado)
                    && a.getSimbolo().equals(simbolo))
                return a.getDestino();

            a = a.getSiguiente();
        }

        return "-";
    }

    private void tabla(Graphics2D g, int y) {
        int x = 30, we = 150, ws = 100, h = 30;

        g.setFont(new Font("Segoe UI", Font.BOLD, 16));
        g.drawRect(x, y, we, h);
        g.drawString("δ", x + 65, y + 21);

        NodoSimbolo s = dfa.getSimbolos().getCabeza();
        int c = 0;

        while (s != null) {
            int px = x + we + c * ws;
            g.drawRect(px, y, ws, h);
            g.drawString(s.getSimbolo(), px + 45, y + 21);
            c++;
            s = s.getSiguiente();
        }

        NodoEstado e = dfa.getEstados().getCabeza();
        int f = 1;

        g.setFont(new Font("Segoe UI", Font.PLAIN, 15));

        while (e != null) {
            int py = y + f * h;

            g.drawRect(x, py, we, h);
            g.drawString(e.getNombre(), x + 60, py + 21);

            s = dfa.getSimbolos().getCabeza();
            c = 0;

            while (s != null) {
                int px = x + we + c * ws;

                g.drawRect(px, py, ws, h);
                g.drawString(
                        destino(e.getNombre(), s.getSimbolo()),
                        px + 35,
                        py + 21
                );

                c++;
                s = s.getSiguiente();
            }

            f++;
            e = e.getSiguiente();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (dfa == null) return;

        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        int y = 25;

        g2.setFont(new Font("Segoe UI", Font.BOLD, 18));

        if (!titulo.isEmpty()) {
            g2.drawString(titulo, 30, y);
            y += 35;
        }

        g2.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        g2.drawString("Q = " + estados(), 30, y);
        g2.drawString("Σ = " + simbolos(), 30, y + 30);
        g2.drawString("q0 = " + dfa.getEstadoInicial(), 30, y + 60);
        g2.drawString("F = " + finales(), 30, y + 90);

        g2.setFont(new Font("Segoe UI", Font.BOLD, 12));
        g2.drawString("δ:", 30, y + 120);

        tabla(g2, y + 140);

        if (!demostracion.isEmpty()) {

            int inicio = y + 185
                    + contarEstados() * 30;

            g2.setFont(new Font("Segoe UI", Font.BOLD, 14));
            g2.drawString("Demostración de δU:", 30, inicio);

            g2.setFont(new Font("Segoe UI", Font.PLAIN, 14));

            int linea = inicio + 30;

            for (String texto : demostracion.split("\n")) {
                g2.drawString(texto, 30, linea);
                linea += 20;
            }
        }
    }

    private int contarEstados() {
        int n = 0;
        NodoEstado e = dfa.getEstados().getCabeza();

        while (e != null) {
            n++;
            e = e.getSiguiente();
        }

        return n;
    }
}