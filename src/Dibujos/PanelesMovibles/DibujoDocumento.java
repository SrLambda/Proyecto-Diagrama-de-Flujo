package Dibujos.PanelesMovibles;
import Dibujos.PanelMovible;
import Dibujos.PanelPersonalizado;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.QuadCurve2D;
import java.util.List;

public class DibujoDocumento extends PanelMovible {
    protected Font textoFont = new Font("Serif", Font.PLAIN, 20);
    public DibujoDocumento(String texto, List<PanelPersonalizado> lista, JPanel _contenedor) {
        super(texto,lista,_contenedor);
        setPreferredSize(new Dimension(200, 400));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int panelWidth = getWidth();
        int panelHeight = getHeight();

        Graphics2D g2d = (Graphics2D) g;
        // Coordenadas de la figura
        int x1 = (int) ((panelWidth / 4) + panelWidth * 0.1);                  // Coordenada x del lado izquierdo del rectángulo
        int x2 = (int) ((panelWidth - (panelWidth / 4)) - panelWidth * 0.1);   // Coordenada x del lado derecho del rectángulo
        int y1 = (int) ((panelHeight / 4) + panelHeight * 0.15);                 // Coordenada y del lado superior del rectángulo
        int y2 = (int) ((panelHeight - (panelHeight / 4)) - panelHeight * 0.15); // Coordenada y del lado inferior del rectángulo
        double ctrl1x = (x1 + (x1 / 2) * 0.3);
        double ctrl1y = -1.1 * (y2);
        double ctrl2x = (x2 + (x2 / 4) * -0.3);
        double ctrl2y = 0.9 * (y2);
        int x3 = (int) ((panelWidth / 4) + panelWidth * 0.25);

        // Coordenadas de Flujo
        int centro_x = panelWidth/2;

        // Dibujar las líneas que forman el rectángulo
        g.setColor(Color.BLACK);
        g.drawLine(x1, y1, x2, y1);     // Lado superior
        g.drawLine(x2, y1, x2, y2);     // Lado derecho
        g.drawLine(x1, y2, x1, y1);     // Lado izquierdo
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(1)); // Grosor de línea
        g2d.draw(new QuadCurve2D.Double(x1, y2, ctrl1x, -ctrl1y, x3, y2));
        g2d.draw(new QuadCurve2D.Double(x3, y2, ctrl2x, ctrl2y, x2, y2));

        // Dibujar flujo
        g2d.setStroke(new BasicStroke(1));
        g.setColor(Color.BLACK);
        g.drawLine(centro_x,0,centro_x,y1);        // Linea superior
        g.drawLine(centro_x,y2,centro_x,panelHeight);  // Linea inferior
        g.drawLine(centro_x,y1,centro_x+10,y1-10);
        g.drawLine(centro_x,y1,centro_x-10,y1-10);

        // fuente con el tamaño especificado
        g.setFont(textoFont);

        // Dibuja el texto centrado
        FontMetrics metrics = g.getFontMetrics();
        int x = (getWidth() - metrics.stringWidth(texto)) / 2;
        int y = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
        g.drawString(texto, x, y);
    }
}





