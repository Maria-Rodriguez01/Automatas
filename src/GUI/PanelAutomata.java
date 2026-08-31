/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

/**
 *
 * @author maria
 */
import estructuras.NodoEstado;
import estructuras.NodoTransicion;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.RenderingHints;

public class PanelAutomata extends JPanel {

    private logica.DFA dfa;

    // Tamaño de los estados
    private final int DIAMETRO = 70;

    // Espacio entre estados
    private final int ESPACIO_HORIZONTAL = 80;
    private final int ESPACIO_VERTICAL = 80;

    public PanelAutomata() {

        setBackground(java.awt.Color.WHITE);

        setPreferredSize(
                new java.awt.Dimension(500, 200)
        );

        setBorder(
                javax.swing.BorderFactory.createLineBorder(
                        java.awt.Color.BLACK
                )
        );
    }

    /*
     * Recibe el DFA que se va a dibujar.
     */
    public void setDFA(logica.DFA dfa) {

        this.dfa = dfa;

        actualizarTamano();

        revalidate();
        repaint();
    }

    /*
     * Cuenta la cantidad de estados.
     */
    private int contarEstados() {

        if (dfa == null) {
            return 0;
        }

        int cantidad = 0;

        NodoEstado actual =
                dfa.getEstados().getCabeza();

        while (actual != null) {

            cantidad++;

            actual =
                    actual.getSiguiente();
        }

        return cantidad;
    }

    /*
     * Actualiza el tamaño del panel
     * dependiendo de la cantidad de estados.
     */
    private void actualizarTamano() {

        if (dfa == null) {
            return;
        }

        int cantidadEstados =
                contarEstados();

        if (cantidadEstados == 0) {

            setPreferredSize(
                    new java.awt.Dimension(
                            500,
                            200
                    )
            );

            return;
        }

        // Máximo de estados por fila
        int estadosPorFila = 4;

        int filas =
                (int) Math.ceil(
                        (double) cantidadEstados
                        / estadosPorFila
                );

        int ancho =
                40
                + estadosPorFila
                * (DIAMETRO + ESPACIO_HORIZONTAL);

        int alto =
                40
                + filas
                * (DIAMETRO + ESPACIO_VERTICAL);

        setPreferredSize(
                new java.awt.Dimension(
                        ancho,
                        alto
                )
        );
    }

    /*
     * Obtiene la posición X de un estado.
     */
    private int obtenerX(int indice) {

        int estadosPorFila = 4;

        int columna =
                indice % estadosPorFila;

        return 40
                + columna
                * (DIAMETRO + ESPACIO_HORIZONTAL);
    }

    /*
     * Obtiene la posición Y de un estado.
     */
    private int obtenerY(int indice) {

        int estadosPorFila = 4;

        int fila =
                indice / estadosPorFila;

        return 40
                + fila
                * (DIAMETRO + ESPACIO_VERTICAL);
    }

    /*
     * Busca la posición de un estado
     * dentro de la lista.
     */
    private int obtenerIndiceEstado(
            String nombre) {

        int indice = 0;

        NodoEstado actual =
                dfa.getEstados().getCabeza();

        while (actual != null) {

            if (actual.getNombre().equals(nombre)) {
                return indice;
            }

            indice++;

            actual =
                    actual.getSiguiente();
        }

        return -1;
    }

    /*
     * Dibuja la flecha del estado inicial.
     */
    private void dibujarFlechaInicial(
        Graphics2D g2,
        int x,
        int y) {

    int centroX =
            x + DIAMETRO / 2;

    int inicioY =
            y - 30;

    int finalY =
            y;

    // Línea vertical
    g2.drawLine(
            centroX,
            inicioY,
            centroX,
            finalY
    );

    // Punta de la flecha
    g2.drawLine(
            centroX,
            finalY,
            centroX - 5,
            finalY - 8
    );

    g2.drawLine(
            centroX,
            finalY,
            centroX + 5,
            finalY - 8
    );
}
    /*
     * Dibuja todas las transiciones.
     */
    private void dibujarTransiciones(
            Graphics2D g2) {

        NodoTransicion actual =
                dfa.getTransiciones().getCabeza();

        while (actual != null) {

            int indiceOrigen =
                    obtenerIndiceEstado(
                            actual.getOrigen()
                    );

            int indiceDestino =
                    obtenerIndiceEstado(
                            actual.getDestino()
                    );

            if (indiceOrigen != -1
                    && indiceDestino != -1) {

                int xOrigen =
                        obtenerX(indiceOrigen);

                int yOrigen =
                        obtenerY(indiceOrigen);

                int xDestino =
                        obtenerX(indiceDestino);

                int yDestino =
                        obtenerY(indiceDestino);

                /*
                 * Si origen y destino son iguales,
                 * por ahora no dibujamos el bucle.
                 */
                if (indiceOrigen != indiceDestino) {

                    dibujarTransicion(
                            g2,
                            xOrigen,
                            yOrigen,
                            xDestino,
                            yDestino,
                            actual.getSimbolo()
                    );
                }
            }

            actual =
                    actual.getSiguiente();
        }
    }

