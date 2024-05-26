package Dibujos;

import java.awt.*;
import java.awt.geom.QuadCurve2D;

public class DibujoDecision extends PanelPersonalizado{
    public DibujoDecision(String texto) {

        super(texto);

    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        int panelWidth = getWidth();
        int panelHeight = getHeight();

        // Coordenadas de la figura


        g.setColor(Color.BLUE);
        int centro_x = panelWidth / 2;

        // Dibujar figura de entrada


        // Dibujar flujo
        /*g.drawLine(centro_x, 0, centro_x, y1);               // Linea superior
        g.drawLine(centro_x, y2, centro_x, panelHeight);         // Linea inferior

        g.drawLine(centro_x, y1, centro_x + 10, y1 - 10);    //  Flecha
        g.drawLine(centro_x, y1, centro_x - 10, y1 - 10);    //  de flujo*/

        // Dibujar el texto centrado
        FontMetrics metrics = g.getFontMetrics();
        int x = (getWidth() - metrics.stringWidth(texto)) / 2;
        int y = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
        g.drawString(texto, x, y);
    }
}
