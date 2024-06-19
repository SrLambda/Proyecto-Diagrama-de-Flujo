package Dibujos.PanelesNoMovibles;

import Dibujos.PanelPersonalizado;
import Dibujos.Ventana.VentanaEmergente;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.QuadCurve2D;
import java.util.List;

public class DibujoFin extends PanelPersonalizado {

    public DibujoFin(String texto, List<PanelPersonalizado> lista, JPanel _contenedor, GridBagConstraints _restriciones, VentanaEmergente _ventanaEmergente) {
        super(texto, lista, _contenedor,_restriciones,_ventanaEmergente);
        this.habilitado = false;
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        int widthTx  = this.anchoAlto[0];
        int heightTx = this.anchoAlto[1];

        int centro_x = this.getWidth()  / 2;
        int centro_y = this.getHeight() / 2;



        // Coordenadas del rectángulo
        int x1 = centro_x - ( ( widthTx  / 2 ) + 10); // Coordenada x del lado izquierdo del rectángulo
        int x2 = centro_x + ( ( widthTx  / 2 ) + 10); // Coordenada x del lado derecho del rectángulo
        int y1 = centro_y - ( ( heightTx / 2 ) + 10); // Coordenada y del lado superior del rectángulo
        int y2 = centro_y + ( ( heightTx / 2 ) + 10); // Coordenada y del lado inferior del rectángulo

        // Radio de los bordes redondeados
        double rx = ( ( heightTx / 2 ) + 10) * Math.cos(Math.PI/4);

        g.setColor(Color.BLACK);
        Graphics2D g2d = (Graphics2D) g;

        //Dibujar figura de entrada

        g.drawLine(x1, y1, x2, y1);     // Lado superior
        g.drawLine(x2, y2, x1, y2);     // Lado inferior


        // Radio = ( ( heightTx / 2 ) + 10)
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(1));
        // Grosor de línea
        g2d.draw(new QuadCurve2D.Double(x1, y1, x1-rx , centro_y-rx , x1 - ( ( heightTx / 2 ) + 10), centro_y));
        g2d.draw(new QuadCurve2D.Double(x1 - ( ( heightTx / 2 ) + 10), centro_y, x1-rx , centro_y+rx ,x1  ,y2 ));

        g2d.draw(new QuadCurve2D.Double(x2, y1, x2+rx , centro_y-rx , x2 + ( ( heightTx / 2 ) + 10), centro_y));
        g2d.draw(new QuadCurve2D.Double(x2 + ( ( heightTx / 2 ) + 10), centro_y, x2+rx , centro_y+rx ,x2  ,y2 ));

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