    /*
     * Dibuja una transición entre dos estados.
     */
    private void dibujarTransicion(
            Graphics2D g2,
            int x1,
            int y1,
            int x2,
            int y2,
            String simbolo) {

        int centroX1 =
                x1 + DIAMETRO / 2;

        int centroY1 =
                y1 + DIAMETRO / 2;

        int centroX2 =
                x2 + DIAMETRO / 2;

        int centroY2 =
                y2 + DIAMETRO / 2;

        double dx =
                centroX2 - centroX1;

        double dy =
                centroY2 - centroY1;

        double distancia =
                Math.sqrt(
                        dx * dx
                        + dy * dy
                );

        if (distancia == 0) {
            return;
        }

        /*
         * Punto donde sale la línea
         * del primer círculo.
         */
        double inicioX =
                centroX1
                + (dx / distancia)
                * (DIAMETRO / 2);

        double inicioY =
                centroY1
                + (dy / distancia)
                * (DIAMETRO / 2);

        /*
         * Punto donde entra la línea
         * al segundo círculo.
         */
        double finalX =
                centroX2
                - (dx / distancia)
                * (DIAMETRO / 2);

        double finalY =
                centroY2
                - (dy / distancia)
                * (DIAMETRO / 2);

        /*
         * Dibujar línea.
         */
        g2.drawLine(
                (int) inicioX,
                (int) inicioY,
                (int) finalX,
                (int) finalY
        );

        /*
         * Dibujar punta de flecha.
         */
        dibujarPuntaFlecha(
                g2,
                inicioX,
                inicioY,
                finalX,
                finalY
        );

        /*
         * Dibujar símbolo.
         */
        int textoX =
                (int) (
                        (inicioX + finalX) / 2
                );

        int textoY =
                (int) (
                        (inicioY + finalY) / 2
                ) - 8;

        g2.drawString(
                simbolo,
                textoX,
                textoY
        );
    }

    /*
     * Dibuja la punta de una flecha.
     */
    private void dibujarPuntaFlecha(
            Graphics2D g2,
            double inicioX,
            double inicioY,
            double finalX,
            double finalY) {

        double angulo =
                Math.atan2(
                        finalY - inicioY,
                        finalX - inicioX
                );

        int largo = 10;

        double angulo1 =
                angulo + Math.PI * 0.8;

        double angulo2 =
                angulo - Math.PI * 0.8;

        int x1 =
                (int) (
                        finalX
                        + largo
                        * Math.cos(angulo1)
                );

        int y1 =
                (int) (
                        finalY
                        + largo
                        * Math.sin(angulo1)
                );

        int x2 =
                (int) (
                        finalX
                        + largo
                        * Math.cos(angulo2)
                );

        int y2 =
                (int) (
                        finalY
                        + largo
                        * Math.sin(angulo2)
                );

        g2.drawLine(
                (int) finalX,
                (int) finalY,
                x1,
                y1
        );

        g2.drawLine(
                (int) finalX,
                (int) finalY,
                x2,
                y2
        );
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        if (dfa == null) {
            return;
        }

        Graphics2D g2 =
                (Graphics2D) g;

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        g2.setStroke(
                new BasicStroke(2)
        );

        /*
         * PRIMERO:
         * dibujar transiciones.
         */
        dibujarTransiciones(g2);

        /*
         * DESPUÉS:
         * dibujar estados.
         */
        NodoEstado actual =
                dfa.getEstados().getCabeza();

        int indice = 0;

        while (actual != null) {

            int x =
                    obtenerX(indice);

            int y =
                    obtenerY(indice);

            /*
             * Si es el estado inicial,
             * dibujar la flecha antes del círculo.
             */
            if (actual.getNombre().equals(
                    dfa.getEstadoInicial())) {

                dibujarFlechaInicial(
                        g2,
                        x,
                        y
                );
            }

            /*
             * Dibujar círculo exterior.
             */
            g2.drawOval(
                    x,
                    y,
                    DIAMETRO,
                    DIAMETRO
            );

            /*
             * Si es estado final,
             * dibujar segundo círculo.
             */
            if (dfa.getEstadosFinales().existe(
                    actual.getNombre())) {

                g2.drawOval(
                        x + 6,
                        y + 6,
                        DIAMETRO - 12,
                        DIAMETRO - 12
                );
            }

            /*
             * Dibujar nombre del estado.
             */
            String nombre =
                    actual.getNombre();

            java.awt.FontMetrics fm =
                    g2.getFontMetrics();

            int textoX =
                    x
                    + (DIAMETRO
                    - fm.stringWidth(nombre))
                    / 2;

            int textoY =
                    y
                    + (DIAMETRO
                    + fm.getAscent())
                    / 2
                    - 3;

            g2.drawString(
                    nombre,
                    textoX,
                    textoY
            );

            indice++;

            actual =
                    actual.getSiguiente();
        }
    }
}