package Dibujos;
import java.awt.*;

public class DibujoProceso extends PanelPersonalizado {

    public DibujoProceso(String texto) {
        super(texto);
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);


        int panelWidth = getWidth();
        int panelHeight = getHeight();

        // Coordenadas del rectángulo
        int x1 = (int) ((panelWidth / 4)+panelWidth*0.1);                  // Coordenada x del lado izquierdo del rectángulo
        int x2 = (int) ((panelWidth - (panelWidth / 4))-panelWidth*0.1);   // Coordenada x del lado derecho del rectángulo
        int y1 = (int) ((panelHeight / 4)+panelHeight*0.15);                 // Coordenada y del lado superior del rectángulo
        int y2 = (int) ((panelHeight - (panelHeight / 4))-panelHeight*0.15); // Coordenada y del lado inferior del rectángulo

        // Coordenadas de Flujo
        int centro_x = panelWidth/2;


        // Dibujar las líneas que forman el rectángulo

        g.setColor(Color.BLUE);
        g.drawLine(x1, y1, x2, y1);     // Lado superior
        g.drawLine(x2, y1, x2, y2);     // Lado derecho
        g.drawLine(x2, y2, x1, y2);     // Lado inferior
        g.drawLine(x1, y2, x1, y1);     // Lado izquierdo

        // Dibujar flujo

        g.drawLine(centro_x,0,centro_x,y1);        // Linea superior
        g.drawLine(centro_x,y2,centro_x,panelHeight);  // Linea inferior
        g.drawLine(centro_x,y1,centro_x+10,y1-10);
        g.drawLine(centro_x,y1,centro_x-10,y1-10);


        // Dibuja el texto centrado
        FontMetrics metrics = g.getFontMetrics();
        int x = (getWidth() - metrics.stringWidth(texto)) / 2;
        int y = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
        g.drawString(texto, x, y);
    }
}
