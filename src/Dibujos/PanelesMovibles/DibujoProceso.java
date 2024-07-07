package Dibujos.PanelesMovibles;
import Dibujos.PanelMovible;
import Dibujos.PanelPersonalizado;
import Dibujos.Ventana.VentanaEmergente;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DibujoProceso extends PanelMovible {
    private int ultimoEjeY;
    private boolean moviendo;
    private int ejeYMouse;

    public DibujoProceso(String texto, List <PanelPersonalizado> lista, JPanel _contenedor,GridBagConstraints _restriciones, VentanaEmergente _ventanaEmergente) {
        super(texto, lista, _contenedor,_restriciones,_ventanaEmergente);

    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        // Ajustar para zoom centralizado
        g2d.translate(getWidth() / 2, getHeight() / 2);
        g2d.scale(zoomFactor, zoomFactor);
        g2d.translate(-getWidth() / 2, -getHeight() / 2);

        int widthTx  = this.anchoAlto[0];
        int heightTx = this.anchoAlto[1];

        int centro_x = this.getWidth()  / 2;
        int centro_y = this.getHeight() / 2;

        // Coordenadas del rectángulo
        int x1 = centro_x - ( ( widthTx  / 2 ) + 10); // Coordenada x del lado izquierdo del rectángulo
        int x2 = centro_x + ( ( widthTx  / 2 ) + 10); // Coordenada x del lado derecho del rectángulo
        int y1 = centro_y - ( ( heightTx / 2 ) + 10); // Coordenada y del lado superior del rectángulo
        int y2 = centro_y + ( ( heightTx / 2 ) + 10); // Coordenada y del lado inferior del rectángulo


        // Dibujar las líneas que forman el rectángulo
        g2d.setColor(Color.BLACK);
        g2d.drawLine(x1, y1, x2, y1);     // Lado superior
        g2d.drawLine(x2, y1, x2, y2);     // Lado derecho
        g2d.drawLine(x2, y2, x1, y2);     // Lado inferior
        g2d.drawLine(x1, y2, x1, y1);     // Lado izquierdo

        // Dibujar flujo
        g2d.setColor(Color.BLACK);
        g2d.drawLine(centro_x,0,centro_x,y1);            // Linea superior
        g2d.drawLine(centro_x,y2,centro_x,getHeight());     // Linea inferior
        g2d.drawLine(centro_x,y1,centro_x+10,y1-10);
        g2d.drawLine(centro_x,y1,centro_x-10,y1-10);


        // fuente con el tamaño especificado
        g2d.setFont(textoFont);

        // Dibuja el texto en el centro del panel
        FontMetrics metrics = g2d.getFontMetrics();
        int x = (getWidth() - metrics.stringWidth(texto)) / 2;
        int y = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
        g2d.drawString(texto, x, y);

    }
}
