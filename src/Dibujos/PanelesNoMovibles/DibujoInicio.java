package Dibujos.PanelesNoMovibles;

import Dibujos.PanelPersonalizado;
import Dibujos.Ventana.VentanaEmergente;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.QuadCurve2D;
import java.util.List;

public class DibujoInicio extends PanelPersonalizado {
    public DibujoInicio(String texto, List<PanelPersonalizado> lista, JPanel _contenedor, GridBagConstraints _restriciones, VentanaEmergente _ventanaEmergente) {
        super(texto, lista, _contenedor,_restriciones,_ventanaEmergente);
        this.habilitado = false;
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.scale(zoomFactor, zoomFactor);

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

        g2d.setColor(Color.BLACK);

        //Dibujar figura de entrada

        g2d.drawLine(x1, y1, x2, y1);     // Lado superior
        g2d.drawLine(x2, y2, x1, y2);     // Lado inferior


        // Radio = ( ( heightTx / 2 ) + 10)
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(1));
        // Grosor de línea
        g2d.draw(new QuadCurve2D.Double(x1, y1, x1-rx , centro_y-rx , x1 - ( ( heightTx / 2 ) + 10), centro_y));
        g2d.draw(new QuadCurve2D.Double(x1 - ( ( heightTx / 2 ) + 10), centro_y, x1-rx , centro_y+rx ,x1  ,y2 ));

        g2d.draw(new QuadCurve2D.Double(x2, y1, x2+rx , centro_y-rx , x2 + ( ( heightTx / 2 ) + 10), centro_y));
        g2d.draw(new QuadCurve2D.Double(x2 + ( ( heightTx / 2 ) + 10), centro_y, x2+rx , centro_y+rx ,x2  ,y2 ));

        // Dibujar flujo
        g2d.drawLine(centro_x, y2, centro_x, getHeight());         // Linea inferior

        // fuente con el tamaño especificado
        g2d.setFont(textoFont);
        
        // Dibujar el texto centrado
        FontMetrics metrics = g.getFontMetrics();
        int x = (getWidth() - metrics.stringWidth(texto)) / 2;
        int y = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
        g2d.drawString(texto, x, y);

        g2d.dispose();
    }
}


