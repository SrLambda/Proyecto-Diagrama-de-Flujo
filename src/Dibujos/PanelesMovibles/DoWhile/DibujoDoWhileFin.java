package Dibujos.PanelesMovibles.DoWhile;

import Dibujos.PanelPersonalizado;
import Dibujos.Ventana.VentanaEmergente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;

public class DibujoDoWhileFin extends PanelPersonalizado {
    private DibujoDoWhileInterno interno;
    private List<PanelPersonalizado> panelesCiclo;
    protected Font textoFont = new Font("Serif", Font.PLAIN, 20);

    public DibujoDoWhileFin(String texto, List<PanelPersonalizado> lista, JPanel _contenedor, DibujoDoWhileInterno _interno,
                            GridBagConstraints _restriciones, VentanaEmergente _ventanaEmergente, Map<String, Object> _variables) {
        super(texto, lista, _contenedor, _restriciones,_ventanaEmergente,_variables);
        this.interno = _interno;
        panelesCiclo = lista;

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    String nuevoTexto = JOptionPane.showInputDialog(null, "Editar texto:", texto);
                    if (nuevoTexto != null && !nuevoTexto.isEmpty()) {
                        cambiarTexto(nuevoTexto);
                    }
                }
            }
        });
    }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            int panelWidth = getWidth();
            int panelHeight = getHeight();

            int centro_x = panelWidth / 2;                                         // Centro horizontal
            int centro_y = panelHeight / 2;                                        // Centro vertical

            int cuarto = panelWidth / 4;

            // Coordenadas del rombo
            int x1 = (int) ((panelWidth / 4) + panelWidth * 0.1);                    // Coordenada x del lado izquierdo del rectángulo
            int x2 = (int) ((panelWidth - (panelWidth / 4)) - panelWidth * 0.1);     // Coordenada x del lado derecho del rectángulo
            int y1 = (int) ((panelHeight / 4) + panelHeight * 0.15);                 // Coordenada y del lado superior del rectángulo
            int y2 = (int) ((panelHeight - (panelHeight / 4)) - panelHeight * 0.15); // Coordenada y del lado inferior del rectángulo

            // Dibujar las líneas que forman el rombo
            g.setColor(Color.BLACK);
            g.drawLine(x1, centro_y, centro_x, y1);     // Lado superior
            g.drawLine(x2, centro_y, centro_x, y1);     // Lado derecho
            g.drawLine(x1, centro_y, centro_x, y2);     // Lado inferior
            g.drawLine(x2, centro_y, centro_x, y2);     // Lado izquierdo

            // Dibujar flujo
            g.drawLine(centro_x,0,centro_x,y1);  // Linea superior
            g.drawLine(centro_x,y2,centro_x,panelHeight);     // Linea inferior
            g.drawLine((int) (centro_x/1.4), centro_y,centro_x/3, centro_y); // Linea horizontal izquierda
            g.drawLine(centro_x/3,0,centro_x/3, centro_y);     // Linea vertical izquierda
            g.drawLine(centro_x,y1,centro_x+10,y1-10);    //  Flecha
            g.drawLine(centro_x,y1,centro_x-10,y1-10);    //  de flujo

            // Dibujar el texto centrado
            g.setFont(textoFont);
            FontMetrics metrics = g.getFontMetrics();
            int x = (getWidth() - metrics.stringWidth(texto)) / 2;
            int y = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
            g.drawString(texto, x, y);
        }

        public List<PanelPersonalizado> getListaFiguras() {
            return listaFiguras;
        }
}

