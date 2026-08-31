package GUI;

/**
 *
 * @author maria
 */
import estructuras.NodoEstado;
import estructuras.NodoSimbolo;
import estructuras.NodoTransicion;
import javax.swing.JPanel;
import java.awt.*;
import javax.swing.BorderFactory;

public class PanelAutomata extends JPanel {

    private logica.DFA dfa;


    public PanelAutomata() {
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(600, 300);
    }
    public void setDFA(logica.DFA dfa) {
        this.dfa = dfa;
        repaint();
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
            if (a.getOrigen().equals(estado) &&
                a.getSimbolo().equals(simbolo))
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
                g.drawString(destino(e.getNombre(), s.getSimbolo()),
                        px + 35, py + 21);
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
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        g2.drawString("Q = " + estados(), 30, 20);
        g2.drawString("Σ = " + simbolos(), 30, 50);
        g2.drawString("q0 = " + dfa.getEstadoInicial(), 30, 80);
        g2.drawString("F = " + finales(), 30, 110);

        g2.setFont(new Font("Segoe UI", Font.BOLD, 12));
        g2.drawString("δ:", 30, 140);
        tabla(g2, 160);
    }
}