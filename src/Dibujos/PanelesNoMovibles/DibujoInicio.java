package Dibujos.PanelesNoMovibles;

import Dibujos.PanelPersonalizado;
import Dibujos.Ventana.VentanaEmergente;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.QuadCurve2D;
import java.util.List;
import java.util.Map;

public class DibujoInicio extends PanelPersonalizado {
    protected Font textoFont = new Font("Serif", Font.PLAIN, 20);
    public DibujoInicio(String texto, List<PanelPersonalizado> lista, JPanel _contenedor, GridBagConstraints _restriciones,
                        VentanaEmergente _ventanaEmergente, Map<String, Object> _variables) {
        super(texto, lista, _contenedor,_restriciones,_ventanaEmergente,_variables);
        this.habilitado = false;
        setPreferredSize(new Dimension(200, 300));
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        int panelWidth = getWidth();
        int panelHeight = getHeight();

        //Coordenadas de la figura
        int x1 = (int) ((panelWidth / 4)+panelWidth*0.1);                  // Coordenada x del lado izquierdo del rectángulo
        int x2 = (int) ((panelWidth - (panelWidth / 4))-panelWidth*0.1);   // Coordenada x del lado derecho del rectángulo
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
        g.drawLine(centro_x, y2, centro_x, panelHeight);         // Linea inferior

        // fuente con el tamaño especificado
        g.setFont(textoFont);
        
        // Dibujar el texto centrado
        FontMetrics metrics = g.getFontMetrics();
        int x = (getWidth() - metrics.stringWidth(texto)) / 2;
        int y = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
        g.drawString(texto, x, y);
    }
}


