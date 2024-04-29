package Dibujos.PanelesMovibles;

import Dibujos.PanelMovible;
import Dibujos.PanelPersonalizado;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.List;

public class DibujoEntrada extends PanelMovible {
    private int ultimoEjeY;
    private boolean moviendo;
    private int ejeYMouse;
    public DibujoEntrada(String texto, List<PanelPersonalizado> lista, JPanel _contenedor) {
        super(texto,lista,_contenedor);
    }


    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);


        int panelWidth = getWidth();
        int panelHeight = getHeight();

        // Coordenadas del Paralelogramo
        int x1 = (int) ((panelWidth / 4) + panelWidth * 0.1);                    // Coordenada x del lado izquierdo del paralelogramo
        int x2 = (int) ((panelWidth - (panelWidth / 4)) - panelWidth * 0.1);     // Coordenada x del lado derecho del paralelogramo
        int y1 = (int) ((panelHeight / 4) + panelHeight * 0.15);                 // Coordenada y del lado superior del paralelogramo
        int y2 = (int) ((panelHeight - (panelHeight / 4)) - panelHeight * 0.15); // Coordenada y del lado inferior del paralelogramo
        int desvio = (int) ((x2 - x1) * 0.1);                                    // Inclinación horizontal del paralelogramo


        int centro_x = panelWidth / 2;


        // Dibujar las líneas que forman el paralelogramo
        g.setColor(Color.BLACK);
        g.drawLine(x1 + desvio, y1, x2 + desvio, y1);     // Lado superior
        g.drawLine(x2 + desvio, y1, x2 - desvio, y2);     // Lado derecho
        g.drawLine(x2 - desvio, y2, x1 - desvio, y2);     // Lado inferior
        g.drawLine(x1 - desvio, y2, x1 + desvio, y1);     // Lado izquierdo

        
        // Dibujar flecha de salida
        g.setColor(Color.RED);
        g.drawLine(x2 + desvio, y1, x2 + desvio - 10, y1 + 5);              // Linea de
        g.drawLine(x2 + desvio, y1, x2 + desvio + 10, y1 - 5);              // Flecha

        g.drawLine( x2 + desvio - 10, y1 + 5, x2 + desvio - 7, y1 + 1);  // Punta de
        g.drawLine( x2 + desvio - 10, y1 + 5, x2 + desvio - 6, y1 + 5);  // Flecha

        // Dibujar flujo
        g.setColor(Color.BLACK);
        g.drawLine(centro_x, 0, centro_x, y1);               // Linea superior
        g.drawLine(centro_x, y2, centro_x, panelHeight);         // Linea inferior

        g.drawLine(centro_x, y1, centro_x + 10, y1 - 10);    //  Flecha
        g.drawLine(centro_x, y1, centro_x - 10, y1 - 10);    //  de flujo


        // Dibuja el texto centrado
        FontMetrics metrics = g.getFontMetrics();
        int x = (getWidth() - metrics.stringWidth(texto)) / 2;
        int y = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
        g.drawString(texto, x, y);
    }
}