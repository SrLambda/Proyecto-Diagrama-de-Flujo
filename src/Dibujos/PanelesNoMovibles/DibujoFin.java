package Dibujos.PanelesNoMovibles;

import Dibujos.PanelPersonalizado;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DibujoFin extends PanelPersonalizado {
    public DibujoFin(String texto, List<PanelPersonalizado> lista, JPanel _contenedor,GridBagConstraints _restriciones) {
        super(texto, lista, _contenedor,_restriciones);
        this.habilitado = false;
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        int panelWidth = getWidth();
        int panelHeight = getHeight();

        // Coordenadas de la figura
        int x1 = (int) ((panelWidth / 4)+panelWidth*0.1);                    // Coordenada x del lado izquierdo del rectángulo
        int x2 = (int) ((panelWidth - (panelWidth / 4))-panelWidth*0.1);     // Coordenada x del lado derecho del rectángulo
        int y1 = (int) ((panelHeight / 4)+panelHeight*0.15);                 // Coordenada y del lado superior del rectángulo
        int y2 = (int) ((panelHeight - (panelHeight / 4))-panelHeight*0.15); // Coordenada y del lado inferior del rectángulo

        // Radio de los bordes redondeados
        int arcWidth = 100;
        int arcHeight = 100;

        g.setColor(Color.BLACK);
        int centro_x = panelWidth / 2;
        Graphics2D g2d = (Graphics2D) g;

        //Dibujar figura de entrada
        g2d.drawRoundRect(x1, y1, x2 - x1, y2 - y1, arcWidth, arcHeight);

        // Dibujar flujo
        g.setColor(Color.BLACK);
        g.drawLine(centro_x, 0, centro_x, y1);               // Linea superior

        g.drawLine(centro_x, y1, centro_x + 10, y1 - 10);    //  Flecha
        g.drawLine(centro_x, y1, centro_x - 10, y1 - 10);    //  de flujo

        // fuente con el tamaño especificado
        g.setFont(textoFont);

        // Dibujar el texto centrado
        FontMetrics metrics = g.getFontMetrics();
        int x = (getWidth() - metrics.stringWidth(texto)) / 2;
        int y = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
        g.drawString(texto, x, y);
    }
}
