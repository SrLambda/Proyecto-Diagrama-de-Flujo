package Dibujos.PanelesMovibles;

import Dibujos.PanelMovible;
import Dibujos.PanelPersonalizado;
import Dibujos.Ventana.VentanaEmergente;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DibujoEntrada extends PanelMovible {
    private int ultimoEjeY;
    private boolean moviendo;
    private int ejeYMouse;

    public DibujoEntrada(String texto, List<PanelPersonalizado> lista, JPanel _contenedor,GridBagConstraints _restriciones, VentanaEmergente _ventanaEmergente) {
        super(texto, lista, _contenedor,_restriciones,_ventanaEmergente);
        this.texto = validar(validarCadena.validar(texto),"Cadena",texto);
        this.variables.add(this.texto);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.scale(zoomFactor, zoomFactor);

        int widthTx  = this.anchoAlto[0];
        int heightTx = this.anchoAlto[1];

        int centro_x = getWidth()  / 2;
        int centro_y = getHeight() / 2;

        // Coordenadas del Paralelogramo

        int x1 = centro_x - ( ( widthTx  / 2 ) +10);                    // Coordenada x del lado izquierdo del rectángulo
        int x2 = centro_x + ( ( widthTx  / 2 ) +10);     // Coordenada x del lado derecho del rectángulo
        int y1 = centro_y - ( ( heightTx / 2 ) +10);                 // Coordenada y del lado superior del rectángulo
        int y2 = centro_y + ( ( heightTx / 2 ) +10);
        int desvio = (int) ((x2-x1)*0.1);

        // Dibujar las líneas que forman el paralelogramo
        g2d.setColor(Color.BLACK);
        g2d.drawLine(x1+desvio, y1, x2+desvio, y1);     // Lado superior
        g2d.drawLine(x2+desvio, y1, x2-desvio, y2);     // Lado derecho
        g2d.drawLine(x2-desvio, y2 , x1-desvio, y2);     // Lado inferior
        g2d.drawLine(x1-desvio, y2 , x1+desvio, y1);     // Lado izquierdo

        // Dibujar flecha de entrada
        g2d.setColor(Color.RED);
        g2d.drawLine(x2 + desvio, y1, x2 + desvio - 10, y1 + 5);              // Linea de
        g2d.drawLine(x2 + desvio, y1, x2 + desvio + 10, y1 - 5);              // Flecha

        g2d.drawLine( x2 + desvio - 10, y1 + 5, x2 + desvio - 7, y1 + 1);  // Punta de
        g2d.drawLine( x2 + desvio - 10, y1 + 5, x2 + desvio - 6, y1 + 5);  // Flecha

        // Dibujar flujo
        g2d.setColor(Color.BLACK);
        g2d.drawLine(centro_x,0,centro_x,y1);               // Linea superior
        g2d.drawLine(centro_x,y2 ,centro_x,getHeight());         // Linea inferior

        g2d.drawLine(centro_x,y1,centro_x+10,y1-10);    //  Flecha
        g2d.drawLine(centro_x,y1,centro_x-10,y1-10);    //  de flujo

        // fuente con el tamaño especificado
        g2d.setFont(textoFont);

        // Dibuja el texto centrado
        FontMetrics metrics = g2d.getFontMetrics();
        int x = (getWidth() - metrics.stringWidth(texto)) / 2;
        int y = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
        g2d.drawString(texto, x, y);
    }
}